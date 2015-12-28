package com.tyrael.laundry.pos.service.custom.impl;

import static com.tyrael.laundry.model.inventory.QInventoryItem.inventoryItem;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.mysema.query.types.expr.BooleanExpression;
import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustomImpl;
import com.tyrael.laundry.commons.util.AuthenticationUtil;
import com.tyrael.laundry.core.service.BranchService;
import com.tyrael.laundry.core.service.BrandService;
import com.tyrael.laundry.dto.inventory.InventoryItemInfo;
import com.tyrael.laundry.model.branch.Branch;
import com.tyrael.laundry.model.branch.Brand;
import com.tyrael.laundry.model.inventory.InventoryItem;
import com.tyrael.laundry.pos.service.InventoryItemService;
import com.tyrael.laundry.pos.service.custom.InventoryItemServiceCustom;
/**
 * 
 * @author Mark Martinez, create Dec 26, 2015
 *
 */
@Transactional
public class InventoryItemServiceCustomImpl
    extends TyraelJpaServiceCustomImpl<InventoryItem, InventoryItemInfo, InventoryItemService> 
    implements InventoryItemServiceCustom {

    @Autowired
    private BrandService brandService;

    @Autowired
    private BranchService branchService;

    private BooleanExpression addBrandFilter(final BooleanExpression predicate) {
        if (AuthenticationUtil.isAuthorized(AuthenticationUtil.ROLE_ADMIN)) {
            return predicate;
        } else {
            List<Brand> brands = brandService.findByUserUsername(AuthenticationUtil.getLoggedInUsername());
            return predicate.and(inventoryItem.branch.brand.in(brands));
        }
    }

    @Override
    public PageInfo<InventoryItemInfo> pageInfo(Pageable page) {
        BooleanExpression query = inventoryItem.deleted.isFalse();
        query = addBrandFilter(query);
        return super.pageInfo(query, page);
    }

    @Override
    public InventoryItemInfo saveInfo(InventoryItemInfo dto) {
        if (null == dto.getCode()) {
            //CREATE operation, Generate random candidate brand codes until we find a unique one
            InventoryItem existing = null;
            String candidateCode;
            do {
                candidateCode = RandomStringUtils.randomAlphanumeric(5).toLowerCase();
                existing = repo.findByCode(candidateCode);
            } while (null != existing);
    
            //Use the generated code as default customer code
            dto.setCode(candidateCode);

            InventoryItem entity = toEntity(dto);

            //Assign the customer to the set brand
            Branch branch = branchService.findByCode(dto.getBranchCode());
            Preconditions.checkNotNull(branch);
            entity.setBranch(branch);

            return toDto(repo.save(entity));
        } else {
            //UPDATE operation
            InventoryItem existing = repo.findByCode(dto.getCode());
            Preconditions.checkNotNull(existing);
            mapper.map(dto, existing);

            //Assign the customer to the set brand
            Branch branch = branchService.findByCode(dto.getBranchCode());
            Preconditions.checkNotNull(branch);
            existing.setBranch(branch);

            return toDto(existing);
        }
    }

    @Override
    public InventoryItemInfo findInfoByCode(String invItemCode) {
        return toDto(repo.findByCode(invItemCode));
    }

}
