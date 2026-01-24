package org.uma.ast.statement.types;

import org.uma.ASTVisitor;

public class FloatType extends Type {
    @Override
    public String getType() {
        return "float";
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
