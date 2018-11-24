package Visitor;

import logic.brick.Brick;
import logic.level.Level;

public interface Visitor {
    void visitLevel(Level l);
    void visitBrick(Brick b);
}
