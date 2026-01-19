package org.uma.ast.statement;

import org.uma.ASTVisitor;
import org.uma.ast.ASTNode;

public class ElseIf {
    private ASTNode condition;
    private ASTNode sentence;

    ElseIf(ASTNode condition, ASTNode sentence) {
        this.condition = condition;
        this.sentence = sentence;
    }

    public ASTNode getCondition() {
        return condition;
    }

    public ASTNode getSentence() {
        return sentence;
    }
}
