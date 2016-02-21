package com.tyrael.laundry.commons.dto.acctspayable;

import com.tyrael.laundry.commons.dto.BaseNamedDto;
import com.tyrael.laundry.commons.util.SlugUtil;

import org.joda.time.DateTime;
import org.springframework.core.style.ToStringCreator;

/**
 * 
 * @author Mark Martinez, created Feb 21, 2016
 *
 */
public class AccountsPayableInfo extends BaseNamedDto {

    protected String code;
    protected String branchCode;
    protected String branchName;
    protected String brandName;
    protected DateTime lastPayment;
    protected RepeatInfoDto repeat;

    @Override
    public ToStringCreator toStringCreator() {
        return super.toStringCreator()
                .append("code", code)
                .append("branch code", branchCode)
                .append("branch name", branchName)
                .append("brand name", brandName)
                .append("last payment", lastPayment)
                .append("repeat", repeat);
    }

    public String getSlug() {
        return SlugUtil.toSlug(branchName, name);
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public RepeatInfoDto getRepeat() {
        return repeat;
    }
    public void setRepeat(RepeatInfoDto repeat) {
        this.repeat = repeat;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public DateTime getLastPayment() {
        return lastPayment;
    }

    public void setLastPayment(DateTime lastPayment) {
        this.lastPayment = lastPayment;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

}
