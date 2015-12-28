package com.tyrael.laundry.pos.service.custom.impl;

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
import com.tyrael.laundry.core.service.BrandService;
import com.tyrael.laundry.dto.inventory.InventoryItemTypeInfo;
import com.tyrael.laundry.model.branch.Brand;
import com.tyrael.laundry.model.inventory.InventoryItemType;
import com.tyrael.laundry.pos.service.InventoryItemTypeService;
import com.tyrael.laundry.pos.service.custom.InventoryItemTypeServiceCustom;
import static com.tyrael.laundry.model.inventory.QInventoryItemType.inventoryItemType;
/**
 * 
 * @author Mark Martinez, create Dec 26, 2015
 *
 */
@Transactional
public class InventoryItemTypeServiceCustomImpl
    extends TyraelJpaServiceCustomImpl<InventoryItemType, InventoryItemTypeInfo, InventoryItemTypeService> 
    implements InventoryItemTypeServiceCustom {

    @Autowired
    private BrandService brandService;

    private BooleanExpression addBrandFilter(final BooleanExpression predicate) {
        if (AuthenticationUtil.isAuthorized(AuthenticationUtil.ROLE_ADMIN)) {
            return predicate;
        } else {
            List<Brand> brands = brandService.findByUserUsername(AuthenticationUtil.getLoggedInUsername());
            return predicate.and(inventoryItemType.brand.in(brands));
        }
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
    
            //Use the generated code as default customer code
            dto.setCode(candidateCode);

            InventoryItemType entity = toEntity(dto);

            //Assign the customer to the set brand
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

}
