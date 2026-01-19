package org.uma.ast.statement;

public class ForInitDecl extends ForInit {
    VariableDeclaration variableDeclaration;

    public ForInitDecl(VariableDeclaration variableDeclaration) {
        this.variableDeclaration = variableDeclaration;
    }

    public VariableDeclaration getVariableDeclaration() {
        return variableDeclaration;
    }
}
