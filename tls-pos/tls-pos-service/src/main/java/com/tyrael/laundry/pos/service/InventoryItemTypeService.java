package com.tyrael.laundry.pos.service;

import com.tyrael.laundry.commons.service.TyraelJpaService;
import com.tyrael.laundry.model.inventory.InventoryItemType;
import com.tyrael.laundry.pos.service.custom.InventoryItemTypeServiceCustom;

/**
 * 
 * @author Mark Martinez, create Dec 26, 2015
 *
 */
public interface InventoryItemTypeService extends InventoryItemTypeServiceCustom,
    TyraelJpaService<InventoryItemType> {

    InventoryItemType findByCode(String candidateCode);

}
