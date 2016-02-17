package com.tyrael.laundry.pos.service.custom;

import static com.tyrael.laundry.model.inventory.QInventoryItem.inventoryItem;

import java.math.BigDecimal;

import org.springframework.data.domain.Pageable;

import com.google.common.collect.ImmutableMap;
import com.mysema.query.types.Path;
import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustom;
import com.tyrael.laundry.dto.inventory.InventoryItemInfo;
import com.tyrael.laundry.model.inventory.InventoryItem;

/**
 * 
 * @author Mark Martinez, create Dec 26, 2015
 *
 */
public interface InventoryItemServiceCustom 
    extends TyraelJpaServiceCustom<InventoryItem, InventoryItemInfo>{

    ImmutableMap<String, Path<?>> FIELD_MAPPING = ImmutableMap.<String, Path<?>>builder()
            .put("id", inventoryItem.id)
            .put("deleted", inventoryItem.deleted)
            .put("dateCreated", inventoryItem.dateCreated)
            .put("branchCode", inventoryItem.branch.code)
            .build();

    InventoryItemInfo findInfoByCode(String invItemCode);
    InventoryItemInfo restock(String invItemCode, BigDecimal quantity);
    InventoryItemInfo consume(String invItemCode, BigDecimal quantity);
    PageInfo<InventoryItemInfo> rqlSearch(String term, Pageable pageRequest);

}
