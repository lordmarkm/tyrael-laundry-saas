package com.tyrael.laundry.reports.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.tyrael.laundry.reports.base.BaseMeta;

/**
 *
 * @author Mark Baldwin B. Martinez on Mar 9, 2016
 *
 */
@Entity(name = "branch_dimension")
public class BranchDimension extends BaseMeta {

    @Column(name = "code", unique = true)
    protected String code;

    @Column(name = "address")
    protected String address;

    @Column(name = "contact_number")
    protected String contactNumber;

    @Column(name = "email")
    protected String email;

    @Column(name = "name")
    protected String name;

    @Column(name = "job_order_minimum")
    protected BigDecimal minimumJobOrderAmount = BigDecimal.ZERO;

}
