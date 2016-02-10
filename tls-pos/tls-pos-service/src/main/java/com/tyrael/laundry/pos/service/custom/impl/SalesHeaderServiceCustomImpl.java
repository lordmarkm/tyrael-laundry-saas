package com.tyrael.laundry.pos.service.custom.impl;

import com.tyrael.laundry.commons.service.TyraelJpaServiceCustomImpl;
import com.tyrael.laundry.dto.inventory.SalesHeaderInfo;
import com.tyrael.laundry.model.inventory.SalesHeader;
import com.tyrael.laundry.pos.service.SalesHeaderService;
import com.tyrael.laundry.pos.service.custom.SalesHeaderServiceCustom;

/**
 * 
 * @author Mark Martinez, create Feb 10, 2016
 *
 */
public class SalesHeaderServiceCustomImpl
    extends TyraelJpaServiceCustomImpl<SalesHeader, SalesHeaderInfo, SalesHeaderService>
    implements SalesHeaderServiceCustom {

    @Override
    public SalesHeaderInfo makeSale(SalesHeaderInfo salesHeader) {
        // TODO Auto-generated method stub
        return null;
    }

}
