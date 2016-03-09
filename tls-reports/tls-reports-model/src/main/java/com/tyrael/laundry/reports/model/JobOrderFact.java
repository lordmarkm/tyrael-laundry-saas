package com.tyrael.laundry.reports.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.tyrael.laundry.reference.JobOrderStatus;
import com.tyrael.laundry.reports.base.BaseMeta;

/**
 *
 * @author Mark Baldwin B. Martinez on Mar 9, 2016
 *
 */
@Entity(name = "job_order_fact")
public class JobOrderFact extends BaseMeta {

    @Column(name = "tracking_no")
    protected String trackingNo;

    @ManyToOne
    @JoinColumn(name = "customer_dim_id")
    protected CustomerDimension customer;

    @ManyToOne
    @JoinColumn(name = "branch_dim_id")
    protected BranchDimension branch;

    @Column(name = "date_received", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    protected DateTime dateReceived;

    @Column(name = "date_due")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    protected DateTime dateDue;

    @Column(name = "date_completed")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    protected DateTime dateCompleted;

    @Column(name = "date_claimed")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    protected DateTime dateClaimed;

    @Column(name = "total_amt", nullable = false)
    protected BigDecimal totalAmount;

    @Column(name = "total_amt_paid")
    protected BigDecimal totalAmountPaid;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    protected JobOrderStatus status;

}
