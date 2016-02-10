package com.tyrael.laundry.model.inventory;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tyrael.laundry.commons.model.BaseEntity;

/**
 * 
 * @author Mark Martinez, create Feb 10, 2016
 *
 */
@Entity(name = "sales_item")
public class SalesItem extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "sales_header_id")
    private SalesHeader salesHeader;

    @ManyToOne
    @JoinColumn(name = "inventory_item_id")
    private InventoryItem inventoryItem;

    @Column(name = "qty", nullable = false)
    private BigDecimal quantity;

    @Column(name = "amt", nullable = false)
    private BigDecimal amount;

    public SalesHeader getSalesHeader() {
        return salesHeader;
    }

    public void setSalesHeader(SalesHeader salesHeader) {
        this.salesHeader = salesHeader;
    }

    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(InventoryItem inventoryItem) {
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

}
