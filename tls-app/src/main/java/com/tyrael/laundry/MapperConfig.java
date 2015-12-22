package com.tyrael.laundry;

import static org.dozer.loader.api.FieldsMappingOptions.copyByReference;
import static org.dozer.loader.api.FieldsMappingOptions.oneWay;

import javax.annotation.PostConstruct;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.dozer.loader.api.TypeMappingOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tyrael.laundry.commons.dto.BaseDto;
import com.tyrael.laundry.commons.dto.BranchDto;
import com.tyrael.laundry.commons.dto.EnumInfo;
import com.tyrael.laundry.commons.dto.customer.CustomerInfo;
import com.tyrael.laundry.commons.dto.joborder.JobOrderInfo;
import com.tyrael.laundry.commons.model.BaseEntity;
import com.tyrael.laundry.converter.EnumInfoConverter;
import com.tyrael.laundry.model.branch.Branch;
import com.tyrael.laundry.model.customer.Customer;
import com.tyrael.laundry.model.joborder.JobOrder;

/**
 * 
 * @author Mark Martinez, Nov 19, 2015
 *
 */
@Configuration
public class MapperConfig {

    @Bean
    public DozerBeanMapper mapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        return mapper;
    }

    @PostConstruct
    public void init() {
        mapper().addMapping(new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(BaseEntity.class, BaseDto.class)
                    .fields("dateCreated", "dateCreated", copyByReference())
                    .fields("dateUpdated", "dateUpdated", copyByReference());

                //Job order mapping
                mapping(JobOrder.class, JobOrderInfo.class)
                    .fields("branch", "branchInfo")
                    .fields("dateReceived", "dateReceived", copyByReference())
                    .fields("dateDue", "dateDue", copyByReference())
                    .fields("dateCompleted", "dateCompleted", copyByReference())
                    .fields("dateClaimed", "dateClaimed", copyByReference());

                mapping(Enum.class, EnumInfo.class)
                    .fields("this", "this", FieldsMappingOptions.customConverter(EnumInfoConverter.class));
                mapping(BranchDto.class, Branch.class, TypeMappingOptions.oneWay())
                    .exclude("brand");
                mapping(Customer.class, CustomerInfo.class)
                    .fields("brand.name", "brandName", oneWay())
                    .fields("brand.code", "brandCode", oneWay());
            }
        });
    }
}
