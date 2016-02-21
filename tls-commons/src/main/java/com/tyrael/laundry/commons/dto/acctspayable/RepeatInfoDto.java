package com.tyrael.laundry.commons.dto.acctspayable;

import org.springframework.core.style.ToStringCreator;

import com.tyrael.laundry.reference.RepeatType;

/**
 * 
 * @author Mark Martinez, created Feb 21, 2016
 *
 */
public class RepeatInfoDto {

    protected RepeatType repeatType;

    /**
     * day of week, day of month, or day of year
     * used where applicable
     */
    protected Integer dueDate;

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("type", repeatType)
                .append("due date", dueDate)
                .toString();
    }

    public RepeatType getRepeatType() {
        return repeatType;
    }

    public void setRepeatType(RepeatType repeatType) {
        this.repeatType = repeatType;
    }

    public Integer getDueDate() {
        return dueDate;
    }

    public void setDueDate(Integer dueDate) {
        this.dueDate = dueDate;
    }

}
