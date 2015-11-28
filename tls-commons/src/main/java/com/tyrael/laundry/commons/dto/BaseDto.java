package com.tyrael.laundry.commons.dto;

import org.joda.time.DateTime;

/**
 * 
 * @author Mark Martinez, Nov 21, 2015
 *
 */
public class BaseDto {

    protected DateTime dateCreated;
    protected DateTime dateUpdated;

    public DateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(DateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public DateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(DateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

}
