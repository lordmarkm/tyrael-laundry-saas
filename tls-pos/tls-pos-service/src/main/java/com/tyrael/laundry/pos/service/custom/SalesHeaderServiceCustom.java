package com.tyrael.laundry.pos.service.custom;

import com.tyrael.laundry.commons.service.TyraelJpaServiceCustom;
import com.tyrael.laundry.dto.inventory.SalesHeaderInfo;
import com.tyrael.laundry.model.inventory.SalesHeader;

/**
 * 
 * @author Mark Martinez, create Feb 10, 2016
 *
 */
public interface SalesHeaderServiceCustom 
    extends TyraelJpaServiceCustom<SalesHeader, SalesHeaderInfo> {

    SalesHeaderInfo makeSale(SalesHeaderInfo salesHeader);

}