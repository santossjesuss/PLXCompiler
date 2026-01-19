package org.uma.ast.statement;

import org.uma.ASTVisitor;
import org.uma.ast.expression.Expression;

public class VariableDeclaration implements Statement {
    private Type type;
    private String id;
    private Expression expression;

    public VariableDeclaration(Type type, String id, Expression expression) {
        this.type = type;
        this.id = id;
        this.expression = expression;
    }

    public Type getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return null;
    }
}
