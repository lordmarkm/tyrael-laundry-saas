package com.tyrael.laundry.commons.dto.event;

import com.tyrael.laundry.commons.dto.BaseDto;
import com.tyrael.laundry.reference.EventType;

/**
 *
 * @author Mark Baldwin B. Martinez on Feb 27, 2016
 *
 */
public class EventDto extends BaseDto {

    private EventType eventType;

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

}
