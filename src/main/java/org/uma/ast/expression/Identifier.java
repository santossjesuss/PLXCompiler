package org.uma.ast.expression;

import org.uma.ASTVisitor;

public class Identifier implements Expression {
    private String name;

    Identifier(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
