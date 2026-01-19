package org.uma.ast.expression;

import org.uma.ASTVisitor;

public class CharacterLiteral implements Expression {
    private char value;

    CharacterLiteral(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
