package org.uma.ast.statement;

import org.uma.ASTVisitor;
import org.uma.ast.expression.Expression;

public class ExpressionStatement implements Statement {
    private Expression expression;

    public ExpressionStatement(Expression expression) {
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
