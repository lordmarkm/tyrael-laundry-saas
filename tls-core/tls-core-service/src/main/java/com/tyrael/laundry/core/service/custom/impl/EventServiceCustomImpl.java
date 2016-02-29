package com.tyrael.laundry.core.service.custom.impl;

import static com.tyrael.laundry.model.event.QTlsEvent.tlsEvent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import com.google.common.collect.Lists;
import com.mysema.query.types.expr.BooleanExpression;
import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.dto.event.EventDto;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustomImpl;
import com.tyrael.laundry.commons.util.AuthenticationUtil;
import com.tyrael.laundry.core.service.BrandService;
import com.tyrael.laundry.core.service.EventService;
import com.tyrael.laundry.core.service.custom.EventServiceCustom;
import com.tyrael.laundry.model.branch.Brand;
import com.tyrael.laundry.model.event.TlsEvent;
import com.tyrael.laundry.model.user.User;
import com.tyrael.laundry.reference.EventType;

public class EventServiceCustomImpl
    extends TyraelJpaServiceCustomImpl<TlsEvent, EventDto, EventService>
    implements EventServiceCustom {

    @Autowired
    private BrandService brandService;

    private BooleanExpression addBrandFilter(BooleanExpression predicate) {
        if (AuthenticationUtil.isAuthorized(AuthenticationUtil.ROLE_ADMIN)) {
            return predicate;
        } else {
            List<String> usernames = Lists.newArrayList();
            List<Brand> brands = brandService.findByUserUsername(AuthenticationUtil.getLoggedInUsername());
            for (Brand brand : brands) {
                for (User user : brand.getUsers()) {
                    usernames.add(user.getName());
                }
            }
            return predicate.and(tlsEvent.createdBy.in(usernames));
        }
    }

    @Override
    public PageInfo<EventDto> findInfoByType(EventType type, Pageable pageRequest) {
        BooleanExpression predicate = tlsEvent.deleted.isFalse();
        if (null != type) {
            predicate = predicate.and(tlsEvent.eventType.eq(type.name()));
        }
        predicate = addBrandFilter(predicate);

        return super.pageInfo(predicate, pageRequest);
    }

}
