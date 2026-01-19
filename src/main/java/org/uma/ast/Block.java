package org.uma.ast;

import org.uma.ASTVisitor;
import org.uma.ast.statement.Statement;

import java.util.List;

public class Block implements ASTNode {
    private List<Statement> statements;

    public Block(List<Statement> statements) {
        this.statements = statements;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
