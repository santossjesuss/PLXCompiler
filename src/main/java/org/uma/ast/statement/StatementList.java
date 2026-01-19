package org.uma.ast.statement;

import org.uma.ASTVisitor;
import org.uma.ast.ASTNode;

import java.util.List;

public class StatementList implements ASTNode {
    private List<ASTNode> statements;

    StatementList(List<ASTNode> statements) {
        this.statements = statements;
    }

    public List<ASTNode> getStatements() {
        return statements;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
