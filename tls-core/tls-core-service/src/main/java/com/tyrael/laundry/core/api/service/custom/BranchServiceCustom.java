package com.tyrael.laundry.core.api.service.custom;

import com.tyrael.laundry.commons.service.TyraelJpaServiceCustom;
import com.tyrael.laundry.core.api.dto.BranchDto;
import com.tyrael.laundry.model.branch.Branch;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
public interface BranchServiceCustom extends TyraelJpaServiceCustom<Branch, BranchDto> {

    BranchDto findInfoByBrandCodeAndCode(String brandCode, String branchCode);

}
