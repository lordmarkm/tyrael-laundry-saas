package com.tyrael.laundry.pos.service.custom.impl;

import static com.tyrael.laundry.model.inventory.QInventoryItemType.inventoryItemType;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.mysema.query.types.expr.BooleanExpression;
import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.dto.inventory.InventoryItemInfo;
import com.tyrael.laundry.commons.dto.inventory.InventoryItemTypeInfo;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustomImpl;
import com.tyrael.laundry.commons.util.AuthenticationUtil;
import com.tyrael.laundry.core.service.BranchService;
import com.tyrael.laundry.core.service.BrandService;
import com.tyrael.laundry.core.service.rql.RsqlParserVisitor;
import com.tyrael.laundry.model.branch.Branch;
import com.tyrael.laundry.model.branch.Brand;
import com.tyrael.laundry.model.inventory.InventoryItemType;
import com.tyrael.laundry.pos.service.InventoryItemTypeService;
import com.tyrael.laundry.pos.service.custom.InventoryItemTypeServiceCustom;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
/**
 * 
 * @author Mark Martinez, create Dec 26, 2015
 *
 */
@Transactional
public class InventoryItemTypeServiceCustomImpl
    extends TyraelJpaServiceCustomImpl<InventoryItemType, InventoryItemTypeInfo, InventoryItemTypeService> 
    implements InventoryItemTypeServiceCustom {

    private static final Logger LOG = LoggerFactory.getLogger(InventoryItemTypeServiceCustomImpl.class);

    @Autowired
    private BrandService brandService;

    @Autowired
    private BranchService branchService;

    private BooleanExpression addBrandFilter(final BooleanExpression predicate) {
        if (AuthenticationUtil.isAuthorized(AuthenticationUtil.ROLE_ADMIN)) {
            return predicate;
        } else {
            List<Brand> brands = brandService.findByUserUsername(AuthenticationUtil.getLoggedInUsername());
            return predicate.and(inventoryItemType.brand.in(brands));
        }
    }

    @Override
    public PageInfo<InventoryItemTypeInfo> rqlSearch(String term, Pageable pageRequest) {
        LOG.debug("Performing paginated rql search. term={}, page = {}", term, pageRequest);

        BooleanExpression predicate = inventoryItemType.deleted.isFalse();;
        if (!StringUtils.isBlank(term)) {
            try {
                Node rootNode = new RSQLParser().parse(term);
                RsqlParserVisitor visitor = new RsqlParserVisitor();
                predicate = rootNode.accept(visitor, FIELD_MAPPING);
            } catch (Exception e) {
                LOG.error("Error parsing or interpreting rql term. term={}, error={}", term, e.getMessage());
                return PageInfo.blank();
            }
        }

        predicate = addBrandFilter(predicate);

        return super.pageInfo(predicate, pageRequest);
    }

    @Override
    public PageInfo<InventoryItemTypeInfo> pageInfo(Pageable page) {
        BooleanExpression query = inventoryItemType.deleted.isFalse();
        query = addBrandFilter(query);
        return super.pageInfo(query, page);
    }

    @Override
    public InventoryItemTypeInfo saveInfo(InventoryItemTypeInfo dto) {
        if (null == dto.getCode()) {
            //CREATE operation, Generate random candidate brand codes until we find a unique one
            InventoryItemType existing = null;
            String candidateCode;
            do {
                candidateCode = RandomStringUtils.randomAlphanumeric(5).toLowerCase();
                existing = repo.findByCode(candidateCode);
            } while (null != existing);
    
            //Use the generated code as default type code
            dto.setCode(candidateCode);

            InventoryItemType entity = toEntity(dto);

            //Assign the type to the set brand
            Brand brand = brandService.findByCode(dto.getBrandCode());
            Preconditions.checkNotNull(brand);
            entity.setBrand(brand);

            return toDto(repo.save(entity));
        } else {
            //UPDATE operation
            InventoryItemType existing = repo.findByCode(dto.getCode());
            Preconditions.checkNotNull(existing);
            mapper.map(dto, existing);

            //Assign the customer to the set brand
            Brand brand = brandService.findByCode(dto.getBrandCode());
            Preconditions.checkNotNull(brand);
            existing.setBrand(brand);

            return toDto(existing);
        }
    }

    @Override
    public InventoryItemTypeInfo findInfoByCode(String invItemTypeCode) {
        return toDto(repo.findByCode(invItemTypeCode));
    }

    @Override
    public List<InventoryItemTypeInfo> findInfoByBrandCode(String brandCode) {
        return toDto(repo.findByBrandCode(brandCode));
    }

    @Override
    public List<InventoryItemTypeInfo> findInfoByBranchCode(String branchCode) {
        Branch branch = branchService.findByCode(branchCode);
        Preconditions.checkNotNull(branch);
        return findInfoByBrandCode(branch.getBrand().getCode());
    }

}
