package org.uma.ast.condition;

import org.uma.ASTVisitor;

public class Comparison implements Condition {
    private String operator;
    private Condition left, right;

    Comparison(String operator, Condition left, Condition right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public String getOperator() {
        return operator;
    }

    public Condition getLeft() {
        return left;
    }

    public Condition getRight() {
        return right;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
