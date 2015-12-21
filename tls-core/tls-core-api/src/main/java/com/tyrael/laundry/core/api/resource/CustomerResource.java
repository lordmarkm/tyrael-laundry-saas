package com.tyrael.laundry.core.api.resource;

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
import com.tyrael.laundry.commons.dto.customer.CustomerInfo;
import com.tyrael.laundry.core.service.CustomerService;
import com.tyrael.laundry.model.customer.Customer;

/**
 * 
 * @author Mark Martinez, create Dec 22, 2015
 *
 */
@RestController
@RequestMapping("/customer")
public class CustomerResource extends BaseResource<Customer, CustomerInfo, CustomerService> {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerResource.class);

    @RequestMapping(method = GET, params = "term")
    public ResponseEntity<PageInfo<CustomerInfo>> page(Principal principal,
            Pageable page,
            @RequestParam String term) {
        LOG.debug("Customer query. Principal={}, page={}, term={}", principal, page, term);
        return new ResponseEntity<>(service.pageInfo(term, page), OK);
    }

    @RequestMapping(method = GET, params = "customerCode")
    public ResponseEntity<CustomerInfo> findByCode(@RequestParam String customerCode) {
        return new ResponseEntity<>(service.findInfoByCode(customerCode), OK);
    }

}
