package org.uma.ast.expression;

import org.uma.ASTVisitor;
import org.uma.ast.ASTNode;

public class TernaryExpr implements ASTNode {
    private ASTNode condition;
    private ASTNode trueBranch, falseBranch;

    TernaryExpr(ASTNode condition, ASTNode trueBranch, ASTNode falseBranch) {
        this.condition = condition;
        this.trueBranch = trueBranch;
        this.falseBranch = falseBranch;
    }

    public ASTNode getCondition() {
        return condition;
    }

    public ASTNode getTrueBranch() {
        return trueBranch;
    }

    public ASTNode getFalseBranch() {
        return falseBranch;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
