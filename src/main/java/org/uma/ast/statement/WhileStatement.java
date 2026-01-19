package org.uma.ast.statement;

import org.uma.ASTVisitor;
import org.uma.ast.ASTNode;
import org.uma.ast.condition.Condition;

public class WhileStatement implements Statement {
    private Condition condition;
    private Statement body;

    WhileStatement(Condition condition, Statement body) {
        this.condition = condition;
        this.body = body;
    }

    public Condition getCondition() {
        return condition;
    }

    public Statement getBody() {
        return body;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
