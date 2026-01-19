package org.uma.ast.expression;

import org.uma.ASTVisitor;
import org.uma.ast.ASTNode;

public class PostIncrement implements ASTNode {
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
