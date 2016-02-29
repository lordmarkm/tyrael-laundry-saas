package com.tyrael.laundry.core.api.resource;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.commons.dto.DashboardDto;
import com.tyrael.laundry.core.service.AccountsPayableService;
import com.tyrael.laundry.core.service.JobOrderService;
import com.tyrael.laundry.pos.service.SalesHeaderService;

/**
 *
 * @author Mark Baldwin B. Martinez on Feb 29, 2016
 *
 */
@RestController
@RequestMapping("/dashboard")
public class DashboardResource {

    @Autowired
    private JobOrderService joborderService;

    @Autowired
    private SalesHeaderService salesHeaderService;

    @Autowired
    private AccountsPayableService acctsPayableService;

    @RequestMapping(method = GET)
    public ResponseEntity<DashboardDto> get() {
        return new ResponseEntity<>(prepareDto(), OK);
    }

    private DashboardDto prepareDto() {
        DashboardDto dashboard = new DashboardDto();
        dashboard.setNewJoborders(joborderService.countNew());
        dashboard.setSalesToday(salesHeaderService.salesToday());
        dashboard.setAccountsDue(acctsPayableService.countDue());
        return dashboard;
    }

}
