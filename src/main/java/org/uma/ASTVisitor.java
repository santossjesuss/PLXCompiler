package org.uma;

import org.uma.ast.Block;
import org.uma.ast.Program;
import org.uma.ast.condition.BooleanLiteral;
import org.uma.ast.condition.Comparison;
import org.uma.ast.condition.LogicalExpr;
import org.uma.ast.condition.NotExpr;
import org.uma.ast.expression.*;
import org.uma.ast.statement.*;
import org.uma.ast.statement.types.CharType;
import org.uma.ast.statement.types.FloatType;
import org.uma.ast.statement.types.IntType;
import org.uma.ast.statement.types.UserType;

public interface ASTVisitor<T> {
    T visit(Program node);
    T visit(Block node);
    T visit(ExpressionStatement node);
    T visit(VariableDeclarationStatement node);
    T visit(IfStatement node);
    T visit(ForStatement node);
    T visit(ForInitDecl node);
    T visit(ForInitExpr node);
    T visit(ForInitEmpty node);
    T visit(WhileStatement node);
    T visit(DoWhileStatement node);
    T visit(SwitchStatement node);
    T visit(PrintStatement node);
    T visit(EmptyStatement node);

    T visit(IntType node);
    T visit(FloatType node);
    T visit(CharType node);
    T visit(UserType node);

    T visit(BinaryExpr node);
    T visit(UnaryExpr node);
    T visit(TernaryExpr node);
    T visit(Assignment node);
    T visit(Identifier node);

    T visit(IntegerLiteral node);
    T visit(FloatLiteral node);
    T visit(CharacterLiteral node);

    T visit(PreIncrement node);
    T visit(PostIncrement node);
    T visit(PreDecrement node);
    T visit(PostDecrement node);

    T visit(Comparison node);
    T visit(LogicalExpr node);
    T visit(NotExpr node);
    T visit(BooleanLiteral node);

}
