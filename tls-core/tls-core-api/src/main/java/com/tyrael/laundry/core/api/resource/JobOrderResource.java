package com.tyrael.laundry.core.api.resource;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.dto.joborder.JobOrderInfo;
import com.tyrael.laundry.commons.resource.BaseResource;
import com.tyrael.laundry.core.service.JobOrderService;
import com.tyrael.laundry.model.joborder.JobOrder;

/**
 * 
 * @author Mark Martinez, create Dec 21, 2015
 *
 */
@RestController
@RequestMapping("/joborder")
public class JobOrderResource extends BaseResource<JobOrder, JobOrderInfo, JobOrderService> {

    private static final Logger LOG = LoggerFactory.getLogger(JobOrderResource.class);

    @RequestMapping(method = GET, params = "term")
    public ResponseEntity<PageInfo<JobOrderInfo>> page(Principal principal,
            Pageable page,
            @RequestParam String term) {
        LOG.debug("JobOrder query. Principal={}, page={}, term={}", principal, page, term);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<>(service.rqlSearch(term, page), OK);
    }

    @PreAuthorize("hasRole('ROLE_POS') or hasRole('ROLE_MANAGER') or canAccess(#principal, #trackingNo)")
    @RequestMapping(method = GET, params = "trackingNo")
    public ResponseEntity<JobOrderInfo> findByTrackingNo(Principal principal, @RequestParam String trackingNo) {
        LOG.debug("Find by tracking number request. trackingNo={}", trackingNo);
        JobOrderInfo jobOrder = service.findByTrackinNoInfo(trackingNo);
        if (null == principal && null != jobOrder) {
            jobOrder.setCustomer(null);
        }
        return new ResponseEntity<>(jobOrder, OK);
    }

}
