package com.tyrael.laundry.commons.dto.acctspayable;

import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.springframework.core.style.ToStringCreator;

import com.tyrael.laundry.commons.dto.BaseDto;

/**
 * 
 * @author Mark Martinez, created Feb 21, 2016
 *
 */
public class AccountsPaymentInfo extends BaseDto {

    protected String accountsPayableCode;
    protected BigDecimal amount;
    protected DateTime paymentDate;
    protected String accountsPayableName;
    protected String branchName;

    @Override
    public ToStringCreator toStringCreator() {
        return super.toStringCreator()
                .append("ap code", accountsPayableCode)
                .append("amt", amount)
                .append("date", paymentDate);
    }

    public String getAccountsPayableCode() {
        return accountsPayableCode;
    }

    public void setAccountsPayableCode(String accountsPayableCode) {
        this.accountsPayableCode = accountsPayableCode;
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

    public String getAccountsPayableName() {
        return accountsPayableName;
    }

    public void setAccountsPayableName(String accountsPayableName) {
        this.accountsPayableName = accountsPayableName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }


}
