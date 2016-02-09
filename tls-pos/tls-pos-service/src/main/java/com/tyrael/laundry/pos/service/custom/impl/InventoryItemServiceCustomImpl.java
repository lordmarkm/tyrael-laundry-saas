package com.tyrael.laundry.pos.service.custom.impl;

import static com.tyrael.laundry.model.inventory.QInventoryItem.inventoryItem;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.mysema.query.types.expr.BooleanExpression;
import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustomImpl;
import com.tyrael.laundry.commons.util.AuthenticationUtil;
import com.tyrael.laundry.commons.util.MathUtil;
import com.tyrael.laundry.core.service.BranchService;
import com.tyrael.laundry.core.service.BrandService;
import com.tyrael.laundry.dto.inventory.InventoryItemInfo;
import com.tyrael.laundry.model.branch.Branch;
import com.tyrael.laundry.model.branch.Brand;
import com.tyrael.laundry.model.inventory.InventoryItem;
import com.tyrael.laundry.model.inventory.InventoryItemType;
import com.tyrael.laundry.pos.service.InventoryItemService;
import com.tyrael.laundry.pos.service.InventoryItemTypeService;
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

    private static Logger LOG = LoggerFactory.getLogger(InventoryItemServiceCustomImpl.class);
    private static final BigDecimal STEP = new BigDecimal(0.01);

    @Autowired
    private BrandService brandService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private InventoryItemTypeService inventoryItemTypeService;

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
            //CREATE operation, Generate random candidate item codes until we find a unique one
            InventoryItem existing = null;
            String candidateCode;
            do {
                candidateCode = RandomStringUtils.randomAlphanumeric(5).toLowerCase();
                existing = repo.findByCode(candidateCode);
            } while (null != existing);
    
            //Use the generated code as default customer code
            dto.setCode(candidateCode);

            InventoryItem entity = toEntity(dto);

            //Assign the inventory item to the set type
            InventoryItemType inventoryItemType = inventoryItemTypeService.findByCode(dto.getInventoryItemTypeCode());
            Preconditions.checkNotNull(inventoryItemType);
            entity.setItemType(inventoryItemType);

            //Assign the inventory item to the set branch
            Branch branch = branchService.findByCode(dto.getBranchCode());
            Preconditions.checkNotNull(branch);
            entity.setBranch(branch);

            //New inventory items have 0 quantity
            entity.setQuantity(BigDecimal.ZERO);

            return toDto(repo.save(entity));
        } else {
            //UPDATE operation
            InventoryItem existing = repo.findByCode(dto.getCode());
            Preconditions.checkNotNull(existing);
            mapper.map(dto, existing);

            //Assign the item to the set branch
            Branch branch = branchService.findByCode(dto.getBranchCode());
            Preconditions.checkNotNull(branch);
            existing.setBranch(branch);

            //Assign the inventory item to the set type
            InventoryItemType inventoryItemType = inventoryItemTypeService.findByCode(dto.getInventoryItemTypeCode());
            Preconditions.checkNotNull(inventoryItemType);
            existing.setItemType(inventoryItemType);

            return toDto(existing);
        }
    }

    @Override
    public InventoryItemInfo findInfoByCode(String invItemCode) {
        return toDto(repo.findByCode(invItemCode));
    }

    @Override
    public InventoryItemInfo restock(String invItemCode, BigDecimal quantity) {
        //Can't restock less than minimum
        Preconditions.checkArgument(MathUtil.gt(quantity, STEP));

        //Item is not null
        InventoryItem invItem = repo.findByCode(invItemCode);
        Preconditions.checkNotNull(invItem);

        invItem.setQuantity(invItem.getQuantity().add(quantity));

        return toDto(invItem);
    }

    @Override
    public InventoryItemInfo consume(String invItemCode, BigDecimal quantity) {
        //Can't restock less than minimum
        Preconditions.checkArgument(MathUtil.gt(quantity, STEP));

        //Item is not null
        InventoryItem invItem = repo.findByCode(invItemCode);
        Preconditions.checkNotNull(invItem);

        //Can't consume more than current qty
        Preconditions.checkArgument(MathUtil.gte(invItem.getQuantity(), quantity));

        invItem.setQuantity(invItem.getQuantity().subtract(quantity));

        return toDto(invItem);
    }
}
