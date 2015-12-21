package com.tyrael.laundry;

import static org.dozer.loader.api.FieldsMappingOptions.copyByReference;
import static org.dozer.loader.api.FieldsMappingOptions.oneWay;

import javax.annotation.PostConstruct;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tyrael.laundry.commons.dto.BaseDto;
import com.tyrael.laundry.commons.dto.BranchDto;
import com.tyrael.laundry.commons.model.BaseEntity;
import com.tyrael.laundry.model.branch.Branch;

/**
 * 
 * @author Mark Martinez, Nov 19, 2015
 *
 */
@Configuration
public class MapperConfig {

    @Bean
    public DozerBeanMapper mapper() {
        return new DozerBeanMapper();
    }

    @PostConstruct
    public void initMapping() {
        mapper().addMapping(new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(BaseEntity.class, BaseDto.class)
                        .fields("dateCreated", "dateCreated", copyByReference(), oneWay())
                        .fields("dateUpdated", "dateUpdated", copyByReference(), oneWay());

                mapping(BranchDto.class, Branch.class, TypeMappingOptions.oneWay())
                    .exclude("brand");
            }
        });
    }

}
