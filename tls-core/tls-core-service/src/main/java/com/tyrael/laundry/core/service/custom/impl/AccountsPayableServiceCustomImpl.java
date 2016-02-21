package com.tyrael.laundry.core.service.custom.impl;

import static com.tyrael.laundry.model.acctspayable.QAccountsPayable.accountsPayable;
import static com.tyrael.laundry.model.acctspayable.QAccountsPayment.accountsPayment;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.mysema.query.types.expr.BooleanExpression;
import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.dto.acctspayable.AccountsPayableInfo;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustomImpl;
import com.tyrael.laundry.commons.util.AuthenticationUtil;
import com.tyrael.laundry.core.service.AccountsPayableService;
import com.tyrael.laundry.core.service.BranchService;
import com.tyrael.laundry.core.service.BrandService;
import com.tyrael.laundry.core.service.custom.AccountsPayableServiceCustom;
import com.tyrael.laundry.core.service.rql.RsqlParserVisitor;
import com.tyrael.laundry.model.acctspayable.AccountsPayable;
import com.tyrael.laundry.model.branch.Branch;
import com.tyrael.laundry.model.branch.Brand;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
@Transactional
public class AccountsPayableServiceCustomImpl
    extends TyraelJpaServiceCustomImpl<AccountsPayable, AccountsPayableInfo, AccountsPayableService>
    implements AccountsPayableServiceCustom {

    private static Logger LOG = LoggerFactory.getLogger(AccountsPayableServiceCustomImpl.class);

    @Autowired
    private BrandService brandService;

    @Autowired
    private BranchService branchService;

    private BooleanExpression addBrandFilter(final BooleanExpression predicate) {
        if (AuthenticationUtil.isAuthorized(AuthenticationUtil.ROLE_ADMIN)) {
            return predicate;
        } else {
            List<Brand> brands = brandService.findByUserUsername(AuthenticationUtil.getLoggedInUsername());
            return predicate.and(accountsPayable.branch.brand.in(brands));
        }
    }

    @Override
    public PageInfo<AccountsPayableInfo> rqlSearch(String term, Pageable pageRequest) {
        LOG.debug("Performing paginated rql search. term={}, page = {}", term, pageRequest);

        BooleanExpression predicate = accountsPayable.deleted.isFalse();

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
    public PageInfo<AccountsPayableInfo> pageInfo(Pageable page) {
        BooleanExpression query = accountsPayable.deleted.isFalse();
        query = addBrandFilter(query);
        return super.pageInfo(query, page);
    }

    @Override
    public AccountsPayableInfo saveInfo(AccountsPayableInfo dto) {
        if (null == dto.getCode()) {
            //CREATE operation, Generate random candidate ap codes until we find a unique one
            AccountsPayable existing = null;
            String candidateCode;
            do {
                candidateCode = RandomStringUtils.randomAlphanumeric(5).toLowerCase();
                existing = repo.findByCode(candidateCode);
            } while (null != existing);
    
            //Use the generated code as default customer code
            dto.setCode(candidateCode);

            AccountsPayable entity = toEntity(dto);

            //Assign the ap to the set branch
            Branch branch = branchService.findByCode(dto.getBranchCode());
            Preconditions.checkNotNull(branch);
            entity.setBranch(branch);

            return toDto(repo.save(entity));
        } else {
            //UPDATE operation
            AccountsPayable existing = repo.findByCode(dto.getCode());
            Preconditions.checkNotNull(existing);
            mapper.map(dto, existing);

            //Assign the customer to the set brand
            Branch branch = branchService.findByCode(dto.getBranchCode());
            Preconditions.checkNotNull(branch);
            existing.setBranch(branch);

            return toDto(existing);
        }
    }

    @Override
    public AccountsPayableInfo findInfoByCode(String customerCode) {
        return toDto(repo.findByCode(customerCode));
    }

}
