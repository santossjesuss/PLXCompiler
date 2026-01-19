package org.uma;

import org.uma.ast.condition.BooleanLiteral;
import org.uma.ast.condition.Comparison;
import org.uma.ast.condition.LogicalExpr;
import org.uma.ast.condition.NotExpr;
import org.uma.ast.expression.*;
import org.uma.ast.statement.*;

public interface ASTVisitor<T> {
    T visit(BinaryExpr node);
    T visit(UnaryExpr node);
    T visit(TernaryExpr node);
    T visit(Assignment node);
    T visit(Identifier node);
    T visit(IntegerLiteral node);
    T visit(CharacterLiteral node);
    T visit(PreIncrement node);
    T visit(PostIncrement node);
    T visit(PreDecrement node);
    T visit(PostDecrement node);

    T visit(Comparison node);
    T visit(LogicalExpr node);
    T visit(NotExpr node);
    T visit(BooleanLiteral node);

    T visit(StatementList node);
    T visit(IfStatement node);
    T visit(ElseIf node);
    T visit(WhileStatement node);
    T visit(DoWhileStatement node);
    T visit(ForStatement node);
    T visit(SwitchStatement node);
    T visit(PrintStatement node);
    T visit(BlockStatement node);
    T visit(IntType node);
    T visit(CharType node);
    T visit(UserType node);
}
