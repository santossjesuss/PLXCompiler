package org.uma.ast.expression;

import org.uma.ASTVisitor;

public class PreIncrement implements Expression {
    private Identifier identifier;

    PreIncrement(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
