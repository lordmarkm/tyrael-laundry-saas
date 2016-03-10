package com.tyrael.laundry.reports.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.tyrael.laundry.reports.base.BaseMeta;

/**
 *
 * @author Mark Baldwin B. Martinez on Mar 10, 2016
 *
 */
@Entity(name = "brand_dimension")
public class BrandDimension extends BaseMeta {

    @Column(name = "code", unique = true)
    protected String code;

    @Column(name = "name")
    protected String name;

}
