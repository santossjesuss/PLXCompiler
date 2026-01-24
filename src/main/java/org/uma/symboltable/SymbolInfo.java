package org.uma.symboltable;

public class SymbolInfo {
    String name;
    String type;
    int scopeLevel;

    SymbolInfo(String name, String type, int level) {
        this.name = name;
        this.type = type;
        this.scopeLevel = level;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getScopeLevel() {
        return scopeLevel;
    }
}
