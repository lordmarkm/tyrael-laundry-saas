package com.tyrael.laundry.reports.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Mark Baldwin B. Martinez on Mar 9, 2016
 *
 */
@Embeddable
public class ContactDetails {

    @Column(name = "cellphone")
    protected String cellphone;

    @Column(name = "landline")
    protected String landline;

    @Column(name = "email")
    protected String email;

}
