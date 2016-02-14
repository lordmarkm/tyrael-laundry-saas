package com.tyrael.laundry.commons.dto;

import org.joda.time.DateTime;
import org.springframework.core.style.ToStringCreator;

/**
 * 
 * @author Mark Martinez, Nov 21, 2015
 *
 */
public class BaseDto {

    protected Long id;
    protected DateTime dateCreated;
    protected DateTime dateUpdated;
    protected String createdBy;
    protected String updatedBy;
    protected boolean deleted;

    @Override
    public final String toString() {
        return this.toStringCreator().toString();
    }

    protected ToStringCreator toStringCreator() {
        return new ToStringCreator(this)
                .append("id", id)
                .append("created", dateCreated)
                .append("updated", dateCreated)
                .append("deleted", deleted);
    }

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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

}
