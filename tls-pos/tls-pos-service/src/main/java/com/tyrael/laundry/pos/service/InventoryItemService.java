package com.tyrael.laundry.pos.service;

import com.tyrael.laundry.commons.service.TyraelJpaService;
import com.tyrael.laundry.model.inventory.InventoryItem;
import com.tyrael.laundry.pos.service.custom.InventoryItemServiceCustom;

/**
 * 
 * @author Mark Martinez, create Dec 26, 2015
 *
 */
public interface InventoryItemService extends InventoryItemServiceCustom,
    TyraelJpaService<InventoryItem> {

    InventoryItem findByCode(String code);

}
