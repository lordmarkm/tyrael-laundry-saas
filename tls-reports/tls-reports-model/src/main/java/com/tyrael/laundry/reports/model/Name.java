package com.tyrael.laundry.reports.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Mark Baldwin B. Martinez on Mar 9, 2016
 *
 */
@Embeddable
public class Name {

    @Column(name = "given_name")
    protected String givenName;

    @Column(name = "middle_name")
    protected String middleName;

    @Column(name = "surname")
    protected String surname;

}
