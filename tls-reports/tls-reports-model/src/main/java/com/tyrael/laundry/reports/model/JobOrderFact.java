package com.tyrael.laundry.reports.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.tyrael.laundry.reports.base.BaseMeta;

@Entity(name = "job_order_fact")
public class JobOrderFact extends BaseMeta {

    @Column(name = "tracking_no")
    protected String trackingNo;

}
