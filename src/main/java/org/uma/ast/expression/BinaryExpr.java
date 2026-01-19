package org.uma.ast.expression;

import org.uma.ASTVisitor;

public class BinaryExpr implements Expression {
    private String operator;
    private Expression left, right;

    BinaryExpr(String op, Expression left, Expression right) {
        operator = op;
        this.left = left;
        this.right = right;
    }

    public String getOperator() {
        return operator;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
