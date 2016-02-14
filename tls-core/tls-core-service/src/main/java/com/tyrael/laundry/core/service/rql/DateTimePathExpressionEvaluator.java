package com.tyrael.laundry.core.service.rql;

import java.util.List;

import com.mysema.query.types.Path;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.path.DateTimePath;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;

/**
 * 
 * @author Mark Martinez, created Feb 14, 2016
 *
 */
public class DateTimePathExpressionEvaluator extends AbstractExpressionEvaluator {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public BooleanExpression evaluate(Path<?> path, ComparisonOperator operator, List<String> arguments) {
        DateTimePath e = (DateTimePath) path;
        List<Object> argsOfExpectedType = cast(e.getType(), arguments);
        switch (operator.getSymbol()) {
        case "==":
            return e.eq(argsOfExpectedType.get(0));
        case "!=":
            return e.ne(argsOfExpectedType.get(0));
        case "=gt=":
        case ">":
            return e.after((Comparable) argsOfExpectedType.get(0));
        case "=ge=":
        case ">=":
            return e.goe((Comparable) argsOfExpectedType.get(0));
        case "=lt=":
        case "<":
            return e.before((Comparable) argsOfExpectedType.get(0));
        case "=le=":
        case "<=":
            return e.loe((Comparable) argsOfExpectedType.get(0));
        case "=in=":
            return e.in(argsOfExpectedType);
        default:
            throw new IllegalArgumentException("Operator not supported: " + operator);
        }
    }

}
