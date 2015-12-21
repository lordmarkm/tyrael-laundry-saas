package com.tyrael.laundry.core.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.dto.joborder.ServiceTypeInfo;
import com.tyrael.laundry.core.service.ServiceTypeService;
import com.tyrael.laundry.model.joborder.ServiceType;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.springframework.http.HttpStatus.*;

/**
 * @author mbmartinez
 */
@RestController
@RequestMapping("/servicetype")
public class ServiceTypeResource extends BaseResource<ServiceType, ServiceTypeInfo, ServiceTypeService> {

    @Override
    protected PageInfo<ServiceTypeInfo> getPageImpl(Pageable page) {
        return service.findInfoByEnabled(page);
    }

    @RequestMapping(value = "/all", method = GET)
    public ResponseEntity<PageInfo<ServiceTypeInfo>> findAll() {
        Pageable pageRequest = new PageRequest(0, 100, new Sort(Direction.ASC, "label"));
        return new ResponseEntity<>(service.pageInfo(pageRequest), OK);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<List<ServiceTypeInfo>> save(@RequestBody List<ServiceTypeInfo> services) {
        return new ResponseEntity<>(service.saveInfo(services), OK);
    }

}
