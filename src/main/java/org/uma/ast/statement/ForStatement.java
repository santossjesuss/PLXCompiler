package org.uma.ast.statement;

import org.uma.ASTVisitor;
import org.uma.ast.ASTNode;
import org.uma.ast.condition.Condition;
import org.uma.ast.expression.Expression;

public class ForStatement implements Statement {
    private ForInit init;
    private Condition condition;
    private Expression update;
    private Statement body;

    ForStatement(ForInit init, Condition condition, Expression update, Statement body) {
        this.init = init;
        this.condition = condition;
        this.update = update;
        this.body = body;
    }

    public ForInit getInit() {
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
