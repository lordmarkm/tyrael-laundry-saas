package com.tyrael.laundry.model.branch;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tyrael.laundry.commons.model.BaseNamedEntity;

/**
 * 
 * @author Mark Martinez, created Nov 28, 2015
 *
 */
@Entity(name = "branch")
public class Branch extends BaseNamedEntity {

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Column(name = "branch_code", unique = true, nullable = false)
    private String code;

    @Column(name = "address")
    private String address;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "email")
    private String email;

    /**
     * Job orders that don't reach this amount will still be charged this
     * amount.
     */
    @Column(name = "job_order_minimum")
    private BigDecimal minimumJobOrderAmount = BigDecimal.ZERO;

    public BigDecimal getMinimumJobOrderAmount() {
        return minimumJobOrderAmount;
    }

    public void setMinimumJobOrderAmount(BigDecimal minimumJobOrderAmount) {
        this.minimumJobOrderAmount = minimumJobOrderAmount;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
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

}
