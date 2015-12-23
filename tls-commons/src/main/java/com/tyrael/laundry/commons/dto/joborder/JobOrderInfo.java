package com.tyrael.laundry.commons.dto.joborder;

import java.math.BigDecimal;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.core.style.ToStringCreator;

import com.tyrael.laundry.commons.dto.BaseDto;
import com.tyrael.laundry.commons.dto.BranchDto;
import com.tyrael.laundry.commons.dto.customer.CustomerInfo;
import com.tyrael.laundry.commons.util.DateUtil;
import com.tyrael.laundry.reference.JobOrderStatus;

/**
 * @author mbmartinez
 */
public class JobOrderInfo extends BaseDto {

    private CustomerInfo customer;
    private DateTime dateReceived;
    private DateTime dateDue;
    private DateTime dateCompleted;
    private DateTime dateClaimed;
    private String trackingNo;
    private List<JobServiceInfo> jobServices;
    private List<JobItemInfo> jobItems;
    private BigDecimal totalAmount;
    private BigDecimal totalAmountPaid;
    private JobOrderStatus status;
    private BranchDto branchInfo;

    @Override
    protected ToStringCreator toStringCreator() {
        return super.toStringCreator()
            .append("tracking no.", trackingNo)
            .append("customer", customer)
            .append("date rcvd", dateReceived)
            .append("date due", dateDue)
            .append("date completed", dateCompleted)
            .append("date claimed", dateClaimed)
            .append("job service", jobServices)
            .append("job items", jobItems)
            .append("total amt", totalAmount)
            .append("total amt paid", totalAmountPaid)
            .append("status", status)
            .append("branch", branchInfo);
    }

    public String getSlug() {
        return new StringBuilder(customer.getSlug())
                .append("-")
                .append(dateReceived.toString(DateUtil.DATE_FORMAT))
                .toString();
    }

    public CustomerInfo getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerInfo customer) {
        this.customer = customer;
    }

    public DateTime getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(DateTime dateReceived) {
        this.dateReceived = dateReceived;
    }

    public DateTime getDateDue() {
        return dateDue;
    }

    public void setDateDue(DateTime dateDue) {
        this.dateDue = dateDue;
    }

    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }

    public List<JobServiceInfo> getJobServices() {
        return jobServices;
    }

    public void setJobServices(List<JobServiceInfo> jobServices) {
        this.jobServices = jobServices;
    }

    public List<JobItemInfo> getJobItems() {
        return jobItems;
    }

    public void setJobItems(List<JobItemInfo> jobItems) {
        this.jobItems = jobItems;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public DateTime getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(DateTime dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public DateTime getDateClaimed() {
        return dateClaimed;
    }

    public void setDateClaimed(DateTime dateClaimed) {
        this.dateClaimed = dateClaimed;
    }

    public BigDecimal getTotalAmountPaid() {
        return totalAmountPaid;
    }

    public void setTotalAmountPaid(BigDecimal totalAmountPaid) {
        this.totalAmountPaid = totalAmountPaid;
    }

    public JobOrderStatus getStatus() {
        return status;
    }

    public void setStatus(JobOrderStatus status) {
        this.status = status;
    }

    public BranchDto getBranchInfo() {
        return branchInfo;
    }

    public void setBranchInfo(BranchDto branchInfo) {
        this.branchInfo = branchInfo;
    }

}
