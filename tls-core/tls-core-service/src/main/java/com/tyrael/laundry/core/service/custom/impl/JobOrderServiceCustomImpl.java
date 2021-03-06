package com.tyrael.laundry.core.service.custom.impl;

import static com.tyrael.laundry.model.joborder.QJobOrder.jobOrder;
import static com.tyrael.laundry.reference.JobOrderStatus.CANCELLED;
import static com.tyrael.laundry.reference.JobOrderStatus.CLOSED;
import static com.tyrael.laundry.reference.JobOrderStatus.PAID_CLAIMED;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mysema.query.types.expr.BooleanExpression;
import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.dto.joborder.JobOrderInfo;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustomImpl;
import com.tyrael.laundry.commons.util.AuthenticationUtil;
import com.tyrael.laundry.core.service.BrandService;
import com.tyrael.laundry.core.service.CustomerService;
import com.tyrael.laundry.core.service.JobOrderService;
import com.tyrael.laundry.core.service.custom.JobOrderServiceCustom;
import com.tyrael.laundry.core.service.rql.RsqlParserVisitor;
import com.tyrael.laundry.model.branch.Brand;
import com.tyrael.laundry.model.customer.Customer;
import com.tyrael.laundry.model.joborder.JobItem;
import com.tyrael.laundry.model.joborder.JobOrder;
import com.tyrael.laundry.model.joborder.JobService;
import com.tyrael.laundry.reference.JobOrderStatus;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;

/**
 * 
 * @author Mark Martinez, create Dec 21, 2015
 *
 */
public class JobOrderServiceCustomImpl extends TyraelJpaServiceCustomImpl<JobOrder, JobOrderInfo, JobOrderService>
    implements JobOrderServiceCustom {

    private static final Logger LOG = LoggerFactory.getLogger(JobOrderServiceCustomImpl.class);

    @Autowired
    private BrandService brandService;

    @Autowired
    private CustomerService customerService;

    private BooleanExpression addBrandFilter(final BooleanExpression predicate) {
        if (!AuthenticationUtil.isAuthenticated() || AuthenticationUtil.isAuthorized(AuthenticationUtil.ROLE_ADMIN)) {
            //Ironically, admin and unauthenticated users have full access to all job orders
            return predicate;
        } else {
            List<Brand> brands = brandService.findByUserUsername(AuthenticationUtil.getLoggedInUsername());
            return CollectionUtils.isEmpty(brands) ? predicate : predicate.and(jobOrder.branch.brand.in(brands));
        }
    }

    @Override
    public PageInfo<JobOrderInfo> pageInfo(Pageable page) {
        BooleanExpression query = jobOrder.deleted.isFalse();
        query = addBrandFilter(query);
        Page<JobOrder> results = repo.findAll(query, page);
        return toPageInfo(results);
    }

    @Override
    public JobOrderInfo findByTrackinNoInfo(String trackingNo) {
        LOG.debug("Find by tracking no. trackingNo={}", trackingNo);
        return toDto(repo.findByTrackingNo(trackingNo));
    }

    @Override
    public PageInfo<JobOrderInfo> rqlSearch(String term, Pageable pageRequest) {
        LOG.debug("Performing paginated rql search. term={}, page = {}", term, pageRequest);

        BooleanExpression predicate = jobOrder.deleted.isFalse();

        if (!StringUtils.isBlank(term)) {
            try {
                Node rootNode = new RSQLParser().parse(term);
                RsqlParserVisitor visitor = new RsqlParserVisitor();
                predicate = rootNode.accept(visitor, FIELD_MAPPING);
            } catch (Exception e) {
                LOG.error("Error parsing or interpreting rql term. term={}, error={}", term, e.getMessage());
                return PageInfo.blank();
            }
        }

        predicate = addBrandFilter(predicate);

        return super.pageInfo(predicate, pageRequest);
    }

    @Override
    public JobOrderInfo saveInfo(JobOrderInfo jobOrderInfo) {
        if (null == jobOrderInfo.getTrackingNo()) {
            jobOrderInfo.setDateReceived(DateTime.now());
            jobOrderInfo.setDateDue(jobOrderInfo.getDateReceived().plusDays(3));
            jobOrderInfo.setTrackingNo(uniqueJobCode(jobOrderInfo));
        }
        if (null == jobOrderInfo.getDateCompleted() 
                && (jobOrderInfo.getStatus() == CLOSED) || jobOrderInfo.getStatus() == CANCELLED) {
            jobOrderInfo.setDateCompleted(DateTime.now());
        }
        if (null == jobOrderInfo.getDateClaimed()
                && jobOrderInfo.getStatus() == PAID_CLAIMED) {
            jobOrderInfo.setDateClaimed(DateTime.now());
        }

        JobOrder jobOrder = toEntity(jobOrderInfo);

        for (JobService service : jobOrder.getJobServices()) {
            service.setJobOrder(jobOrder);
        }
        for (JobItem item : jobOrder.getJobItems()) {
            item.setJobOrder(jobOrder);
        }

        jobOrder = repo.save(jobOrder);

        Customer customer = customerService.findOne(jobOrder.getCustomer().getId());
        BigDecimal balance = repo.computeBalance(customer);
        customer.setBalance(balance);

        return toDto(jobOrder);
    }

    private String uniqueJobCode(JobOrderInfo jobOrderInfo) {
        String jobCode = null;
        while (null == jobCode) {
            StringBuilder jobCodeBuilder = new StringBuilder();
            if (null != jobOrderInfo.getBranchInfo()) {
                jobCodeBuilder.append(jobOrderInfo.getBranchInfo().getCode());
            }
            jobCodeBuilder.append(RandomStringUtils.randomAlphabetic(5).toLowerCase());
            if (null == repo.findByTrackingNo(jobCodeBuilder.toString())) {
                jobCode = jobCodeBuilder.toString();
            }
        }
        return jobCode;
    }

    @Override
    public int countNew() {
        BooleanExpression predicate = jobOrder.deleted.isFalse();
        predicate = predicate.and(jobOrder.status.eq(JobOrderStatus.NEW));
        predicate = addBrandFilter(predicate);
        return (int) repo.count(predicate);
    }

    @Override
    public JobOrderInfo deleteInfo(Long id) {
        JobOrderInfo deleted = super.deleteInfo(id);

        Customer customer = customerService.findOne(deleted.getCustomer().getId());
        BigDecimal balance = repo.computeBalance(customer);
        customer.setBalance(balance);

        return deleted;
    }
}
