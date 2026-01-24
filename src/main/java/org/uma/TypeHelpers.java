package org.uma;

import org.uma.ast.ASTNode;
import org.uma.ast.expression.FloatLiteral;
import org.uma.ast.expression.Identifier;
import org.uma.ast.expression.IntegerLiteral;
import org.uma.symboltable.SymbolTable;

public class TypeHelpers {
    public String getType(ASTNode node, SymbolTable symbolTable) {
        if (node instanceof IntegerLiteral)     return "int";
        if (node instanceof FloatLiteral)       return "float";
        if (node instanceof Identifier) {
            String id = ((Identifier) node).getName();
            return symbolTable.lookup(id).getType();
        }

        return null;
    }

    public boolean compatibleTypes(String srcType, String targetType) {
        if (targetType.equals(srcType))
            return true;

        if (targetType.equals("float") && srcType.equals("int"))
            return true;

        return false;
    }

    public boolean isIntType(String type) {
        return type != null && type.equals("int");
    }

    public boolean isFloatType(String type) {
        return type != null && type.equals("float");
    }

    public boolean isCharType(String type) {
        return type != null && type.equals("char");
    }
}
