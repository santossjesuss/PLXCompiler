package org.uma;

import org.uma.ast.ASTNode;
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
import org.uma.symboltable.SymbolTable;

import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.List;


public class CodeGenerator implements ASTVisitor<String> {
    private int tempCounter = 0;
    private int labelCounter = 0;
    private SymbolTable symbolTable;
    private TypeHelpers typeHelpers;
    private PrintWriter writer;
    private List<String> errors;

    public CodeGenerator(SymbolTable symbolTable, PrintWriter writer) {
        this.symbolTable = symbolTable;
        this.typeHelpers = new TypeHelpers();
        this.writer = writer;
        this.errors = new ArrayList<>();
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
        String operator = node.getOperator();
        String temp = newTemp();

        String typeLeft = typeHelpers.getType(node.getLeft(), symbolTable);
        String typeRight = typeHelpers.getType(node.getRight(), symbolTable);
        boolean isFloatOp = typeLeft.equals("float") || typeRight.equals("float");
        if (isFloatOp) {
            operator = node.getOperator() + "r";
        }

        writer.println(temp + " = " + left + " " + operator + " " + right + ";");

        return temp;
    }

    @Override
    public String visit(UnaryExpr node) {
        String operator = node.getOperator();
        String expression = node.getExpression().accept(this);
        String temp = newTemp();

        writer.println(temp + " = " + operator + expression + ";");

        return temp;
    }

    @Override
    public String visit(TernaryExpr node) {
        String condition = node.getCondition().accept(this);
        String temp = newTemp();
        String falseLabel = newLabel();
        String endLabel = newLabel();

        writer.println("if (" + condition + " == 0) goto " + falseLabel + ";");

        String trueBranch = node.getTrueBranch().accept(this);
        writer.println(temp + " = " + trueBranch + ";");
        writer.println("goto " + endLabel);

        String falseBranch = node.getFalseBranch().accept(this);
        writer.println(falseLabel + ":");
        writer.println(temp + " = " + falseBranch + ";");

        writer.println(endLabel + ":");

        return temp;
    }

    @Override
    public String visit(Assignment node) {
        String identifier = node.getIdentifier().accept(this);
        String value = node.getValue().accept(this);

        if (!symbolTable.isDeclared(identifier)) {
            errors.add("error:\nVariable '" + identifier + "' not declared");
            writer.println("error;");
            return identifier;
        }

        String identifierType = typeHelpers.getType(node.getIdentifier(), symbolTable);
        String expressionType = typeHelpers.getType(node.getValue(), symbolTable);
        if (typeHelpers.compatibleTypes(identifierType, expressionType)) {
            errors.add("error:\nType mismatch in assignment");
            writer.println("error;");
            return identifier;
        }

        if (typeHelpers.isIntType(identifierType) && typeHelpers.isFloatType(expressionType)) {
            String convTemp = newTemp();
            writer.println(convTemp + " = (int) " + value + ";");
            value = convTemp;
        } else if (typeHelpers.isFloatType(identifierType) && typeHelpers.isIntType(expressionType)) {
            String convTemp = newTemp();
            writer.println(convTemp + " = (float) " + value + ";");
            value = convTemp;
        }

        writer.println(identifier + " = " + value + ";");

        return identifier;
    }

    @Override
    public String visit(Identifier node) {
        return node.getName();
    }

    @Override
    public String visit(IntegerLiteral node) {
        return String.valueOf(node.getValue());
    }

    @Override
    public String visit(FloatLiteral node) {
        return String.valueOf(node.getValue());
    }

    @Override
    public String visit(CharacterLiteral node) {
        return String.valueOf(node.getValue());
    }

    @Override
    public String visit(PreIncrement node) {
        String identifier = node.getIdentifier().accept(this);

        writer.println(identifier + " = " + identifier + " + 1;");

        return identifier;
    }

    @Override
    public String visit(PostIncrement node) {
        String identifier = node.getIdentifier().accept(this);
        String temp = newTemp();

        writer.println(temp + " = " + identifier + ";");
        writer.println(identifier + " = " + identifier + " + 1;");

        return temp;
    }

    @Override
    public String visit(PreDecrement node) {
        String identifier = node.getIdentifier().accept(this);

        writer.println(identifier + " = " + identifier + " - 1;");

        return identifier;
    }

    @Override
    public String visit(PostDecrement node) {
        String identifier = node.getIdentifier().accept(this);
        String temp = newTemp();

        writer.println(temp + " = " + identifier + ";");
        writer.println(identifier + " = " + identifier + " - 1;");

        return temp;
    }

    @Override
    public String visit(Comparison node) {
        String left = node.getLeft().accept(this);
        String right = node.getRight().accept(this);
        String operator = node.getOperator();
        String temp = newTemp();
        String trueLabel = newLabel();
        String falseLabel = newLabel();
        String endLabel = newLabel();

        writer.println("if (" + left + " " + operator + " " + right + ") goto " + trueLabel + ";");
        writer.println("goto " + falseLabel + ";");

        writer.println(trueLabel + ":");
        writer.println(temp + " = 1;");
        writer.println("goto " + endLabel + ";");

        writer.println(falseLabel + ":");
        writer.println(temp + " = 0;");

        writer.println(endLabel + ":");

        return temp;
    }

    @Override
    public String visit(LogicalExpr node) {
        String temp = newTemp();
        String endLabel = newLabel();

        if (node.getOperator().equals("&&")) {
            String falseLabel = newLabel();

            String leftResult = node.getLeft().accept(this);
            writer.println("if (" + leftResult + " == 0) goto " + falseLabel + ";");
            String rightResult = node.getRight().accept(this);
            writer.println("if (" + rightResult + " == 0) goto " + falseLabel + ";");

            writer.println(temp + " = 1;");
            writer.println("goto " + endLabel + ";");

            writer.println(falseLabel + ":");
            writer.println(temp + " = 0;");

            writer.println(endLabel + ":");
        } else {
            String trueLabel = newLabel();

            String leftResult = node.getLeft().accept(this);
            writer.println("if (" + leftResult + " == 1) goto " + trueLabel + ";");
            String rightResult = node.getRight().accept(this);
            writer.println("if (" + rightResult + " == 1) goto " + trueLabel + ";");

            writer.println(temp + " = 0;");
            writer.println("goto " + endLabel + ";");

            writer.println(trueLabel + ":");
            writer.println(temp + " = 1;");

            writer.println(endLabel + ":");
        }
        return endLabel;
    }

    @Override
    public String visit(NotExpr node) {
        String condition = node.getCondition().accept(this);
        String temp = newTemp();

        writer.println(temp + " = !" + condition + ";");

        return temp;
    }

    @Override
    public String visit(BooleanLiteral node) {
        boolean nodeValue = node.getValue();
        String value = nodeValue ? "1" : "0";
        String temp = newTemp();

        writer.println(temp + " = " + value + ";");

        return temp;
    }

    @Override
    public String visit(Program node) {
        node.getBlock().accept(this);

        writer.println("halt;");

        return "";
    }

    @Override
    public String visit(Block node) {
        symbolTable.enterScope();
        for (Statement statement : node.getStatements()) {
            statement.accept(this);
        }
        symbolTable.exitScope();

        return "";
    }

    @Override
    public String visit(IfStatement node) {
        symbolTable.enterScope();
        String conditionResult = node.getCondition().accept(this);
        String elseLabel = newLabel();
        String endLabel = newLabel();

        writer.println("if (" + conditionResult + " == 0) goto " + elseLabel + ";");
        node.getThenBranch().accept(this);
        writer.println("goto " + endLabel + ";");

        writer.println(elseLabel + ":");
        if (node.getElseBranch() != null) {
            node.getElseBranch().accept(this);
        }

        writer.println(endLabel + ":");

        symbolTable.exitScope();
        return "";
    }

    @Override
    public String visit(WhileStatement node) {
        symbolTable.enterScope();
        String conditionResult = node.getCondition().accept(this);
        String startLabel = newLabel();
        String endLabel = newLabel();

        writer.println(startLabel + ":");
        writer.println("if (" + conditionResult + " == 0) goto " + endLabel + ";");
        node.getBody().accept(this);
        writer.println("goto " + startLabel + ";");

        writer.println(endLabel + ":");

        symbolTable.exitScope();
        return "";
    }

    @Override
    public String visit(DoWhileStatement node) {
        symbolTable.enterScope();
        String bodyLabel = newLabel();
        String endLabel = newLabel();

        writer.println(bodyLabel + ":");
        node.getBody().accept(this);

        String conditionResult = node.getCondition().accept(this);
        writer.println("if (" + conditionResult + " != 0) goto " + bodyLabel + ";");

        writer.println(endLabel + ":");

        symbolTable.exitScope();
        return "";
    }

    @Override
    public String visit(SwitchStatement node) {
        symbolTable.enterScope();
        String expressionResult = node.getExpression().accept(this);
        String defaultLabel = newLabel();
        String endLabel = newLabel();

        List<SwitchCase> caseList = node.getCaseList();
        List<String> caseLabels = new ArrayList<>();
        for (SwitchCase sc : caseList) {
            caseLabels.add(newLabel());
        }

        for (int i = 0; i < caseList.size(); i++) {
            String value = String.valueOf(caseList.get(i).getValue());
            writer.println("if (" + expressionResult + " == " + value + ") goto " + caseLabels.get(i) + ";");
        }
        writer.println("goto " + defaultLabel + ";");

        for (int i = 0; i < caseList.size(); i++) {
            writer.println(caseLabels.get(i) + ":");
            caseList.get(i).getBody().accept(this);
        }

        if (node.getOptionalDefault() != null) {
            writer.println(defaultLabel + ":");
            node.getOptionalDefault().accept(this);
        }

        writer.println(endLabel + ":");

        symbolTable.exitScope();
        return "";
    }

    @Override
    public String visit(ForStatement node) {
        symbolTable.enterScope();
        String conditionLabel = newLabel();
        String bodyLabel = newLabel();
        String updateLabel = newLabel();
        String endLabel = newLabel();

        if (node.getInit() != null) {
            node.getInit().accept(this);
        }

        writer.println(conditionLabel + ":");
        if (node.getCondition() != null) {
            String conditionResult = node.getCondition().accept(this);
            writer.println("if (" + conditionResult + " == 0) goto " + endLabel + ";");
        }

        writer.println(bodyLabel + ":");
        node.getBody().accept(this);

        writer.println(updateLabel + ":");
        if (node.getUpdate() != null) {
            node.getUpdate().accept(this);
        }

        writer.println("goto " + conditionLabel + ";");

        writer.println(endLabel + ":");

        symbolTable.exitScope();
        return "";
    }

    @Override
    public String visit(ForInitDecl node) {
        node.getVariableDeclaration().accept(this);

        return "";
    }

    @Override
    public String visit(ForInitExpr node) {
        return node.getExpression().accept(this);
    }

    @Override
    public String visit(ForInitEmpty node) {
        return "";
    }

    @Override
    public String visit(PrintStatement node) {
        String value = node.getExpr().accept(this);
        String type = typeHelpers.getType(node.getExpr(), symbolTable);

        if (typeHelpers.isCharType(type)) {
            value = String.valueOf((int) value.charAt(0));
            writer.println("printc " + value + ";");
        } else {
            writer.println("print " + value + ";");
        }

        return "";
    }

    @Override
    public String visit(VariableDeclarationStatement node) {
        List<VariableDeclaration> declarationList = node.getDeclarations();
        if (declarationList.isEmpty())  return null;

        String type = declarationList.getFirst().getType().accept(this);

        for (VariableDeclaration declaration : node.getDeclarations()) {
            String identifier = declaration.getId().accept(this);

            if (symbolTable.isDeclared(identifier)) {
                errors.add("error:\nVariable '" + identifier + "' already declared");
                writer.println("error;");
                //continue;     // recovery
                return "";
            }
            symbolTable.declare(identifier, type);

            if (declaration.getExpression() != null) {
                String value = declaration.getExpression().accept(this);
                writer.println(identifier + " = " + value + ";");
            }
        }

        return "";
    }

    @Override
    public String visit(ExpressionStatement node) {
        node.getExpression().accept(this);

        return "";
    }

    @Override
    public String visit(EmptyStatement node) {
        return "";
    }

    @Override
    public String visit(IntType node) {
        return node.getType();
    }

    @Override
    public String visit(FloatType node) {
        return node.getType();
    }

    @Override
    public String visit(CharType node) {
        return node.getType();
    }

    @Override
    public String visit(UserType node) {
        return node.getType();
    }
}
