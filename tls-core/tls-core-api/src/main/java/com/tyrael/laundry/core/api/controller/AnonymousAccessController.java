package com.tyrael.laundry.core.api.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tyrael.laundry.commons.dto.customer.CustomerInfo;
import com.tyrael.laundry.commons.dto.joborder.JobOrderInfo;
import com.tyrael.laundry.core.service.CustomerService;
import com.tyrael.laundry.core.service.JobOrderService;
import com.tyrael.laundry.model.customer.Customer;

/**
 *
 * @author Mark Baldwin B. Martinez on Mar 9, 2016
 *
 */
@Controller
@RequestMapping("/anon")
public class AnonymousAccessController {

    private static final Logger LOG = LoggerFactory.getLogger(AnonymousAccessController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JobOrderService jobOrderService;

    @Autowired
    private Mapper mapper;

    @RequestMapping(value = "/job-order/{trackingNo}", method = GET)
    public ModelAndView viewJobOrderDetails(@PathVariable String trackingNo) {
        JobOrderInfo jobOrder = jobOrderService.findByTrackinNoInfo(trackingNo);
        return new ModelAndView("anonymous_joborder")
                .addObject("jobOrder", jobOrder);
    }

    @RequestMapping(value = "/{customerCode}", method = GET)
    public String redirectToJobOrders(@PathVariable String customerCode) {
        Customer customer = customerService.findByCode(customerCode);
        if (null != customer) {
            CustomerInfo customerInfo = mapper.map(customer, CustomerInfo.class);
            LOG.debug("Redirecting to anonymous job orders view. customerCode={}", customerCode);
            return "redirect:/app/#/anon/joborders/" + customerCode + "?customerName=" + customerInfo.getFormattedName();
        } else {
            return "redirect:/";
        }
    }

}
