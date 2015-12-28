package com.tyrael.laundry.core.api.resource;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.dto.joborder.ServiceTypeInfo;
import com.tyrael.laundry.commons.resource.BaseResource;
import com.tyrael.laundry.core.service.ServiceTypeService;
import com.tyrael.laundry.model.joborder.ServiceType;

/**
 * @author mbmartinez
 */
@RestController
@RequestMapping("/servicetype")
public class ServiceTypeResource extends BaseResource<ServiceType, ServiceTypeInfo, ServiceTypeService> {

    @Override
    protected PageInfo<ServiceTypeInfo> getPageImpl(Pageable page) {
        return service.pageInfo(page);
    }

    @RequestMapping("/enabled")
    protected PageInfo<ServiceTypeInfo> findEnabled(@RequestParam String branchCode, Pageable page) {
        return service.findInfoByBranchCodeAndEnabled(branchCode, page);
    }

    @RequestMapping(value = "/batch", method = POST)
    public ResponseEntity<List<ServiceTypeInfo>> save(@RequestBody List<ServiceTypeInfo> services) {
        return new ResponseEntity<>(service.saveInfo(services), OK);
    }

}
