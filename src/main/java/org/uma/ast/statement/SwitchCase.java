package org.uma.ast.statement;

import org.uma.ast.ASTNode;

public class SwitchCase {
    private int value;
    private ASTNode body;

    SwitchCase(int value, ASTNode body) {
        this.value = value;
        this.body = body;
    }

    public int getValue() {
        return value;
    }

    public ASTNode getBody() {
        return body;
    }
}
