package com.tyrael.laundry.model.event;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tyrael.laundry.commons.model.BaseEntity;
import com.tyrael.laundry.model.branch.Branch;
import com.tyrael.laundry.model.branch.Brand;

/**
 *
 * @author Mark Baldwin B. Martinez on Feb 24, 2016
 *
 */
@Entity(name = "event_log")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "event_type")
public abstract class TlsEvent extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "brand_id")
    protected Brand brand;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    protected Branch branch;

    @Column(name = "event_type", insertable = false, updatable = false)
    protected String eventType;

    @Column(name = "event_msg")
    protected String message;

    public TlsEvent() {
        //No-arg constructor
    }

    public TlsEvent(String message) {
        this.message = message;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
