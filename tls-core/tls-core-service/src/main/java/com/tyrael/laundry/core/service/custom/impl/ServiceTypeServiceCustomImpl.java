package com.tyrael.laundry.core.service.custom.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;

import com.google.common.collect.Lists;
import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.dto.joborder.ServiceTypeInfo;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustomImpl;
import com.tyrael.laundry.core.service.BranchService;
import com.tyrael.laundry.core.service.ServiceTypeService;
import com.tyrael.laundry.core.service.custom.ServiceTypeServiceCustom;
import com.tyrael.laundry.model.joborder.ServiceType;

/**
 * @author mbmartinez
 */
public class ServiceTypeServiceCustomImpl 
    extends TyraelJpaServiceCustomImpl<ServiceType, ServiceTypeInfo, ServiceTypeService>
    implements ServiceTypeServiceCustom {

    private static Logger LOG = LoggerFactory.getLogger(ServiceTypeServiceCustomImpl.class);
    private static final String CODE = "CODE";
    private static final String LABEL = "LABEL";
    private static final String ICON = "ICON";
    private static final String PPK = "PRICE_PER_KILO";

    @Autowired
    private BranchService branchService;

    @PostConstruct
    public void init() throws IOException {
        if (repo.findAll().size() > 0) {
            return;
        }

        LOG.debug("Loading service types from csv.");

        Resource resource = new ClassPathResource("ref_servicetypes");
        for (CSVRecord record : CSVFormat.RFC4180.withHeader().withDelimiter(',').parse(new InputStreamReader(resource.getInputStream()))) {
            ServiceType servicetype = new ServiceType();
            servicetype.setCode(record.get(CODE));
            servicetype.setLabel(record.get(LABEL));
            servicetype.setIcon(record.get(ICON));
            servicetype.setPricePerKilo(new BigDecimal(record.get(PPK)));
            servicetype.setEnabled(true);
            servicetype.setDeleted(false);
            repo.save(servicetype);
        }

    }

    @Override
    public PageInfo<ServiceTypeInfo> findInfoByBranchCodeAndEnabled(String branchCode, Pageable page) {
        LOG.debug("Find info by enabled. page={}", page);
        return toPageInfo(repo.findByBranchCodeAndEnabled(branchCode, true, page));
    }

    /**
     * Override batch save in order to set branch ids
     */
    @Override
    public List<ServiceTypeInfo> saveInfo(Iterable<ServiceTypeInfo> dtos) {
        List<ServiceType> serviceTypes = Lists.newArrayList();
        for (ServiceTypeInfo stInfo : dtos) {
            ServiceType st = toEntity(stInfo);
            if (null != stInfo.getBranchId()) {
                st.setBranch(branchService.findOne(stInfo.getBranchId()));
            }
            serviceTypes.add(st);
        }
        return toDto(repo.save(serviceTypes));
    }
}
