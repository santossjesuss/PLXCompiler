package org.uma.ast.statement;

import org.uma.ASTVisitor;
import org.uma.ast.ASTNode;

import java.util.List;

public class SwitchStatement implements ASTNode {
    private ASTNode expression;
    private List<SwitchCase> caseList;
    private ASTNode optionalDefault;

    public SwitchStatement(ASTNode expression, List<SwitchCase> caseList, ASTNode optionalDefault) {
        this.expression = expression;
        this.caseList = caseList;
        this.optionalDefault = optionalDefault;
    }

    public ASTNode getExpression() {
        return expression;
    }

    public List<SwitchCase> getCaseList() {
        return caseList;
    }

    public ASTNode getOptionalDefault() {
        return optionalDefault;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
