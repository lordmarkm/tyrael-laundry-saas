package com.tyrael.laundry;

import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.tyrael.laundry.commons.util.DateUtil;

/**
 * 
 * @author Mark Martinez, Nov 21, 2015
 *
 */
@Configuration
public class DateTimeSerializationConfig {

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void configureDateTimeSerialization() {
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new SimpleDateFormat(DateUtil.DATETIME_FORMAT));
        objectMapper.registerModule(new JodaModule());
    }

}
