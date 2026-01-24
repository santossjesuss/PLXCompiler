package org.uma.ast.statement;

import org.uma.ASTVisitor;
import org.uma.ast.condition.Condition;

import java.util.List;

public class IfStatement implements Statement {
    private Condition condition;
    private Statement thenBranch;
    private Statement elseBranch;

    IfStatement(Condition condition, Statement thenBranch, Statement elseBranch) {
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    public Condition getCondition() {
        return condition;
    }

    public Statement getThenBranch() {
        return thenBranch;
    }

    public Statement getElseBranch() {
        return elseBranch;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
