package org.uma.ast.expression;

import org.uma.ASTVisitor;
import org.uma.ast.ASTNode;

public class UnaryExpr implements ASTNode {
    private String operator;
    private ASTNode operand;

    UnaryExpr(String operator, ASTNode operand) {
        this.operator = operator;
        this.operand = operand;
    }

    public String getOperator() {
        return operator;
    }

    public ASTNode getOperand() {
        return operand;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
