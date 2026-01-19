package org.uma.ast.expression;

import org.uma.ASTVisitor;

public class Assignment implements Expression {
    private Identifier identifier;
    private Expression value;

    Assignment(Identifier ident, Expression value) {
        identifier = ident;
        this.value = value;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public Expression getValue() {
        return value;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
