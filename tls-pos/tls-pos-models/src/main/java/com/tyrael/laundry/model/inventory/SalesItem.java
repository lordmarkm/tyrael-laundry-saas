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

    /**
     * Item name, price, and uom at the time of the sale since inv items can be edited
     */
    @Column(name = "inv_item_name")
    private String inventoryItemName;

    @Column(name = "price_per_item")
    private BigDecimal pricePerItem;

    @Column(name = "uom")
    private String uom;

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

    public String getInventoryItemName() {
        return inventoryItemName;
    }

    public void setInventoryItemName(String inventoryItemName) {
        this.inventoryItemName = inventoryItemName;
    }

    public BigDecimal getPricePerItem() {
        return pricePerItem;
    }

    public void setPricePerItem(BigDecimal pricePerItem) {
        this.pricePerItem = pricePerItem;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

}
