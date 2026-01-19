package org.uma.ast.statement;

import org.uma.ASTVisitor;

public class UserType extends Type {
    private String name;

    UserType(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return name;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
