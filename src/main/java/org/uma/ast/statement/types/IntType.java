package org.uma.ast.statement.types;

import org.uma.ASTVisitor;

public class IntType extends Type {
    @Override
    public String getType() {
        return "int";
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
