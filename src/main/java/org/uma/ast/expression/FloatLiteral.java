package org.uma.ast.expression;

import org.uma.ASTVisitor;

public class FloatLiteral implements Expression {
    private float value;

    FloatLiteral(float value) {
        this.value = value;
    }

    FloatLiteral(String value) {
        this.value = Float.parseFloat(value);
    }

    public float getValue() {
        return value;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
