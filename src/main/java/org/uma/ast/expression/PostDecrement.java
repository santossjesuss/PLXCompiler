package org.uma.ast.expression;

import org.uma.ASTVisitor;

public class PostDecrement implements Expression {
    private Identifier identifier;

    PostDecrement(Identifier identifier) {
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
