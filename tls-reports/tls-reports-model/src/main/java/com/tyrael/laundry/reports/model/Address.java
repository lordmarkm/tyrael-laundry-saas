package com.tyrael.laundry.reports.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Mark Baldwin B. Martinez on Mar 9, 2016
 *
 */
@Embeddable
public class Address {

    @Column(name = "addr_line1")
    protected String addressLine1;

    @Column(name = "addr_line2")
    protected String addressLine2;

    @Column(name = "addr_city")
    protected String city;

    @Column(name = "addr_provice")
    protected String province;

    @Column(name = "addr_zip")
    protected String zip;

}
