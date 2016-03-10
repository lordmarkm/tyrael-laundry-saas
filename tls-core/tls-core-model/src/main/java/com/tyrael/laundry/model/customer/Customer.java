package com.tyrael.laundry.model.customer;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tyrael.laundry.commons.model.BaseEntity;
import com.tyrael.laundry.model.branch.Brand;

/**
 * @author mbmartinez
 */
@Entity(name = "customer")
public class Customer extends BaseEntity {

    @Embedded
    private Name name;

    @Embedded
    private Address address;

    @Embedded
    private ContactDetails contactDetails;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Column(name = "balance")
    private BigDecimal balance;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
