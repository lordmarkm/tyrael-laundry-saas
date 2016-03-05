package com.tyrael.laundry.core.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tyrael.laundry.commons.dto.customer.CustomerInfo;
import com.tyrael.laundry.core.service.CustomerService;
import com.tyrael.laundry.core.service.EventService;
import com.tyrael.laundry.model.customer.Customer;
import com.tyrael.laundry.model.event.CustomerEvent;

/**
 *
 * @author Mark Martinez, created Mar 5, 2016
 *
 */
@Aspect
@Component
public class CustomerEventCreator {
    private static final Logger LOG = LoggerFactory.getLogger(JobOrderEventCreator.class);

    @Autowired
    private EventService eventService;

    @Autowired
    private CustomerService customerService;

    @Around("execution(* com.tyrael.laundry.core.service.custom.impl.CustomerServiceCustomImpl.saveInfo(..)) && args(dto)")
    public Object logEvent(ProceedingJoinPoint jp, CustomerInfo dto) throws Throwable {

        String message = null;
        if (null == dto.getCode()) {
            message = "New customer created";
        } else {
            message = "Customer updated";
        }

        LOG.debug("Creating customer save event. arg={}", dto);

        CustomerInfo customerInfo =  (CustomerInfo) jp.proceed();
        Customer customer = customerService.findOne(customerInfo.getId());
        if (null != customer) {
            CustomerEvent joe = new CustomerEvent(message, customer);
            eventService.save(joe);
        }

        return customerInfo;
    }

    @AfterReturning("execution(* com.tyrael.laundry.commons.service.TyraelJpaServiceCustomImpl.deleteInfo(..))"
            + " && target(com.tyrael.laundry.core.service.custom.CustomerServiceCustom) && args(id)")
    public void logRestockEvent(JoinPoint joinPoint, Long id) {
        Customer customer = customerService.findOne(id);
        LOG.debug("Logging customer delete event. customer={}", customer);
        if (null != customer) {
            CustomerEvent ce = new CustomerEvent("Customer has been deleted", customer);
            eventService.save(ce);
        }
    }
}
