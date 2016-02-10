package com.tyrael.laundry.pos.service;

import com.tyrael.laundry.commons.service.TyraelJpaService;
import com.tyrael.laundry.model.inventory.SalesHeader;
import com.tyrael.laundry.pos.service.custom.SalesHeaderServiceCustom;

/**
 * 
 * @author Mark Martinez, create Feb 10, 2016
 *
 */
public interface SalesHeaderService extends SalesHeaderServiceCustom,
    TyraelJpaService<SalesHeader> {

}
