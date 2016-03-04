package com.tyrael.laundry.commons.dto.inventory;

import java.math.BigDecimal;

import org.springframework.core.style.ToStringCreator;

import com.tyrael.laundry.commons.dto.BaseNamedDto;
import com.tyrael.laundry.commons.util.SlugUtil;

/**
 * 
 * @author Mark Martinez, create Dec 26, 2015
 *
 */
public class InventoryItemTypeInfo extends BaseNamedDto {

    private String code;
    private BigDecimal defaultBuyingPrice;
    private BigDecimal defaultSellingPrice;
    private String defaultSupplierName;
    private boolean forSale;
    private String uom;

    /**
     * The code and name of the brand the iit is assigned to
     */
    private String brandCode;
    private String brandName;

    @Override
    protected ToStringCreator toStringCreator() {
        return super.toStringCreator()
            .append("name", name)
            .append("desc", description)
            .append("defaultBuyingPrice", defaultBuyingPrice)
            .append("defaultSellingPrice", defaultSellingPrice)
            .append("defaultSupplierName", defaultSupplierName)
            .append("forSale", forSale)
            .append("uom", uom)
            .append("code", code)
            .append("brandCode", brandCode)
            .append("brandName", brandName);
    }

    public String getSlug() {
        return SlugUtil.toSlug(name);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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

}
