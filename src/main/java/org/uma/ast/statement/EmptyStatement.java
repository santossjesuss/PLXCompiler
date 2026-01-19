package org.uma.ast.statement;

import org.uma.ASTVisitor;

public class EmptyStatement implements Statement {
    public EmptyStatement() {}

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return null;
    }
}
