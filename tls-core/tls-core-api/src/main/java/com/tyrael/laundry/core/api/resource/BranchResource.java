package com.tyrael.laundry.core.api.resource;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.commons.dto.BranchDto;
import com.tyrael.laundry.commons.resource.BaseResource;
import com.tyrael.laundry.core.service.BranchService;
import com.tyrael.laundry.model.branch.Branch;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
@RestController
@RequestMapping("/branch")
public class BranchResource extends BaseResource<Branch, BranchDto, BranchService> {

    @RequestMapping(value = "/{brandCode}", method = GET, params = "branchCode")
    public ResponseEntity<BranchDto> findByBrandCodeAndCode(@PathVariable String brandCode, @RequestParam String branchCode) {
        return new ResponseEntity<>(service.findInfoByBrandCodeAndCode(brandCode, branchCode), OK);
    }

    @RequestMapping(value = "/{brandCode}", method = GET, params = "byBrandCode")
    public ResponseEntity<List<BranchDto>> findByBrandCode(@PathVariable String brandCode, @RequestParam boolean byBrandCode) {
        return new ResponseEntity<>(service.findInfoByBrandCode(brandCode), OK);
    }

}
