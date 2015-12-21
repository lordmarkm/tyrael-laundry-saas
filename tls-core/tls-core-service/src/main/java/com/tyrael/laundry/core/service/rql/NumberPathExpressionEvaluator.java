package com.tyrael.laundry.core.service.rql;

import java.util.List;

import com.mysema.query.types.Path;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.path.NumberPath;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;

/**
 * @author mbmartinez
 */
public class NumberPathExpressionEvaluator extends AbstractExpressionEvaluator {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public BooleanExpression evaluate(Path<?> path, ComparisonOperator operator, List<String> arguments) {
        NumberPath e = (NumberPath) path;
        List<Object> argsOfExpectedType = cast(e.getType(), arguments);
        switch (operator.getSymbol()) {
        case "==":
            return e.eq(argsOfExpectedType.get(0));
        case "!=":
            return e.ne(argsOfExpectedType.get(0));
        case "=gt=":
            return e.gt((Number) argsOfExpectedType.get(0));
        case "=ge=":
            return e.goe((Number) argsOfExpectedType.get(0));
        case "=lt=":
            return e.lt((Number) argsOfExpectedType.get(0));
        case "=le=":
            return e.loe((Number) argsOfExpectedType.get(0));
        case "=in=":
            return e.in(argsOfExpectedType);
        default:
            throw new IllegalArgumentException("Operator not supported: " + operator);
        }
    }

}
