package com.tyrael.laundry.reports.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import com.tyrael.laundry.reports.base.BaseMeta;

/**
 *
 * @author Mark Baldwin B. Martinez on Mar 9, 2016
 *
 */
@Entity(name = "customer_dimension")
public class CustomerDimension extends BaseMeta {

    @Embedded
    protected Name name;

    @Embedded
    protected Address address;

    @Embedded
    protected ContactDetails contactDetails;

    @Column(name = "code", unique = true)
    protected String code;

}
