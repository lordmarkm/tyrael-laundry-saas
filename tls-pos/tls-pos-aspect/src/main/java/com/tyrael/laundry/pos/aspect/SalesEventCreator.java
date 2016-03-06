package com.tyrael.laundry.pos.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tyrael.laundry.commons.dto.inventory.SalesHeaderInfo;
import com.tyrael.laundry.core.service.EventService;
import com.tyrael.laundry.model.inventory.SalesHeader;
import com.tyrael.laundry.model.inventory.event.SalesHeaderEvent;
import com.tyrael.laundry.pos.service.SalesHeaderService;

/**
 *
 * @author Mark Martinez, created Mar 6, 2016
 *
 */
@Aspect
@Component
public class SalesEventCreator {

    private static final Logger LOG = LoggerFactory.getLogger(SalesEventCreator.class);

    @Autowired
    private EventService eventService;

    @Autowired
    private SalesHeaderService salesHeaderService;

    @Around("execution(* com.tyrael.laundry.pos.service.custom.impl.SalesHeaderServiceCustomImpl.saveInfo(..))")
    public Object logEvent(ProceedingJoinPoint jp) throws Throwable {
        SalesHeaderInfo salesHeaderInfo =  (SalesHeaderInfo) jp.proceed();
        LOG.debug("Creating sales header event. arg={}", salesHeaderInfo);

        SalesHeader salesHeader = salesHeaderService.findOne(salesHeaderInfo.getId());
        if (null != salesHeader) {
            SalesHeaderEvent she = new SalesHeaderEvent("Sales header created", salesHeader);
            eventService.save(she);
        }
        return salesHeaderInfo;
    }

}
