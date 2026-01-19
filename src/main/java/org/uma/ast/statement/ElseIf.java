package org.uma.ast.statement;

import org.uma.ast.condition.Condition;

public class ElseIf {
    private Condition condition;
    private Statement sentence;

    ElseIf(Condition condition, Statement sentence) {
        this.condition = condition;
        this.sentence = sentence;
    }

    public Condition getCondition() {
        return condition;
    }

    public Statement getSentence() {
        return sentence;
    }
}
