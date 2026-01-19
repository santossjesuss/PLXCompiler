package org.uma.ast.statement;

import org.uma.ASTVisitor;

public class CharType extends Type{
    @Override
    public String getType() {
        return "char";
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
