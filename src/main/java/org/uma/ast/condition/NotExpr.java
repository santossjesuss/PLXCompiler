package org.uma.ast.condition;

import org.uma.ASTVisitor;
import org.uma.ast.ASTNode;

public class NotExpr implements Condition {
    private Condition condition;

    NotExpr(Condition condition) {
        this.condition = condition;
    }

    public ASTNode getCondition() {
        return condition;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
