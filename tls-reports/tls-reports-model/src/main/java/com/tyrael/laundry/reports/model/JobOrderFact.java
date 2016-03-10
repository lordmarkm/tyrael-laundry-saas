package com.tyrael.laundry.reports.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tyrael.laundry.reference.JobOrderStatus;
import com.tyrael.laundry.reports.base.BaseMeta;

/**
 *
 * @author Mark Baldwin B. Martinez on Mar 9, 2016
 *
 */
@Entity(name = "job_order_fact")
public class JobOrderFact extends BaseMeta {

    @Column(name = "tracking_no", nullable = false)
    protected String trackingNo;

    @ManyToOne
    @JoinColumn(name = "customer_dim_id", nullable = false)
    protected CustomerDimension customer;

    @ManyToOne
    @JoinColumn(name = "branch_dim_id", nullable = false)
    protected BranchDimension branch;

    @ManyToOne
    @JoinColumn(name = "brand_dim_id", nullable = false)
    protected BrandDimension brand;

    @Column(name = "date_received_dim_id", nullable = false)
    protected Long dateReceivedDimensionId;

    @Column(name = "date_due_dim_id", nullable = false)
    protected Long dateDueDimensionId;

    @Column(name = "date_completed_dim_id")
    protected Long dateCompletedDimensionId;

    @Column(name = "date_claimed_dim_id")
    protected Long dateClaimedDimension;

    @Column(name = "total_amt", nullable = false)
    protected BigDecimal totalAmount;

    @Column(name = "total_amt_paid")
    protected BigDecimal totalAmountPaid;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    protected JobOrderStatus status;

    @Column(name = "wt_kg")
    protected BigDecimal weightInKilos;

}
