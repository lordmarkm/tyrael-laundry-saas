package com.tyrael.laundry.converter;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.tyrael.laundry.commons.dto.joborder.JobItemInfo;
import com.tyrael.laundry.commons.dto.joborder.JobOrderInfo;
import com.tyrael.laundry.commons.dto.joborder.JobServiceInfo;
import com.tyrael.laundry.core.service.BranchService;
import com.tyrael.laundry.core.service.CustomerService;
import com.tyrael.laundry.core.service.JobOrderService;
import com.tyrael.laundry.model.branch.Branch;
import com.tyrael.laundry.model.customer.Customer;
import com.tyrael.laundry.model.joborder.JobItem;
import com.tyrael.laundry.model.joborder.JobOrder;
import com.tyrael.laundry.model.joborder.JobService;

/**
 *
 * @author Mark Martinez, create Dec 22, 2015
 *
 */
@Component
public class JobOrderConverter extends DozerConverter<JobOrderInfo, JobOrder> {

    @Autowired
    private JobOrderService service;

    @Autowired
    private BranchService branchService;

    @Autowired
    private CustomerService customerService;

    private Mapper mapper = new DozerBeanMapper();

    public JobOrderConverter() {
        super(JobOrderInfo.class, JobOrder.class);
    }

    @Override
    public JobOrder convertTo(JobOrderInfo source, JobOrder destination) {
        JobOrder j = service.findByTrackingNo(source.getTrackingNo());
        if (null == j) {
            j = new JobOrder();
            j.setTrackingNo(source.getTrackingNo());
        }

        j.setJobServices(toJobServices(source.getJobServices()));
        j.setJobItems(toJobItems(source.getJobItems()));

        //Set customer
        Customer customer = customerService.findByCode(source.getCustomer().getCode());
        j.setCustomer(customer);
        
        //Set branch
        Branch branch = branchService.findByCode(source.getBranchInfo().getCode());
        j.setBranch(branch);

        j.setTotalAmount(source.getTotalAmount());
        j.setTotalAmountPaid(source.getTotalAmountPaid());
        j.setStatus(source.getStatus());

        return j;
    }

    private List<JobItem> toJobItems(List<JobItemInfo> jobItemInfos) {
        List<JobItem> jobItems = Lists.newArrayList();
        mapper.map(jobItemInfos, jobItems);
        return jobItems;
    }

//    private List<JobService> toJobServices(List<JobServiceInfo> jobServiceInfos, List<JobService> jobServices) {
//        Map<String, JobService> jsMap = Maps.newHashMap();
//
//        //Keep a list of active job services. Job services not here should be deleted.
//        List<JobService> activeJobServices = Lists.newArrayList();
//
//        //Construct a map of existing job services
//        for (JobService js : jobServices) {
//            jsMap.put(js.getServiceType().getCode(), js);
//        }
//
//        //Map new job services to existing job services, creating new if necessary
//        for (JobServiceInfo jsInfo : jobServiceInfos) {
//            JobService js = jsMap.remove(jsInfo.getServiceType().getCode());
//            if (null != js) {
//                mapper.map(jsInfo, js);
//            } else {
//                js = mapper.map(jsInfo, JobService.class);
//                jsMap.put(js.getServiceType().getCode(), js);
//            }
//            activeJobServices.add(js);
//        }
//
//        return activeJobServices;
//    }

    private List<JobService> toJobServices(List<JobServiceInfo> jobServiceInfos) {
        List<JobService> jobServices = Lists.newArrayList();
        mapper.map(jobServiceInfos, jobServices);
        return jobServices;
    }

    @Override
    public JobOrderInfo convertFrom(JobOrder source, JobOrderInfo destination) {
        throw new IllegalStateException("Reverse operation not supported!");
    }


}
