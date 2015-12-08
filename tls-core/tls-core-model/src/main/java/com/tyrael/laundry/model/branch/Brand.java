package com.tyrael.laundry.model.branch;

import javax.persistence.Column;
import javax.persistence.Entity;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
