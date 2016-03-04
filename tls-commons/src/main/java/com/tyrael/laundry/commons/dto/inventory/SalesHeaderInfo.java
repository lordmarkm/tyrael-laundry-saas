package com.tyrael.laundry.commons.dto.inventory;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.core.style.ToStringCreator;

import com.tyrael.laundry.commons.dto.BaseDto;
import com.tyrael.laundry.commons.dto.BranchDto;

/**
 * 
 * @author Mark Martinez, created Feb 13, 2016
 *
 */
public class SalesHeaderInfo extends BaseDto {

    private BigDecimal totalAmountPaid;
    private BranchDto branch;
    private List<SalesItemInfo> items;

    @Override
    public ToStringCreator toStringCreator() {
        return super.toStringCreator()
                .append("total amt", totalAmountPaid)
                .append("branch", branch)
                .append("items", items);
    }

    public BigDecimal getTotalAmountPaid() {
        return totalAmountPaid;
    }
    public void setTotalAmountPaid(BigDecimal totalAmountPaid) {
        this.totalAmountPaid = totalAmountPaid;
    }
    public BranchDto getBranch() {
        return branch;
    }
    public void setBranch(BranchDto branch) {
        this.branch = branch;
    }
    public List<SalesItemInfo> getItems() {
        return items;
    }
    public void setItems(List<SalesItemInfo> items) {
        this.items = items;
    }

}
