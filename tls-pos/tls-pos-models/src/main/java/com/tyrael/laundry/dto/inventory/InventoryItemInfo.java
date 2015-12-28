package com.tyrael.laundry.dto.inventory;

import java.math.BigDecimal;

import com.tyrael.laundry.commons.dto.BaseDto;

/**
 * 
 * @author Mark Martinez, create Dec 28, 2015
 *
 */
public class InventoryItemInfo extends BaseDto {

    private String inventoryItemTypeCode;
    private String inventoryItemTypeName;
    private String branchCode;
    private String code;
    private BigDecimal quantity;
    private BigDecimal buyingPrice;
    private BigDecimal sellingPrice;
    private String supplierName;
    private boolean forSale;

    public String getInventoryItemTypeCode() {
        return inventoryItemTypeCode;
    }
    public void setInventoryItemTypeCode(String inventoryItemTypeCode) {
        this.inventoryItemTypeCode = inventoryItemTypeCode;
    }
    public String getInventoryItemTypeName() {
        return inventoryItemTypeName;
    }
    public void setInventoryItemTypeName(String inventoryItemTypeName) {
        this.inventoryItemTypeName = inventoryItemTypeName;
    }
    public String getBranchCode() {
        return branchCode;
    }
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
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

}
