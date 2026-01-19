package org.uma.ast.statement;

public class SwitchCase  {
    private int value;
    private Statement body;

    SwitchCase(int value, Statement body) {
        this.value = value;
        this.body = body;
    }

    public int getValue() {
        return value;
    }

    public Statement getBody() {
        return body;
    }
}
