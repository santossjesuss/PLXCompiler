package org.uma.ast.statement;

import org.uma.ast.expression.Expression;

public class ForInitExpr extends ForInit {
    Expression expression;

    public ForInitExpr(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }
}
