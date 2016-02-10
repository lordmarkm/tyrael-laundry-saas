package com.tyrael.laundry.pos.service.custom;

import java.util.List;

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

    InventoryItemTypeInfo findInfoByCode(String invItemTypeCode);
    List<InventoryItemTypeInfo> findInfoByBrandCode(String brandCode);
    List<InventoryItemTypeInfo> findInfoByBranchCode(String branchCode);

}
