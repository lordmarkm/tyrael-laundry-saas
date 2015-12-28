package com.tyrael.laundry.model.inventory;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import com.tyrael.laundry.commons.model.BaseEntity;
import com.tyrael.laundry.model.branch.Branch;

/**
 * 
 * @author Mark Martinez, create Dec 26, 2015
 *
 */
@Entity(name = "inventory_item")
public class InventoryItem extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "item_type_id")
    private InventoryItemType itemType;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @Column(name = "qty")
    private BigDecimal quantity;

    @Column(name = "buy_price")
    private BigDecimal buyingPrice;

    @Column(name = "sell_price")
    private BigDecimal sellingPrice;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "for_sale")
    @Type(type = "yes_no")
    private boolean forSale;

    public InventoryItemType getItemType() {
        return itemType;
    }

    public void setItemType(InventoryItemType itemType) {
        this.itemType = itemType;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(BigDecimal buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public boolean isForSale() {
        return forSale;
    }

    public void setForSale(boolean forSale) {
        this.forSale = forSale;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

}
