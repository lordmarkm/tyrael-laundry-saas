package com.tyrael.laundry.core.service.custom;

import org.springframework.data.domain.Pageable;

import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.dto.event.EventDto;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustom;
import com.tyrael.laundry.model.event.TlsEvent;
import com.tyrael.laundry.reference.EventType;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
public interface EventServiceCustom extends TyraelJpaServiceCustom<TlsEvent, EventDto> {

    PageInfo<EventDto> findInfoByType(EventType type, Pageable pageRequest);

}
