package org.uma.ast.condition;

import org.uma.ASTVisitor;
import org.uma.ast.ASTNode;

public class Comparison implements ASTNode {
    private String operator;
    private ASTNode left, right;

    Comparison(String operator, ASTNode left, ASTNode right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public String getOperator() {
        return operator;
    }

    public ASTNode getLeft() {
        return left;
    }

    public ASTNode getRight() {
        return right;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
