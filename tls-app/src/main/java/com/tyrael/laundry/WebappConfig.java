package com.tyrael.laundry;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@EnableWebMvc
@Configuration
public class WebappConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private FreeMarkerViewResolver fmvr;

    @PostConstruct
    public void init() {
        fmvr.setOrder(1);
    }

    //Enable direct access to .html, .css, etc
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable(); 
    }

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".html");
        resolver.setOrder(99);
        return resolver;
    }

    //Link directly to html pages
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
      registry.addViewController("/error").setViewName("errorpage");

      //Home page for anonymous visitors (doesn't load angular stuff)
      registry.addViewController("/").setViewName("index");

      //Home page for the angular app
      registry.addViewController("/app/").setViewName("app/app");
    }

    //Allows us to use Pageable as an argument for controller methods
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        resolver.setMaxPageSize(100);
        resolver.setFallbackPageable(new PageRequest(1, 100));
        resolver.setOneIndexedParameters(true);
        resolver.setSizeParameterName("count");
        resolver.setPageParameterName("page");
        argumentResolvers.add(resolver);
    }
}
