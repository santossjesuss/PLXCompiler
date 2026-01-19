package org.uma;

import org.uma.ast.ASTNode;
import org.uma.ast.condition.Comparison;
import org.uma.ast.condition.LogicalExpr;
import org.uma.ast.condition.NotExpr;
import org.uma.ast.expression.*;
import org.uma.ast.statement.*;
import org.uma.symboltable.SymbolTable;

import java.io.PrintWriter;

public class CodeGenerator implements ASTVisitor<String> {
    private int tempCounter = 0;
    private int labelCounter = 0;
    private SymbolTable symbolTable;
    private PrintWriter writer;

    public CodeGenerator(SymbolTable symbolTable, PrintWriter writer) {
        this.symbolTable = symbolTable;
        this.writer = writer;
    }

    private String newTemp() {
        return "t" + (tempCounter++);
    }

    private String newLabel() {
        return "L" + (labelCounter++);
    }

    @Override
    public String visit(BinaryExpr node) {
        String left = node.getLeft().accept(this);
        String right = node.getRight().accept(this);
        String temp = newTemp();

        switch (node.getOperator()) {
            case "+":
                writer.println("\t" + temp + " = " + left + " + " + right + ";");
                break;
            case "-":
                writer.println("\t" + temp + " = " + left + " - " + right + ";");
                break;
            case "*":
                writer.println("\t" + temp + " = " + left + " * " + right + ";");
                break;
            case "/":
                writer.println("\t" + temp + " = " + left + " / " + right + ";");
                break;
        }
        return temp;
    }

    @Override
    public String visit(UnaryExpr node) {
        String operand = node.getOperand().accept(this);
        String temp = newTemp();

        if (node.getOperator().equals("-")) {
            writer.println("\t" + temp + "= -" + operand + ";");
        }
        return temp;
    }

    @Override
    public String visit(Assignment node) {
        String value = node.getValue().accept(this);
        writer.println("\t" + node.getIdentifier() + " = " + value + ";");
        return node.getIdentifier();
    }

    @Override
    public String visit(Identifier node) {
        return node.getName();
    }

    @Override
    public String visit(IntegerLiteral node) {
        return Integer.toString(node.getValue());
    }

    @Override
    public String visit(Comparison node) {
        String left = node.getLeft().accept(this);
        String right = node.getRight().accept(this);
        String trueLabel = newLabel();
        String falseLabel = newLabel();

        writer.println("\tif (" + left + " " + node.getOperator() + " " + right + ") goto " + trueLabel + ";");
        writer.println("\tgoto " + falseLabel + ";");

        return trueLabel + ":" + falseLabel;
    }

    @Override
    public String visit(LogicalExpr node) {
        if (node.getOperator().equals("&&")) {
            String falseLabel = newLabel();
            String endLabel = newLabel();

            String leftResult = node.getLeft().accept(this);
            writer.println("\tifFalse " + leftResult + " goto " + falseLabel + ";");

            String rightResult = node.getRight().accept(this);
            writer.println("\tifFalse " + rightResult + " goto " + falseLabel + ";");

            writer.println("\tgoto " + endLabel + ";");
            writer.println(falseLabel + ":");
            writer.println("\t# false result");
            writer.println(endLabel + ":");

            return endLabel;
        } else {
            String trueLabel = newLabel();
            String endLabel = newLabel();

            String leftResult = node.getLeft().accept(this);
            writer.println("\tifTrue " + leftResult + " goto " + trueLabel + ";");

            String rightResult = node.getRight().accept(this);
            writer.println("\tifTrue " + rightResult + " goto " + trueLabel + ";");

            writer.println("\tgoto " + endLabel + ";");
            writer.println(trueLabel + ":");
            writer.println("\t# true result");
            writer.println(endLabel + ":");

            return endLabel;
        }
    }

    @Override
    public String visit(NotExpr node) {
        String operand = node.getOperand().accept(this);
        String temp = newTemp();
        writer.println("\t" + temp + " = !" + operand + ";");
        return temp;
    }

    @Override
    public String visit(StatementList node) {
        for (ASTNode statement : node.getStatements()) {
            statement.accept(this);
        }
        return "";
    }

    @Override
    public String visit(IfStatement node) {
        String conditionResult = node.getCondition().accept(this);
        String[] labels = conditionResult.split(":");
        String trueLabel = labels[0];
        String falseLabel = labels[1];

        writer.println(trueLabel + ":");
        node.getThenBranch().accept(this);
        String endLabel = newLabel();
        writer.println("\tgoto " + endLabel + ";");

        writer.println(falseLabel + ":");
        if (node.getElseBranch() != null) {
            node.getElseBranch().accept(this);
        }

        writer.println(endLabel + ":");
        return "";
    }

    @Override
    public String visit(WhileStatement node) {
        String startLabel = newLabel();
        String conditionLabel = newLabel();
        String endLabel = newLabel();

        writer.println("\tgoto " + conditionLabel + ";");
        writer.println(startLabel + ":");
        node.getBody().accept(this);

        writer.println(conditionLabel + ":");
        String conditionResult = node.getCondition().accept(this);
        String[] labels = conditionResult.split(":");
        writer.println("\tifTrue " + labels[0] + " goto " + startLabel + ";");
        writer.println("\tgoto " + endLabel + ";");

        writer.println(endLabel + ":");
        return "";
    }

    @Override
    public String visit(DoWhileStatement node) {
        String startLabel = newLabel();
        String endLabel = newLabel();

        writer.println(startLabel + ":");
        node.getBody().accept(this);

        String conditionResult = node.getCondition().accept(this);
        String[] labels = conditionResult.split(":");
        writer.println("\tifTrue " + labels[0] + " goto " + startLabel + ";");
        writer.println("\tgoto " + endLabel + ";");

        writer.println(endLabel + ":");
        return "";
    }

    @Override
    public String visit(ForStatement node) {
        if (node.getInit() != null) {
            node.getInit().accept(this);
        }

        String startLabel = newLabel();
        String updateLabel = newLabel();
        String endLabel = newLabel();

        writer.println("\tgoto " + updateLabel + ";");
        writer.println(startLabel + ":");
        node.getBody().accept(this);

        writer.println(updateLabel + ":");
        if (node.getUpdate() != null) {
            node.getUpdate().accept(this);
        }

        if (node.getCondition() != null) {
            String conditionResult = node.getCondition().accept(this);
            String[] labels = conditionResult.split(":");
            writer.println("\tifTrue " + labels[0] + " goto " + startLabel + ";");
        }

        writer.println("\tgoto " + endLabel + ";");
        writer.println(endLabel + ":");
        return "";
    }

    @Override
    public String visit(PrintStatement node) {
        String value = node.getExpr().accept(this);
        writer.println("\tprint " + value + ";");
        return "";
    }

    @Override
    public String visit(BlockStatement node) {
        return node.getStatements().accept(this);
    }

    @Override
    public String visit(ExpressionStatement node) {
        return node.getExpression().accept(this);
    }
}
