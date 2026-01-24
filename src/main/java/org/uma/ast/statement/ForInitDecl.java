package org.uma.ast.statement;

import org.uma.ASTVisitor;

public class ForInitDecl extends ForInit {
    VariableDeclarationStatement variableDeclarationStatement;

    public ForInitDecl(VariableDeclarationStatement variableDeclarationStatement) {
        this.variableDeclarationStatement = variableDeclarationStatement;
    }

    public VariableDeclarationStatement getVariableDeclaration() {
        return variableDeclarationStatement;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
