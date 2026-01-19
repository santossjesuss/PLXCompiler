package org.uma.ast.expression;

import org.uma.ASTVisitor;
import org.uma.ast.ASTNode;

public class Assignment implements ASTNode {
    private String identifier;
    private ASTNode value;

    Assignment(String ident, ASTNode value) {
        identifier = ident;
        this.value = value;
    }

    public String getIdentifier() {
        return identifier;
    }

    public ASTNode getValue() {
        return value;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
