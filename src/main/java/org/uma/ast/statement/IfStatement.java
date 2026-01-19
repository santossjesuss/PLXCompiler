package org.uma.ast.statement;

import org.uma.ASTVisitor;
import org.uma.ast.ASTNode;

import java.util.List;

public class IfStatement implements ASTNode {
    private ASTNode condition;
    private ASTNode thenBranch;
    private List<ElseIf> elseIfList;
    private ASTNode elseBranch;

    IfStatement(ASTNode condition, ASTNode thenBranch, List<ElseIf> elseIfList, ASTNode elseBranch) {
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseIfList = elseIfList;
        this.elseBranch = elseBranch;
    }

    public ASTNode getCondition() {
        return condition;
    }

    public ASTNode getThenBranch() {
        return thenBranch;
    }

    public List<ElseIf> getElseIfList() {
        return elseIfList;
    }

    public ASTNode getElseBranch() {
        return elseBranch;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
