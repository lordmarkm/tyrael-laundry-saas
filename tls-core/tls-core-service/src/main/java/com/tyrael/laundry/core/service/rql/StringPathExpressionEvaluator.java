package com.tyrael.laundry.core.service.rql;

import java.util.List;

import com.mysema.query.types.Path;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.expr.StringExpression;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;

public class StringPathExpressionEvaluator extends AbstractExpressionEvaluator {

    @SuppressWarnings("unchecked")
    public BooleanExpression evaluate(Path<?> path, ComparisonOperator operator, List<String> arguments) {
        StringExpression e = (StringExpression) path;
        List<String> argsOfExpectedType = cast(e.getType(), arguments);
        switch (operator.getSymbol()) {
        case "==":
            String term = argsOfExpectedType.get(0);
            if (term.endsWith("*")) {
                term = term.substring(0, term.length() - 1);
                return e.startsWithIgnoreCase(term);
            } else {
                return e.equalsIgnoreCase(term);
            }
        case "!=":
            return e.ne(argsOfExpectedType.get(0));
        case "=in=":
            return e.in(argsOfExpectedType);
        case "=out=":
            return e.notIn(argsOfExpectedType);
        default:
            throw new IllegalArgumentException("Operator not supported: " + operator);
        }
    }

}
