package com.tyrael.laundry.pos.service.custom;

import java.math.BigDecimal;

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

    InventoryItemInfo findInfoByCode(String invItemCode);
    InventoryItemInfo restock(String invItemCode, BigDecimal quantity);
    InventoryItemInfo consume(String invItemCode, BigDecimal quantity);

}
