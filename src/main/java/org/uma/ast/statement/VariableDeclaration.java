package org.uma.ast.statement;

import org.uma.ast.expression.Expression;
import org.uma.ast.expression.Identifier;
import org.uma.ast.statement.types.Type;

public class VariableDeclaration {
    private Type type;
    private Identifier id;
    private Expression expression;

    public VariableDeclaration(Type type, Identifier id, Expression expression) {
        this.type = type;
        this.id = id;
        this.expression = expression;
    }

    public Type getType() {
        return type;
    }

    public Identifier getId() {
        return id;
    }

    public Expression getExpression() {
        return expression;
    }
}
