package org.uma;

import org.uma.ast.ASTNode;
import org.uma.symboltable.SymbolTable;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        validateArgs(args);

        Lexer lexer;
        parser parser;

        try (
                Reader reader = createReader(args);
                PrintWriter writer = createWriter(args);
        ) {
            lexer = new Lexer(reader);
            parser = new parser(lexer);
            ASTNode ast = (ASTNode) parser.parse().value;

            SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
            ast.accept(semanticAnalyzer);

            if (semanticAnalyzer.hasErrors()) {
                for (String error : semanticAnalyzer.getErrors()) {
                    System.err.println(error);
                }
                System.exit(1);
            }

            SymbolTable symbolTable = new SymbolTable();
            CodeGenerator codeGenerator = new CodeGenerator(symbolTable, writer);

            writer.println("# code generated #");
            writer.println("==================");
            ast.accept(codeGenerator);

            writer.flush();
        } catch (Exception e) {
            System.err.println("Compilation failed:");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void validateArgs(String[] args) {
        if (args.length > 2) {
            System.err.println("Cannot provide more than 2 arguments.");
        }
    }

    private static Reader createReader(String[] args) throws FileNotFoundException {
        if (args.length == 0) {
            return new InputStreamReader(System.in);
        }
        return new FileReader(args[0]);
    }

    private static PrintWriter createWriter(String[] args) throws FileNotFoundException {
        if (args.length < 2) {
            return new PrintWriter(System.out);
        }
        return new PrintWriter(args[1]);
    }
}