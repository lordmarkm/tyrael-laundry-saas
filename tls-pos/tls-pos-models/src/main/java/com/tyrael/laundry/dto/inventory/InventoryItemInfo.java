package com.tyrael.laundry.dto.inventory;

import java.math.BigDecimal;

import com.tyrael.laundry.commons.dto.BaseDto;
import com.tyrael.laundry.commons.util.SlugUtil;

/**
 * 
 * @author Mark Martinez, create Dec 28, 2015
 *
 */
public class InventoryItemInfo extends BaseDto {

    private String brandName;
    private String brandCode;
    private String branchName;
    private String branchCode;
    private String inventoryItemTypeCode;
    private String inventoryItemTypeName;
    private String uom;
    private String code;
    private BigDecimal quantity;
    private BigDecimal buyingPrice;
    private BigDecimal sellingPrice;
    private String supplierName;
    private boolean forSale;

    public String getSlug() {
        return SlugUtil.toSlug(inventoryItemTypeName, code);
    }

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
    public String getBrandName() {
        return brandName;
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    public String getBrandCode() {
        return brandCode;
    }
    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }
    public String getBranchName() {
        return branchName;
    }
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

}
