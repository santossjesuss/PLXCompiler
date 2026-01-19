package org.uma.ast.statement;

import org.uma.ASTVisitor;
import org.uma.ast.ASTNode;

public class WhileStatement implements ASTNode {
    private ASTNode condition;
    private ASTNode body;

    WhileStatement(ASTNode condition, ASTNode body) {
        this.condition = condition;
        this.body = body;
    }

    public ASTNode getCondition() {
        return condition;
    }

    public ASTNode getBody() {
        return body;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
