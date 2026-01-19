package org.uma.ast.condition;

import org.uma.ASTVisitor;
import org.uma.ast.ASTNode;

public class NotExpr implements ASTNode {
    private ASTNode operand;

    NotExpr(ASTNode operand) {
        this.operand = operand;
    }

    public ASTNode getOperand() {
        return operand;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
