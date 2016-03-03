package com.tyrael.laundry.model.event;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tyrael.laundry.model.joborder.JobOrder;

@Entity(name = "event_job_order")
@DiscriminatorValue("JOB_ORDER")
public class JobOrderEvent extends TlsEvent {

    @ManyToOne
    @JoinColumn(name = "job_order_id")
    private JobOrder jobOrder;

    public JobOrderEvent() {
        //No-arg constructor
    }

    public JobOrderEvent(String message, JobOrder jobOrder) {
        super(message);
        this.jobOrder = jobOrder;
    }

    public JobOrder getJobOrder() {
        return jobOrder;
    }

    public void setJobOrder(JobOrder jobOrder) {
        this.jobOrder = jobOrder;
    }

}
