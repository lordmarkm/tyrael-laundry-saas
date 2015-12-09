package com.tyrael.laundry.core.api.dto;

import java.util.List;

import com.tyrael.laundry.commons.dto.BaseNamedDto;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
public class BrandDto extends BaseNamedDto {

    private String code;
    private List<BranchDto> branches;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<BranchDto> getBranches() {
        return branches;
    }

    public void setBranches(List<BranchDto> branches) {
        this.branches = branches;
    }

}
