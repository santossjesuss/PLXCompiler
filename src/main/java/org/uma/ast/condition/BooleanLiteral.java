package org.uma.ast.condition;

import org.uma.ASTVisitor;
import org.uma.ast.ASTNode;

public class BooleanLiteral implements ASTNode {
    private boolean value;

    BooleanLiteral(boolean value) {
        this.value = value;
    }

    public boolean isValue() {
        return value;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
