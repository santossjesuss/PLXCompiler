package org.uma.symboltable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymbolTable {
    private List<Map<String, SymbolInfo>> scopes;
    private int currentLevel;

    public SymbolTable() {
        scopes = new ArrayList<>();
        currentLevel = 0;
        enterScope();
    }

    public void enterScope() {
        currentLevel++;
        scopes.add(new HashMap<>());
    }

    public void exitScope() {
        if (currentLevel > 0) {
            scopes.removeLast();
            currentLevel--;
        }
    }

    public boolean declare(String name, String type) {
        Map<String, SymbolInfo> currentScope = scopes.getLast();

        if (currentScope.containsKey(name)) {
            return false;
        }

        currentScope.put(name, new SymbolInfo(name, type, currentLevel));
        return true;
    }

    public SymbolInfo lookup(String name) {
        for (int i = scopes.size() - 1; i >= 0; i--) {
            Map<String, SymbolInfo> scope = scopes.get(i);
            if (scope.containsKey(name)) {
                return scope.get(name);
            }
        }
        return null;
    }

    public boolean isDeclared(String name) {
        return lookup(name) != null;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void printTable() {
        System.out.println("\n=== Symbol Table ===");
        for (int i = 0; i < scopes.size(); i++) {
            System.out.println("Scope level " + i + ":");
            Map<String, SymbolInfo> scope = scopes.get(i);
            for (SymbolInfo info : scope.values()) {
                System.out.println(" " + info.name + " : " + info.type);
            }
        }
    }
}
