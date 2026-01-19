package org.uma.ast.statement;

import org.uma.ASTVisitor;
import org.uma.ast.ASTNode;

public class ForStatement implements ASTNode {
    private ASTNode init;
    private ASTNode condition;
    private ASTNode update;
    private ASTNode body;

    ForStatement(ASTNode init, ASTNode condition, ASTNode update, ASTNode body) {
        this.init = init;
        this.condition = condition;
        this.update = update;
        this.body = body;
    }

    public ASTNode getInit() {
        return init;
    }

    public ASTNode getCondition() {
        return condition;
    }

    public ASTNode getUpdate() {
        return update;
    }

    public ASTNode getBody() {
        return body;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
