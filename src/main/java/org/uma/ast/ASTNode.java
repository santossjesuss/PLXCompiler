package org.uma.ast;

import org.uma.ASTVisitor;

public interface ASTNode {
    <T> T accept(ASTVisitor<T> visitor);
}
