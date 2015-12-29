package com.tyrael.laundry.core.service.custom.impl;

import static com.tyrael.laundry.model.branch.QBranch.branch;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.mysema.query.types.expr.BooleanExpression;
import com.tyrael.laundry.commons.dto.BranchDto;
import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustomImpl;
import com.tyrael.laundry.commons.util.AuthenticationUtil;
import com.tyrael.laundry.core.service.BranchService;
import com.tyrael.laundry.core.service.BrandService;
import com.tyrael.laundry.core.service.ServiceTypeService;
import com.tyrael.laundry.core.service.custom.BranchServiceCustom;
import com.tyrael.laundry.model.branch.Branch;
import com.tyrael.laundry.model.branch.Brand;
import com.tyrael.laundry.model.joborder.ServiceType;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
@Transactional
public class BranchServiceCustomImpl
    extends TyraelJpaServiceCustomImpl<Branch, BranchDto, BranchService>
    implements BranchServiceCustom {

    @Autowired
    private BrandService brandService;

    @Autowired
    private ServiceTypeService serviceTypeService;

    private BooleanExpression addBrandFilter(final BooleanExpression predicate) {
        if (AuthenticationUtil.isAuthorized(AuthenticationUtil.ROLE_ADMIN)) {
            return predicate;
        } else {
            List<Brand> brands = brandService.findByUserUsername(AuthenticationUtil.getLoggedInUsername());
            return predicate.and(branch.brand.in(brands));
        }
    }

    @Override
    public PageInfo<BranchDto> pageInfo(Pageable page) {
        BooleanExpression query = branch.deleted.isFalse();
        query = addBrandFilter(query);
        Page<Branch> results = repo.findAll(query, page);
        return toPageInfo(results);
    }

    @Override
    public BranchDto saveInfo(BranchDto dto) {
        Branch branch;
        boolean newBranch = false;
        if (null == dto.getCode()) {
            //CREATE operation, Generate random candidate brand codes until we find a unique one
            Branch existing = null;
            String candidateCode;
            do {
                candidateCode = RandomStringUtils.randomAlphanumeric(5).toLowerCase();
                existing = repo.findByCode(candidateCode);
            } while (null != existing);
    
            //Use the generated code as default brand code
            dto.setCode(candidateCode);
            branch = toEntity(dto);
            newBranch = true;
        } else {
            //UPDATE operation
            Branch existing = repo.findByCode(dto.getCode());
            Preconditions.checkNotNull(existing);
            mapper.map(dto, existing);
            branch = existing;
        }

        //Set the brand
        Brand brand = brandService.findByCode(dto.getBrand().getCode());
        Preconditions.checkNotNull(brand);
        branch.setBrand(brand);

        Branch saved = repo.save(branch);

        if (newBranch) {
            //Clone the original service types and copy them for this branch
//            Page<ServiceType> serviceTypes = serviceTypeService.findByBranchCodeAndEnabled(null, true, null);
            List<ServiceType> serviceTypes = serviceTypeService.findByBranchCode(null);
            for (ServiceType originalSt : serviceTypes) {
                ServiceType clone = mapper.map(originalSt, ServiceType.class);
                clone.setId(null);
                clone.setBranch(saved);
                serviceTypeService.save(clone);
            }
        }

        return toDto(saved);
    }

    @Override
    public BranchDto findInfoByBrandCodeAndCode(String brandCode, String branchCode) {
        return toDto(repo.findByBrandCodeAndCode(brandCode, branchCode));
    }

    @Override
    public List<BranchDto> findInfoByBrandCode(String brandCode) {
        return toDto(repo.findByBrandCode(brandCode));
    }

}
