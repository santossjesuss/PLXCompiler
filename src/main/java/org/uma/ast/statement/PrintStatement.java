package org.uma.ast.statement;

import org.uma.ASTVisitor;
import org.uma.ast.expression.Expression;

public class PrintStatement implements Statement {
    private Expression expr;

    PrintStatement(Expression expr) {
        this.expr = expr;
    }

    public Expression getExpr() {
        return expr;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
