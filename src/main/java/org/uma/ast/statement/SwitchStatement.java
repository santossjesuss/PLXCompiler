package org.uma.ast.statement;

import org.uma.ASTVisitor;
import org.uma.ast.ASTNode;
import org.uma.ast.expression.Expression;

import java.util.List;

public class SwitchStatement implements Statement {
    private Expression expression;
    private List<SwitchCase> caseList;
    private Statement optionalDefault;

    public SwitchStatement(Expression expression, List<SwitchCase> caseList, Statement optionalDefault) {
        this.expression = expression;
        this.caseList = caseList;
        this.optionalDefault = optionalDefault;
    }

    public Expression getExpression() {
        return expression;
    }

    public List<SwitchCase> getCaseList() {
        return caseList;
    }

    public Statement getOptionalDefault() {
        return optionalDefault;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
