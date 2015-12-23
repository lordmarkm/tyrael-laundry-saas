package com.tyrael.laundry.commons.dto.joborder;

import com.tyrael.laundry.commons.dto.BaseDto;
import com.tyrael.laundry.reference.JobItemType;

/**
 * @author markm
 */
public class JobItemInfo extends BaseDto {

    private JobItemType jobItemType;
    private int quantity;
    private String iconPath;

    public JobItemType getJobItemType() {
        return jobItemType;
    }
    public void setJobItemType(JobItemType jobItemType) {
        this.jobItemType = jobItemType;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getIconPath() {
        return iconPath;
    }
    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

}

