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
import com.tyrael.laundry.commons.dto.acctspayable.AccountsPayableInfo;
import com.tyrael.laundry.commons.dto.acctspayable.AccountsPaymentInfo;
import com.tyrael.laundry.commons.dto.customer.CustomerInfo;
import com.tyrael.laundry.commons.dto.inventory.InventoryItemInfo;
import com.tyrael.laundry.commons.dto.inventory.InventoryItemTypeInfo;
import com.tyrael.laundry.commons.dto.inventory.SalesItemInfo;
import com.tyrael.laundry.commons.dto.joborder.JobItemInfo;
import com.tyrael.laundry.commons.dto.joborder.JobOrderInfo;
import com.tyrael.laundry.commons.dto.joborder.ServiceTypeInfo;
import com.tyrael.laundry.commons.model.BaseEntity;
import com.tyrael.laundry.converter.EnumInfoConverter;
import com.tyrael.laundry.model.acctspayable.AccountsPayable;
import com.tyrael.laundry.model.acctspayable.AccountsPayment;
import com.tyrael.laundry.model.branch.Branch;
import com.tyrael.laundry.model.customer.Customer;
import com.tyrael.laundry.model.inventory.InventoryItem;
import com.tyrael.laundry.model.inventory.InventoryItemType;
import com.tyrael.laundry.model.inventory.SalesItem;
import com.tyrael.laundry.model.joborder.JobItem;
import com.tyrael.laundry.model.joborder.JobOrder;
import com.tyrael.laundry.model.joborder.ServiceType;

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

                //Branch mapping
                mapping(BranchDto.class, Branch.class, TypeMappingOptions.oneWay())
                    .exclude("brand");
                mapping(Branch.class, BranchDto.class, TypeMappingOptions.oneWay())
                    .fields("brand.name", "brandName")
                    .fields("brand.code", "brandCode");

                mapping(Customer.class, CustomerInfo.class)
                    .fields("brand.name", "brandName", oneWay())
                    .fields("brand.code", "brandCode", oneWay());

                mapping(JobItem.class, JobItemInfo.class)
                    .fields("jobItemType.iconPath", "iconPath", oneWay());
                mapping(ServiceType.class, ServiceTypeInfo.class)
                    .fields("branch.id", "branchId", oneWay())
                    .fields("branch.name", "branchName", oneWay())
                    .fields("branch.brand.name", "brandName", oneWay());

                //Inventory mapping
                mapping(InventoryItemType.class, InventoryItemTypeInfo.class)
                    .fields("brand.name", "brandName", oneWay())
                    .fields("brand.code", "brandCode", oneWay());
                mapping(InventoryItem.class, InventoryItemInfo.class, TypeMappingOptions.oneWay())
                    .fields("branch.brand.name", "brandName")
                    .fields("branch.brand.code", "brandCode")
                    .fields("branch.name", "branchName")
                    .fields("branch.code", "branchCode")
                    .fields("itemType.name", "inventoryItemTypeName")
                    .fields("itemType.code", "inventoryItemTypeCode")
                    .fields("itemType.uom", "uom");
                mapping(SalesItemInfo.class, SalesItem.class, TypeMappingOptions.oneWay())
                    .fields("inventoryItem.inventoryItemTypeName", "inventoryItemName")
                    .fields("inventoryItem.sellingPrice", "pricePerItem");

                //Accounts payable mapping
                mapping(AccountsPayable.class, AccountsPayableInfo.class)
                    .fields("branch.code", "branchCode", oneWay())
                    .fields("branch.name", "branchName", oneWay())
                    .fields("branch.brand.name", "brandName", oneWay())
                    .fields("lastPayment", "lastPayment", copyByReference());
                mapping(AccountsPayment.class, AccountsPaymentInfo.class)
                    .fields("paymentDate", "paymentDate", copyByReference())
                    .fields("accountsPayable.name", "accountsPayableName", oneWay())
                    .fields("accountspayable.branch.name", "branchName", oneWay());
            }
        });
    }
}
