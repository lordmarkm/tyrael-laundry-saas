package com.tyrael.laundry.core.service.custom.impl;

import static com.tyrael.laundry.reference.JobOrderStatus.CANCELLED;
import static com.tyrael.laundry.reference.JobOrderStatus.CLOSED;
import static com.tyrael.laundry.reference.JobOrderStatus.PAID_CLAIMED;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;

import com.mysema.query.types.expr.BooleanExpression;
import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.dto.joborder.JobOrderInfo;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustomImpl;
import com.tyrael.laundry.core.service.JobOrderService;
import com.tyrael.laundry.core.service.custom.JobOrderServiceCustom;
import com.tyrael.laundry.core.service.rql.RsqlParserVisitor;
import com.tyrael.laundry.model.joborder.JobItem;
import com.tyrael.laundry.model.joborder.JobOrder;
import com.tyrael.laundry.model.joborder.JobService;

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

    @Override
    public JobOrderInfo findByTrackinNoInfo(String trackingNo) {
        return toDto(repo.findByTrackingNo(trackingNo));
    }

    @Override
    public PageInfo<JobOrderInfo> rqlSearch(String term, Pageable pageRequest) {
        LOG.debug("Performing paginated rql search. term={}, page = {}", term, pageRequest);

        BooleanExpression predicate = null;
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

        return toDto(repo.save(jobOrder));
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
}
