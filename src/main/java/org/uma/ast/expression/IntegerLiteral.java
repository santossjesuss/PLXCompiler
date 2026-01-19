package org.uma.ast.expression;

import org.uma.ASTVisitor;
import org.uma.ast.ASTNode;

public class IntegerLiteral implements ASTNode {
    private int value;

    IntegerLiteral(int value) {
        this.value = value;
    }

    IntegerLiteral(String value) {
        this.value = Integer.parseInt(value);
    }

    public int getValue() {
        return value;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
