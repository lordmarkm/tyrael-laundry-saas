package com.tyrael.laundry.core.service;

import com.tyrael.laundry.commons.service.TyraelJpaService;
import com.tyrael.laundry.core.service.custom.JobOrderServiceCustom;
import com.tyrael.laundry.model.joborder.JobOrder;

/**
 * 
 * @author Mark Martinez, create Dec 21, 2015
 *
 */
public interface JobOrderService extends JobOrderServiceCustom, TyraelJpaService<JobOrder> {

    JobOrder findByTrackingNo(String trackingNo);

}
