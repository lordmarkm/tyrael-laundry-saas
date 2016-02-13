package com.tyrael.laundry.pos.service.custom.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Preconditions;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustomImpl;
import com.tyrael.laundry.dto.inventory.SalesHeaderInfo;
import com.tyrael.laundry.model.inventory.InventoryItem;
import com.tyrael.laundry.model.inventory.SalesHeader;
import com.tyrael.laundry.model.inventory.SalesItem;
import com.tyrael.laundry.pos.service.InventoryItemService;
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

    @Autowired
    private InventoryItemService inventoryItemService;

    @Override
    public SalesHeaderInfo makeSale(SalesHeaderInfo salesHeaderInfo) {
        
        SalesHeader salesHeader = toEntity(salesHeaderInfo);
        setBranch(salesHeader);
        doCalculations(salesHeader);

        return toDto(repo.save(salesHeader));
    }

    private void setBranch(SalesHeader salesHeader) {
        //This method assumes that a sales header can only be from a single branch
        SalesItem firstItem = salesHeader.getItems().get(0);
        InventoryItem invItem = inventoryItemService.findOne(firstItem.getInventoryItem().getId());
        Preconditions.checkNotNull(invItem);

        salesHeader.setBranch(invItem.getBranch());
    }

    private void doCalculations(SalesHeader salesHeader) {
        BigDecimal totalAmountPaid = BigDecimal.ZERO;
        for (SalesItem salesItem : salesHeader.getItems()) {
            //Actual calculations
            BigDecimal sellingPrice = salesItem.getInventoryItem().getSellingPrice();
            BigDecimal salesItemAmount = sellingPrice.multiply(salesItem.getQuantity());
            salesItem.setAmount(salesItemAmount);
            totalAmountPaid = totalAmountPaid.add(salesItemAmount);

            //Some housekeeping
            salesItem.setSalesHeader(salesHeader);
        }

        salesHeader.setTotalAmountPaid(totalAmountPaid);
    }

}
