package org.uma.ast.expression;

import org.uma.ASTVisitor;

public class PostIncrement implements Expression {
    private Identifier identifier;

    PostIncrement(Identifier identifier) {
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
