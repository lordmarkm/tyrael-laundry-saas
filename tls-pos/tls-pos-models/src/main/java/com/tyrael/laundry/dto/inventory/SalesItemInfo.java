package com.tyrael.laundry.dto.inventory;

import java.math.BigDecimal;

import com.tyrael.laundry.commons.dto.BaseDto;

/**
 * 
 * @author Mark Martinez, created Feb 13, 2016
 *
 */
public class SalesItemInfo extends BaseDto {

    private InventoryItemInfo inventoryItem;
    private BigDecimal quantity;
    private BigDecimal amount;
    private String inventoryItemName;

    public InventoryItemInfo getInventoryItem() {
        return inventoryItem;
    }
    public void setInventoryItem(InventoryItemInfo inventoryItem) {
        this.inventoryItem = inventoryItem;
    }
    public BigDecimal getQuantity() {
        return quantity;
    }
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public String getInventoryItemName() {
        return inventoryItemName;
    }
    public void setInventoryItemName(String inventoryItemName) {
        this.inventoryItemName = inventoryItemName;
    }

}
