package com.tyrael.laundry.model.acctspayable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.tyrael.laundry.reference.RepeatType;

/**
 * 
 * @author Mark Martinez, created Feb 21, 2016
 *
 */
@Embeddable
public class RepeatInfo {

    @Column(name = "repeat_type")
    @Enumerated(EnumType.STRING)
    private RepeatType repeatType;

    /**
     * day of week, day of month, or day of year
     * used where applicable
     */
    @Column(name = "due_date")
    private Integer dueDate;

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
