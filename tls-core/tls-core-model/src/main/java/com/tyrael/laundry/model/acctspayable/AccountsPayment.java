package com.tyrael.laundry.model.acctspayable;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.tyrael.laundry.commons.model.BaseEntity;

/**
 *
 * @author Mark Baldwin B. Martinez on Feb 21, 2016
 *
 */
@Entity(name = "accounts_payment")
public class AccountsPayment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "accts_payable_id", nullable = false)
    private AccountsPayable accountsPayable;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "payment_date", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime paymentDate;

    public AccountsPayable getAccountsPayable() {
        return accountsPayable;
    }

    public void setAccountsPayable(AccountsPayable accountsPayable) {
        this.accountsPayable = accountsPayable;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public DateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(DateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

}
