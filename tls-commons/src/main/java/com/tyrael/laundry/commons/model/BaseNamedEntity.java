package com.tyrael.laundry.commons.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 
 * @author Mark Martinez, created Nov 28, 2015
 *
 */
@MappedSuperclass
public abstract class BaseNamedEntity extends BaseEntity {

    @Column(name = "name")
    protected String name;

    @Column(name = "description")
    protected String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
