package org.uma.ast.statement;

import org.uma.ASTVisitor;
import org.uma.ast.ASTNode;

public class PrintStatement implements ASTNode {
    private ASTNode expr;

    PrintStatement(ASTNode expr) {
        this.expr = expr;
    }

    public ASTNode getExpr() {
        return expr;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
