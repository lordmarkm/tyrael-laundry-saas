package com.tyrael.laundry.commons.dto;

import org.springframework.core.style.ToStringCreator;

import com.tyrael.laundry.commons.util.SlugUtil;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
public class BaseNamedDto extends BaseDto {

    protected String name;
    protected String description;

    @Override
    protected ToStringCreator toStringCreator() {
        return new ToStringCreator(this)
                .append("name", name)
                .append("desc", description);
    }

    public String getSlug() {
        return SlugUtil.toSlug(name);
    }
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
