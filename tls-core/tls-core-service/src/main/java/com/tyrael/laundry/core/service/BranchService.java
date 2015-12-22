package com.tyrael.laundry.core.service;

import java.util.List;

import com.tyrael.laundry.commons.service.TyraelJpaService;
import com.tyrael.laundry.core.service.custom.BranchServiceCustom;
import com.tyrael.laundry.model.branch.Branch;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
public interface BranchService extends BranchServiceCustom, TyraelJpaService<Branch> {

    Branch findByCode(String code);
    Branch findByBrandCodeAndCode(String brandCode, String branchCode);
    List<Branch> findByBrandCode(String brandCode);

}
