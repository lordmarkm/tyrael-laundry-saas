package com.tyrael.laundry.model.customer;

import javax.persistence.Embedded;
import javax.persistence.Entity;

import com.tyrael.laundry.commons.model.BaseEntity;

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

}
