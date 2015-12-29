package com.tyrael.laundry.commons.dto;

import java.math.BigDecimal;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tyrael.laundry.commons.dto.BaseNamedDto;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
public class BranchDto extends BaseNamedDto {

    /**
     * @JsonIgnore here and on getter, @JsonProperty on setter in order to use property
     * on deserialize only.
     */
    @JsonIgnore
    private BrandDto brand;
    private String brandName;
    private String brandCode;

    private BigDecimal minimumJobOrderAmount;
    private String code;
    private String address;
    private String contactNumber;
    private String email;

    @Override
    protected ToStringCreator toStringCreator() {
        return new ToStringCreator(this)
                .append("brand", brand)
                .append("branchCode", code)
                .append("address", address)
                .append("contact num", contactNumber)
                .append("email", email)
                .append("minimum", minimumJobOrderAmount);
    }

    @JsonIgnore
    public BrandDto getBrand() {
        return brand;
    }

    @JsonProperty
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

}
