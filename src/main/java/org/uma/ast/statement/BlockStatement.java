package org.uma.ast.statement;

import org.uma.ASTVisitor;
import org.uma.ast.ASTNode;

public class BlockStatement implements ASTNode {
    private ASTNode statements;

    BlockStatement(ASTNode statements) {
        this.statements = statements;
    }

    public ASTNode getStatements() {
        return statements;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
