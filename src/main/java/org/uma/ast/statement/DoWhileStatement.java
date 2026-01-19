package org.uma.ast.statement;

import org.uma.ASTVisitor;
import org.uma.ast.ASTNode;

public class DoWhileStatement implements ASTNode {
    private ASTNode body;
    private ASTNode condition;

    DoWhileStatement(ASTNode body, ASTNode condition) {
        this.body = body;
        this.condition = condition;
    }

    public ASTNode getBody() {
        return body;
    }

    public ASTNode getCondition() {
        return condition;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
