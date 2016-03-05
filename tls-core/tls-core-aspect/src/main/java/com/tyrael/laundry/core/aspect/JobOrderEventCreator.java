package com.tyrael.laundry.core.aspect;

import java.math.BigDecimal;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tyrael.laundry.commons.dto.joborder.JobOrderInfo;
import com.tyrael.laundry.commons.util.MathUtil;
import com.tyrael.laundry.core.service.EventService;
import com.tyrael.laundry.core.service.JobOrderService;
import com.tyrael.laundry.model.event.JobOrderEvent;
import com.tyrael.laundry.model.joborder.JobOrder;
import com.tyrael.laundry.reference.JobOrderStatus;

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
        String originalTrackingNo = arg.getTrackingNo();

        JobOrder beforeUpdate = null;
        JobOrderStatus statusBeforeUpdate = null;
        BigDecimal amountPaidBeforeUpdate = null;
        if (null != arg.getId()) {
            beforeUpdate = jobOrderService.findOne(arg.getId());
            statusBeforeUpdate = beforeUpdate.getStatus();
            amountPaidBeforeUpdate = beforeUpdate.getTotalAmountPaid();
        }

        LOG.debug("Creating job order update event. arg={}", arg);

        JobOrderInfo jobOrderInfo =  (JobOrderInfo) jp.proceed();

        JobOrder jobOrder = jobOrderService.findOne(jobOrderInfo.getId());
        if (null != jobOrder) {
            String message;
            LOG.debug("Checking arg tracking no. val={}", originalTrackingNo);
            LOG.debug("Comparing new and old status. old={}, new={}", arg.getStatus(), jobOrder.getStatus());
            if (null == originalTrackingNo) {
                message = "New job order created";
            } else if (statusBeforeUpdate != jobOrder.getStatus()) {
                message = "Job order status updated";
            } else if (null != beforeUpdate && !MathUtil.eq(amountPaidBeforeUpdate, jobOrder.getTotalAmountPaid())) {
                message = "Payment has been made for job order";
            } else {
                message = "Job order has been updated";
            }

            JobOrderEvent joe = new JobOrderEvent(message, jobOrder);
            eventService.save(joe);
        }

        return jobOrderInfo;
    }

    @AfterReturning("execution(* com.tyrael.laundry.commons.service.TyraelJpaServiceCustomImpl.deleteInfo(..))"
            + " && target(com.tyrael.laundry.core.service.custom.JobOrderServiceCustom) && args(id)")
    public void logRestockEvent(JoinPoint joinPoint, Long id) {
        JobOrder jobOrder = jobOrderService.findOne(id);
        LOG.debug("Logging delete event. jobOrder={}", jobOrder);
        if (null != jobOrder) {
            JobOrderEvent joe = new JobOrderEvent("Job order has been deleted", jobOrder);
            eventService.save(joe);
        }
    }

}
