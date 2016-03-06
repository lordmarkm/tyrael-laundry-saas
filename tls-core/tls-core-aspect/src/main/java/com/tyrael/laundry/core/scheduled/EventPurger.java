package com.tyrael.laundry.core.scheduled;

import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mysema.query.types.expr.BooleanExpression;
import com.tyrael.laundry.core.service.EventService;
import com.tyrael.laundry.model.event.QTlsEvent;
import com.tyrael.laundry.model.event.TlsEvent;

@Component
public class EventPurger {

    private static Logger LOG = LoggerFactory.getLogger(EventPurger.class);

    @Autowired
    private EventService eventService;

    /**
     * Runs once a day, purges events older than 45 days
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void purgeOldEvents() {
        LOG.debug("Purging events older than 45 days.");
        DateTime fortyFiveDaysAgo = DateTime.now().minusDays(45);
        BooleanExpression predicate = QTlsEvent.tlsEvent.dateCreated.before(fortyFiveDaysAgo);
        List<TlsEvent> oldEvents = (List<TlsEvent>) eventService.findAll(predicate);
        LOG.debug("Found events. Proceeding to delete. count={}", oldEvents.size());
        eventService.delete(oldEvents);
    }

}
