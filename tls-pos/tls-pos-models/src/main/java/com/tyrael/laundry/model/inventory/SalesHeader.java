package com.tyrael.laundry.model.inventory;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.tyrael.laundry.commons.model.BaseEntity;
import com.tyrael.laundry.model.branch.Branch;

/**
 *
 * @author Mark Martinez, create Feb 10, 2016
 *
 */
@Entity(name = "sales_header")
public class SalesHeader extends BaseEntity {

    @Column(name = "total_amt_paid", nullable = false)
    private BigDecimal totalAmountPaid;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    @OneToMany(mappedBy = "salesHeader", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalesItem> salesItems;

    public BigDecimal getTotalAmountPaid() {
        return totalAmountPaid;
    }

    public void setTotalAmountPaid(BigDecimal totalAmountPaid) {
        this.totalAmountPaid = totalAmountPaid;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public List<SalesItem> getSalesItems() {
        return salesItems;
    }

    public void setSalesItems(List<SalesItem> salesItems) {
        this.salesItems = salesItems;
    }

}
