package org.uma;

import org.uma.ast.ASTNode;
import org.uma.ast.condition.Comparison;
import org.uma.ast.condition.LogicalExpr;
import org.uma.ast.condition.NotExpr;
import org.uma.ast.expression.*;
import org.uma.ast.statement.*;
import org.uma.symboltable.SymbolInfo;
import org.uma.symboltable.SymbolTable;

import java.util.ArrayList;
import java.util.List;

public class SemanticAnalyzer implements ASTVisitor<Void> {
    private SymbolTable symbolTable;
    private List<String> errors;

    public SemanticAnalyzer() {
        symbolTable = new SymbolTable();
        errors = new ArrayList<>();
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean hasErrors() {
       return !errors.isEmpty();
    }

    private void error(String message, int line) {
        errors.add("Line " + line + ": " + message);
    }

    @Override
    public Void visit(BinaryExpr node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
        return null;
    }

    @Override
    public Void visit(UnaryExpr node) {
        node.getOperand().accept(this);
        return null;
    }

    @Override
    public Void visit(Assignment node) {
        SymbolInfo info = symbolTable.lookup(node.getIdentifier());
        if (info == null) {
            error("Variable '" + node.getIdentifier() + "' not declared", 0);
        }

        node.getValue().accept(this);
        return null;
    }

    @Override
    public Void visit(Identifier node) {
        SymbolInfo info = symbolTable.lookup(node.getName());
        if (info == null) {
            error("Variable '" + node.getName() + "' not declared", 0);
        }
        return null;
    }

    @Override
    public Void visit(IntegerLiteral node) {
        return null;    // always valid
    }

    @Override
    public Void visit(Comparison node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
        return null;
    }

    @Override
    public Void visit(LogicalExpr node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
        return null;
    }

    @Override
    public Void visit(NotExpr node) {
        node.getOperand().accept(this);
        return null;
    }

    @Override
    public Void visit(StatementList node) {
        for (ASTNode statement : node.getStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(IfStatement node) {
        node.getCondition().accept(this);

        symbolTable.enterScope();
        node.getThenBranch().accept(this);
        symbolTable.exitScope();

        if (node.getElseBranch() != null) {
            symbolTable.enterScope();
            node.getElseBranch().accept(this);
            symbolTable.exitScope();
        }
        return null;
    }

    @Override
    public Void visit(WhileStatement node) {
        node.getCondition().accept(this);
        symbolTable.enterScope();
        node.getBody().accept(this);
        symbolTable.exitScope();
        return null;
    }

    @Override
    public Void visit(DoWhileStatement node) {
        symbolTable.enterScope();
        node.getBody().accept(this);
        symbolTable.exitScope();
        node.getCondition().accept(this);
        return null;
    }

    @Override
    public Void visit(ForStatement node) {
        symbolTable.enterScope();

        if (node.getInit() != null) {
            node.getInit().accept(this);
        }

        if (node.getCondition() != null) {
            node.getCondition().accept(this);
        }

        if (node.getUpdate() != null) {
            node.getUpdate().accept(this);
        }

        node.getBody().accept(this);
        symbolTable.exitScope();
        return null;
    }

    @Override
    public Void visit(PrintStatement node) {
        node.getExpr().accept(this);
        return null;
    }

    @Override
    public Void visit(BlockStatement node) {
        symbolTable.enterScope();
        node.getStatements().accept(this);
        symbolTable.exitScope();
        return null;
    }

    @Override
    public Void visit(ExpressionStatement node) {
        node.getExpression().accept(this);
        return null;
    }
}
