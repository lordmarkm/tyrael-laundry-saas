package com.tyrael.laundry.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
@Configuration
public class TyraelLaundryWebConfig extends WebMvcConfigurationSupport {

    //Enable direct access to .html, .css, etc
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable(); 
    }

}
