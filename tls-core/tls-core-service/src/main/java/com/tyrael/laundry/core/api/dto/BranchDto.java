package com.tyrael.laundry.core.api.dto;

import java.math.BigDecimal;

import org.springframework.core.style.ToStringCreator;

import com.tyrael.laundry.commons.dto.BaseNamedDto;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
public class BranchDto extends BaseNamedDto {

    private BrandDto brand;
    private BigDecimal minimumJobOrderAmount;
    private String code;

    @Override
    protected ToStringCreator toStringCreator() {
        return new ToStringCreator(this)
                .append("brand", brand)
                .append("branchCode", code)
                .append("minimum", minimumJobOrderAmount);
    }

    public BrandDto getBrand() {
        return brand;
    }

    public void setBrand(BrandDto brand) {
        this.brand = brand;
    }

    public BigDecimal getMinimumJobOrderAmount() {
        return minimumJobOrderAmount;
    }

    public void setMinimumJobOrderAmount(BigDecimal minimumJobOrderAmount) {
        this.minimumJobOrderAmount = minimumJobOrderAmount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
