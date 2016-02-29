package com.tyrael.laundry.model.acctspayable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.tyrael.laundry.commons.model.BaseNamedEntity;
import com.tyrael.laundry.model.branch.Branch;

/**
 * 
 * @author Mark Martinez, created Feb 21, 2016
 *
 */
@Entity(name = "accounts_payable")
public class AccountsPayable extends BaseNamedEntity {

    @Column(name = "ap_code", nullable = false)
    private String code;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    @Column(name = "last_payment")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime lastPayment;

    @Embedded
    private RepeatInfo repeat;

    public RepeatInfo getRepeat() {
        return repeat;
    }

    public void setRepeat(RepeatInfo repeat) {
        this.repeat = repeat;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public DateTime getLastPayment() {
        return lastPayment;
    }

    public void setLastPayment(DateTime lastPayment) {
        this.lastPayment = lastPayment;
    }

}
