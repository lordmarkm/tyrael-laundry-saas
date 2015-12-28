package com.tyrael.laundry.model.inventory;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tyrael.laundry.commons.model.BaseNamedEntity;
import com.tyrael.laundry.model.branch.Brand;

/**
 * 
 * @author Mark Martinez, create Dec 26, 2015
 *
 */
@Entity(name = "inv_item_type")
public class InventoryItemType extends BaseNamedEntity {

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Column(name = "default_buy_price")
    private BigDecimal defaultBuyingPrice;

    @Column(name = "default_sell_price")
    private BigDecimal defaultSellingPrice;

    @Column(name = "default_supplier_name")
    private String defaultSupplierName;

    @Column(name = "default_for_sale")
    private boolean forSale;

    @Column(name = "uom")
    private String uom;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public BigDecimal getDefaultBuyingPrice() {
        return defaultBuyingPrice;
    }

    public void setDefaultBuyingPrice(BigDecimal defaultBuyingPrice) {
        this.defaultBuyingPrice = defaultBuyingPrice;
    }

    public BigDecimal getDefaultSellingPrice() {
        return defaultSellingPrice;
    }

    public void setDefaultSellingPrice(BigDecimal defaultSellingPrice) {
        this.defaultSellingPrice = defaultSellingPrice;
    }

    public String getDefaultSupplierName() {
        return defaultSupplierName;
    }

    public void setDefaultSupplierName(String defaultSupplierName) {
        this.defaultSupplierName = defaultSupplierName;
    }

    public boolean isForSale() {
        return forSale;
    }

    public void setForSale(boolean forSale) {
        this.forSale = forSale;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
