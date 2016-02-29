package com.tyrael.laundry.core.api.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.dto.event.EventDto;
import com.tyrael.laundry.commons.resource.BaseResource;
import com.tyrael.laundry.core.service.EventService;
import com.tyrael.laundry.model.event.TlsEvent;
import com.tyrael.laundry.reference.EventType;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/event")
public class EventResource extends BaseResource<TlsEvent, EventDto, EventService> {

    private static Logger LOG = LoggerFactory.getLogger(EventResource.class);

    @RequestMapping(method = GET, params="type")
    public ResponseEntity<PageInfo<EventDto>> findByType(@RequestParam EventType type, Pageable page) {
        LOG.debug("Find events by type. type={}", type);
        return ok(service.findInfoByType(type, page));
    }

    @Override
    protected PageInfo<EventDto> getPageImpl(Pageable page) {
        return service.findInfoByType(null, page);
    }

}
