package org.uma.ast.statement;

import org.uma.ASTVisitor;

import java.util.List;

public class VariableDeclarationStatement implements Statement {
    List<VariableDeclaration> declarations;

    public VariableDeclarationStatement(List<VariableDeclaration> declarations) {
        this.declarations = declarations;
    }

    public List<VariableDeclaration> getDeclarations() {
        return declarations;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}