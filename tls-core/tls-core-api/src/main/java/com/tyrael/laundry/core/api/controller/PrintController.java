package com.tyrael.laundry.core.api.controller;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tyrael.laundry.commons.dto.joborder.JobOrderInfo;
import com.tyrael.laundry.core.service.JobOrderService;

/**
 *
 * @author Mark Martinez, create Dec 23, 2015
 *
 */
@Controller
@RequestMapping("/print/{trackingNo}")
public class PrintController {

    @Autowired
    private JobOrderService service;

    @Autowired
    private Mapper mapper;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView print(@PathVariable String trackingNo) {
        JobOrderInfo joborder = mapper.map(service.findByTrackingNo(trackingNo), JobOrderInfo.class);
        if (null == joborder) {
            return null;
        }
        return new ModelAndView("print")
                .addObject("joborder", joborder);
    }

}
