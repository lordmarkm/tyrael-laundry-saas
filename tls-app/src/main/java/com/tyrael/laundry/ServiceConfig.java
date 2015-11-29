package com.tyrael.laundry;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 
 * @author Mark Martinez, Nov 19, 2015
 *
 */
@Configuration
@EnableJpaRepositories(basePackages={"com.tyrael.laundry.core.api.service"}, repositoryImplementationPostfix = "CustomImpl")
public class ServiceConfig {

}
