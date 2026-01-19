package org.uma.ast.expression;

import org.uma.ASTVisitor;

public class IntegerLiteral implements Expression {
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
