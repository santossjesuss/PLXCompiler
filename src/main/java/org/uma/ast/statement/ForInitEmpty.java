package org.uma.ast.statement;

import org.uma.ASTVisitor;

public class ForInitEmpty extends ForInit {
    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
