package com.tyrael.laundry.core.service.custom.impl;

import static com.tyrael.laundry.model.acctspayable.QAccountsPayment.accountsPayment;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.mysema.query.types.expr.BooleanExpression;
import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.dto.acctspayable.AccountsPaymentInfo;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustomImpl;
import com.tyrael.laundry.core.service.AccountsPayableService;
import com.tyrael.laundry.core.service.AccountsPaymentService;
import com.tyrael.laundry.core.service.custom.AccountsPaymentServiceCustom;
import com.tyrael.laundry.core.service.rql.RsqlParserVisitor;
import com.tyrael.laundry.model.acctspayable.AccountsPayable;
import com.tyrael.laundry.model.acctspayable.AccountsPayment;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
@Transactional
public class AccountsPaymentServiceCustomImpl
    extends TyraelJpaServiceCustomImpl<AccountsPayment, AccountsPaymentInfo, AccountsPaymentService>
    implements AccountsPaymentServiceCustom {

    private static final Logger LOG = LoggerFactory.getLogger(AccountsPaymentServiceCustomImpl.class);

    @Autowired
    private AccountsPayableService apService;

    @Override
    public PageInfo<AccountsPaymentInfo> rqlSearch(String term, Pageable pageRequest) {
        LOG.debug("Performing paginated rql search. term={}, page = {}", term, pageRequest);

        BooleanExpression predicate = accountsPayment.deleted.isFalse();

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
    public AccountsPaymentInfo saveInfo(AccountsPaymentInfo dto) {
        AccountsPayment entity = toEntity(dto);

        //Assign the ap to the set branch
        AccountsPayable accountsPayable = apService.findByCode(dto.getAccountsPayableCode());
        Preconditions.checkNotNull(accountsPayable);
        if (null == accountsPayable.getLastPayment() || entity.getPaymentDate().isAfter(accountsPayable.getLastPayment())) {
            accountsPayable.setLastPayment(entity.getPaymentDate());
        }

        entity.setAccountsPayable(accountsPayable);

        return toDto(repo.save(entity));
    }

}
