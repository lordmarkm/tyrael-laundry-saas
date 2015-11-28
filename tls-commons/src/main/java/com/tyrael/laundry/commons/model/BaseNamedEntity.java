package com.tyrael.laundry.commons.model;

import javax.persistence.Column;

/**
 * 
 * @author Mark Martinez, created Nov 28, 2015
 *
 */
public class BaseNamedEntity extends BaseEntity {

    @Column(name = "name")
    protected String name;

    @Column(name = "desc")
    protected String description;

}
