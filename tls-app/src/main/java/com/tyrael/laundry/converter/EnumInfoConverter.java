package com.tyrael.laundry.converter;

import org.dozer.CustomConverter;

import com.tyrael.laundry.commons.dto.EnumInfo;
import com.tyrael.laundry.reference.JobItemType;

/**
 * 
 * @author Mark Martinez, create Dec 22, 2015
 *
 */
public class EnumInfoConverter implements CustomConverter {

    @Override
    public Object convert(Object existingDestinationFieldValue,
            Object sourceFieldValue,
            Class<?> destinationClass,
            Class<?> sourceClass) {

        EnumInfo ei = (EnumInfo) existingDestinationFieldValue;
        JobItemType type = (JobItemType) sourceFieldValue;
        ei.setCode(type.name());
        ei.setIconPath(type.getIconPath());

        return ei;
    }

}
