package com.tyrael.laundry.model.branch;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.tyrael.laundry.commons.model.BaseNamedEntity;

/**
 * 
 * @author Mark Martinez, created Nov 28, 2015
 *
 */
@Entity(name = "brand")
public class Brand extends BaseNamedEntity {

    @Column(name = "brand_code")
    private String code;

    @OneToMany(mappedBy = "brand")
    private List<Branch> branches;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

}
