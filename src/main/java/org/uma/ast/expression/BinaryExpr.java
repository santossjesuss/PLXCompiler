package org.uma.ast.expression;

import org.uma.ASTVisitor;
import org.uma.ast.ASTNode;

public class BinaryExpr implements ASTNode {
    private String operator;
    private ASTNode left, right;

    BinaryExpr(String op, ASTNode left, ASTNode right) {
        operator = op;
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
