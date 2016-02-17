package com.tyrael.laundry.pos.api.resource;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.resource.BaseResource;
import com.tyrael.laundry.dto.inventory.SalesHeaderInfo;
import com.tyrael.laundry.model.inventory.SalesHeader;
import com.tyrael.laundry.pos.service.SalesHeaderService;

/**
 * 
 * @author Mark Martinez, create Feb 10, 2016
 *
 */
@RestController
@RequestMapping("/sales-header")
public class SalesHeaderResource
    extends BaseResource<SalesHeader, SalesHeaderInfo, SalesHeaderService> {

    private static final Logger LOG = LoggerFactory.getLogger(SalesHeaderResource.class);

    @RequestMapping(method = GET, params = "term")
    public ResponseEntity<PageInfo<SalesHeaderInfo>> page(Principal principal,
            Pageable page, @RequestParam String term) {
        LOG.debug("SalesHeader query. Principal={}, page={}, term={}", principal, page, term);

        return new ResponseEntity<>(service.rqlSearch(term, page), OK);
    }

}
