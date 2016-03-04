package com.tyrael.laundry.pos.service.custom.impl;

import static com.tyrael.laundry.model.inventory.QSalesHeader.salesHeader;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import com.google.common.base.Preconditions;
import com.mysema.query.types.expr.BooleanExpression;
import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.dto.inventory.SalesHeaderInfo;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustomImpl;
import com.tyrael.laundry.commons.util.AuthenticationUtil;
import com.tyrael.laundry.commons.util.MathUtil;
import com.tyrael.laundry.core.service.BrandService;
import com.tyrael.laundry.core.service.rql.RsqlParserVisitor;
import com.tyrael.laundry.model.branch.Brand;
import com.tyrael.laundry.model.inventory.InventoryItem;
import com.tyrael.laundry.model.inventory.SalesHeader;
import com.tyrael.laundry.model.inventory.SalesItem;
import com.tyrael.laundry.pos.service.InventoryItemService;
import com.tyrael.laundry.pos.service.SalesHeaderService;
import com.tyrael.laundry.pos.service.custom.SalesHeaderServiceCustom;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;

/**
 * 
 * @author Mark Martinez, create Feb 10, 2016
 *
 */
public class SalesHeaderServiceCustomImpl
    extends TyraelJpaServiceCustomImpl<SalesHeader, SalesHeaderInfo, SalesHeaderService>
    implements SalesHeaderServiceCustom {

    private static final Logger LOG = LoggerFactory.getLogger(SalesHeaderServiceCustomImpl.class);

    @Autowired
    private InventoryItemService inventoryItemService;

    @Autowired
    private BrandService brandService;

    private BooleanExpression addBrandFilter(final BooleanExpression predicate) {
        if (AuthenticationUtil.isAuthorized(AuthenticationUtil.ROLE_ADMIN)) {
            return predicate;
        } else {
            List<Brand> brands = brandService.findByUserUsername(AuthenticationUtil.getLoggedInUsername());
            return predicate.and(salesHeader.branch.brand.in(brands));
        }
    }

    @Override
    public PageInfo<SalesHeaderInfo> pageInfo(Pageable page) {
        BooleanExpression query = salesHeader.deleted.isFalse();
        query = addBrandFilter(query);
        return super.pageInfo(query, page);
    }

    @Override
    public PageInfo<SalesHeaderInfo> rqlSearch(String term, Pageable pageRequest) {
        LOG.debug("Performing paginated rql search. term={}, page = {}", term, pageRequest);

        BooleanExpression predicate = salesHeader.deleted.isFalse();

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
    public SalesHeaderInfo saveInfo(SalesHeaderInfo salesHeaderInfo) {
        
        SalesHeader salesHeader = toEntity(salesHeaderInfo);
        setBranch(salesHeader);
        doCalculations(salesHeader);

        return toDto(repo.save(salesHeader));
    }

    private void setBranch(SalesHeader salesHeader) {
        //This method assumes that a sales header can only be from a single branch
        SalesItem firstItem = salesHeader.getItems().get(0);
        InventoryItem invItem = inventoryItemService.findOne(firstItem.getInventoryItem().getId());
        Preconditions.checkNotNull(invItem);

        salesHeader.setBranch(invItem.getBranch());
    }

    private void doCalculations(SalesHeader salesHeader) {
        BigDecimal totalAmountPaid = BigDecimal.ZERO;
        for (SalesItem salesItem : salesHeader.getItems()) {
            //Calculate line price
            BigDecimal sellingPrice = salesItem.getInventoryItem().getSellingPrice();
            BigDecimal salesItemAmount = sellingPrice.multiply(salesItem.getQuantity());
            salesItem.setAmount(salesItemAmount);

            //Add line price to invoice amount
            totalAmountPaid = totalAmountPaid.add(salesItemAmount);

            //Deduct sold quantity from inventory items (should auto persist still)
            InventoryItem inventoryItem = inventoryItemService.findOne(salesItem.getInventoryItem().getId());
            if (MathUtil.lt(inventoryItem.getQuantity(), salesItem.getQuantity())) {
                throw new IllegalArgumentException("Sales item quantity is greater than available quantity!");
            }
            inventoryItem.setQuantity(inventoryItem.getQuantity().subtract(salesItem.getQuantity()));

            //Some housekeeping
            salesItem.setUom(inventoryItem.getItemType().getUom());
            salesItem.setSalesHeader(salesHeader);
        }

        salesHeader.setTotalAmountPaid(totalAmountPaid);
    }

    @Override
    public int salesToday() {
        DateTime today = DateTime.now();
        BooleanExpression predicate = salesHeader.dateCreated.year().eq(today.getYear())
            .and(salesHeader.dateCreated.dayOfYear().eq(today.getDayOfYear()));
        predicate = predicate.and(salesHeader.deleted.isFalse());
        predicate = addBrandFilter(predicate);

        return (int) repo.count(predicate);
    }

}
