package com.tyrael.laundry.core.api.service.custom.impl;

import com.tyrael.laundry.commons.service.TyraelJpaServiceCustomImpl;
import com.tyrael.laundry.core.api.dto.BranchDto;
import com.tyrael.laundry.core.api.service.BranchService;
import com.tyrael.laundry.core.api.service.custom.BranchServiceCustom;
import com.tyrael.laundry.model.branch.Branch;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
public class BranchServiceCustomImpl
    extends TyraelJpaServiceCustomImpl<Branch, BranchDto, BranchService>
    implements BranchServiceCustom {

}
