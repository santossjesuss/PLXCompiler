package org.uma.ast.statement;

import org.uma.ASTVisitor;
import org.uma.ast.expression.Expression;

public class ForInitExpr extends ForInit {
    Expression expression;

    public ForInitExpr(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
