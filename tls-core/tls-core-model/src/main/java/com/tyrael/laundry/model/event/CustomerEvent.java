package com.tyrael.laundry.model.event;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tyrael.laundry.model.customer.Customer;

@Entity(name = "event_customer")
@DiscriminatorValue("CUSTOMER")
public class CustomerEvent extends TlsEvent {

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public CustomerEvent() {
        //No-arg constructor
    }

    public CustomerEvent(String message, Customer customer) {
        super(message);
        this.customer = customer;
        this.brand = customer.getBrand();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
