package com.tyrael.laundry.commons.dto.event;

import com.tyrael.laundry.commons.dto.BaseDto;
import com.tyrael.laundry.commons.dto.BranchDto;
import com.tyrael.laundry.commons.dto.BrandDto;
import com.tyrael.laundry.commons.dto.joborder.JobOrderInfo;
import com.tyrael.laundry.reference.EventType;

/**
 *
 * @author Mark Baldwin B. Martinez on Feb 27, 2016
 *
 */
public class EventDto extends BaseDto {

    private EventType eventType;
    private String message;
    private BrandDto brand;
    private BranchDto branch;
    private JobOrderInfo jobOrder;

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JobOrderInfo getJobOrder() {
        return jobOrder;
    }

    public void setJobOrder(JobOrderInfo jobOrder) {
        this.jobOrder = jobOrder;
    }

    public BranchDto getBranch() {
        return branch;
    }

    public void setBranch(BranchDto branch) {
        this.branch = branch;
    }

    public BrandDto getBrand() {
        return brand;
    }

    public void setBrand(BrandDto brand) {
        this.brand = brand;
    }

}
