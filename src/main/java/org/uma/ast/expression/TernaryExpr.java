package org.uma.ast.expression;

import org.uma.ASTVisitor;
import org.uma.ast.condition.Condition;

public class TernaryExpr implements Expression {
    private Condition condition;
    private Expression trueBranch, falseBranch;

    TernaryExpr(Condition condition, Expression trueBranch, Expression falseBranch) {
        this.condition = condition;
        this.trueBranch = trueBranch;
        this.falseBranch = falseBranch;
    }

    public Condition getCondition() {
        return condition;
    }

    public Expression getTrueBranch() {
        return trueBranch;
    }

    public Expression getFalseBranch() {
        return falseBranch;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
