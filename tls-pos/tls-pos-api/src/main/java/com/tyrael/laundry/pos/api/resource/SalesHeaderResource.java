package com.tyrael.laundry.pos.api.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
