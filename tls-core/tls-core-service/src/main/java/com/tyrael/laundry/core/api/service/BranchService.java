package com.tyrael.laundry.core.api.service;

import com.tyrael.laundry.commons.service.TyraelJpaService;
import com.tyrael.laundry.core.api.service.custom.BranchServiceCustom;
import com.tyrael.laundry.model.branch.Branch;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
public interface BranchService extends BranchServiceCustom, TyraelJpaService<Branch> {

    Branch findByCode(String code);

}
