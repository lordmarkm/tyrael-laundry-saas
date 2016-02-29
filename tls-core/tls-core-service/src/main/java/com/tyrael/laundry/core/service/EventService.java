package com.tyrael.laundry.core.service;

import com.tyrael.laundry.commons.service.TyraelJpaService;
import com.tyrael.laundry.core.service.custom.EventServiceCustom;
import com.tyrael.laundry.model.event.TlsEvent;

/**
 *
 * @author Mark Baldwin B. Martinez on Feb 27, 2016
 *
 */
public interface EventService extends TyraelJpaService<TlsEvent>, EventServiceCustom {

}
