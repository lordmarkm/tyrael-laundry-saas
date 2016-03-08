package com.tyrael.laundry.core.service;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Query;

import com.tyrael.laundry.commons.service.TyraelJpaService;
import com.tyrael.laundry.core.service.custom.JobOrderServiceCustom;
import com.tyrael.laundry.model.customer.Customer;
import com.tyrael.laundry.model.joborder.JobOrder;

/**
 * 
 * @author Mark Martinez, create Dec 21, 2015
 *
 */
public interface JobOrderService extends JobOrderServiceCustom, TyraelJpaService<JobOrder> {

    JobOrder findByTrackingNo(String trackingNo);

    @Query("select sum(j.totalAmount) - sum(j.totalAmountPaid) from com.tyrael.laundry.model.joborder.JobOrder j where j.customer = ?1")
    BigDecimal computeBalance(Customer customer);

}
