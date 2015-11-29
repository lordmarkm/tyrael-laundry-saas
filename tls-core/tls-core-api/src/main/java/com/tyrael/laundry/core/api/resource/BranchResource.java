package com.tyrael.laundry.core.api.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.core.api.dto.BranchDto;
import com.tyrael.laundry.core.api.service.BranchService;
import com.tyrael.laundry.model.branch.Branch;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
@RestController
@RequestMapping("/branch")
public class BranchResource extends BaseResource<Branch, BranchDto, BranchService> {

}
