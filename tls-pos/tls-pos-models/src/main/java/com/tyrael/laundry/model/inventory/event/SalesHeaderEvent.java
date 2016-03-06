package com.tyrael.laundry.model.inventory.event;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tyrael.laundry.model.event.TlsEvent;
import com.tyrael.laundry.model.inventory.SalesHeader;

@Entity(name = "event_sales_header")
@DiscriminatorValue("SALES_HEADER")
public class SalesHeaderEvent extends TlsEvent {

    @ManyToOne
    @JoinColumn(name = "sales_header_id")
    private SalesHeader salesHeader;

    public SalesHeaderEvent() {
        //No-arg constructor
    }

    public SalesHeaderEvent(String message, SalesHeader salesHeader) {
        super(message);
        this.salesHeader = salesHeader;
        this.branch = salesHeader.getBranch();
        this.brand = salesHeader.getBranch().getBrand();
    }

    public SalesHeader getSalesHeader() {
        return salesHeader;
    }

    public void setSalesHeader(SalesHeader salesHeader) {
        this.salesHeader = salesHeader;
    }

}
