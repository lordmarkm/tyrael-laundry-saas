package com.tyrael.laundry.core.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tyrael.laundry.commons.dto.joborder.JobOrderInfo;
import com.tyrael.laundry.core.service.EventService;
import com.tyrael.laundry.core.service.JobOrderService;
import com.tyrael.laundry.model.event.JobOrderEvent;
import com.tyrael.laundry.model.joborder.JobOrder;

/**
 *
 * @author Mark Martinez, created Mar 3, 2016
 *
 */
@Aspect
@Component
public class JobOrderEventCreator {

    private static final Logger LOG = LoggerFactory.getLogger(JobOrderEventCreator.class);

    @Autowired
    private EventService eventService;

    @Autowired
    private JobOrderService jobOrderService;

    @Around("execution(* com.tyrael.laundry.core.service.custom.impl.JobOrderServiceCustomImpl.saveInfo(..))")
    public Object logEvent(ProceedingJoinPoint jp) throws Throwable {
        JobOrderInfo arg = (JobOrderInfo) jp.getArgs()[0];
        LOG.debug("Creating job order update event.");
        JobOrderInfo jobOrderInfo =  (JobOrderInfo) jp.proceed();

        JobOrder jobOrder = jobOrderService.findOne(jobOrderInfo.getId());
        if (null != jobOrder) {
            String message;
            switch (jobOrder.getStatus()) {
            
            }

            JobOrderEvent joe = new Job
        }

        return jobOrderInfo;
    }

}
