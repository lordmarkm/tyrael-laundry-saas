package com.tyrael.laundry.core.service.rql;

import java.util.List;

import org.apache.commons.lang3.EnumUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.mysema.query.types.Path;
import com.mysema.query.types.expr.BooleanExpression;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;

/**
 * @author mbmartinez
 */
public abstract class AbstractExpressionEvaluator {

    private static Logger LOG = LoggerFactory.getLogger(AbstractExpressionEvaluator.class);
    public abstract BooleanExpression evaluate(Path<?> path, ComparisonOperator operator, List<String> arguments);

    @SuppressWarnings("rawtypes")
    protected List cast(Class<?> type, List<String> string) {
        if (type.isAssignableFrom(String.class)) {
            return string;
        }
        List<Object> converted = Lists.newArrayList();
        for (int i = 0; i < string.size(); i++) {
            converted.add(cast(type, string.get(i)));
        }
        return converted;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected Object cast(Class<?> type, String string) {
//        LOG.debug("Trying to determine selector class. type={}", type);
        if (type.isAssignableFrom(String.class)) {
            return string;
        } else if (type.isAssignableFrom(Long.class)) {
            return Long.valueOf(string);
        } else if (type.isAssignableFrom(Boolean.class)) {
            return Boolean.valueOf(string);
        } else if (type.isAssignableFrom(DateTime.class)) {
            DateTime date = DateTime.parse(string);
            LOG.debug("Parsed date/time. value={}", date);
            return date;
        } else if (type.isEnum()) {
            return EnumUtils.getEnum((Class) type, string);
        }
        throw new IllegalArgumentException("Selector class not supported. selector class=" + type);
    }
}
