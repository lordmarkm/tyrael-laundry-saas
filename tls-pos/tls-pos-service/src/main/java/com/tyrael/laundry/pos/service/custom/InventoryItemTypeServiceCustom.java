package com.tyrael.laundry.pos.service.custom;

import static com.tyrael.laundry.model.inventory.QInventoryItemType.inventoryItemType;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.google.common.collect.ImmutableMap;
import com.mysema.query.types.Path;
import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustom;
import com.tyrael.laundry.dto.inventory.InventoryItemTypeInfo;
import com.tyrael.laundry.model.inventory.InventoryItemType;

/**
 * 
 * @author Mark Martinez, create Dec 26, 2015
 *
 */
public interface InventoryItemTypeServiceCustom 
    extends TyraelJpaServiceCustom<InventoryItemType, InventoryItemTypeInfo> {

    ImmutableMap<String, Path<?>> FIELD_MAPPING = ImmutableMap.<String, Path<?>>builder()
            .put("id", inventoryItemType.id)
            .put("deleted", inventoryItemType.deleted)
            .put("dateCreated", inventoryItemType.dateCreated)
            .put("brandCode", inventoryItemType.brand.code)
            .build();

    InventoryItemTypeInfo findInfoByCode(String invItemTypeCode);
    List<InventoryItemTypeInfo> findInfoByBrandCode(String brandCode);
    List<InventoryItemTypeInfo> findInfoByBranchCode(String branchCode);
    PageInfo<InventoryItemTypeInfo> rqlSearch(String term, Pageable pageRequest);

}
