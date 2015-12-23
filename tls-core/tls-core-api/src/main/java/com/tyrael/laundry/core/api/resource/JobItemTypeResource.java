package com.tyrael.laundry.core.api.resource;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.tyrael.laundry.commons.dto.EnumInfo;
import com.tyrael.laundry.reference.JobItemType;

@RestController
@RequestMapping("/jobitemtype")
public class JobItemTypeResource {

    @Autowired
    private Mapper mapper;

    @RequestMapping(method = RequestMethod.GET)
    public List<EnumInfo> findAll() {
        List<EnumInfo> infos = Lists.newArrayList();
        for (JobItemType type : JobItemType.values()) {
            infos.add(mapper.map(type, EnumInfo.class));
        }
        Collections.sort(infos, new Comparator<EnumInfo>() {
            @Override
            public int compare(EnumInfo o1, EnumInfo o2) {
                return o1.getLabel().compareTo(o2.getLabel());
            }
        });
        return infos;
    }

}
