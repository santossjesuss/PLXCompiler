package org.uma.ast.statement;

import org.uma.ASTVisitor;
import org.uma.ast.condition.Condition;

public class DoWhileStatement implements Statement {
    private Statement body;
    private Condition condition;

    DoWhileStatement(Statement body, Condition condition) {
        this.body = body;
        this.condition = condition;
    }

    public Statement getBody() {
        return body;
    }

    public Condition getCondition() {
        return condition;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
