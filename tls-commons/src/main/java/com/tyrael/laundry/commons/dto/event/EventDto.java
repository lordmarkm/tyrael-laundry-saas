package com.tyrael.laundry.commons.dto.event;

import com.tyrael.laundry.commons.dto.BaseDto;
import com.tyrael.laundry.reference.EventType;

/**
 *
 * @author Mark Baldwin B. Martinez on Feb 27, 2016
 *
 */
public class EventDto extends BaseDto {

    private EventType type;

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

}
