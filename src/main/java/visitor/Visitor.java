package visitor;

import logic.brick.Brick;
import logic.level.Level;

/**
 * Establece los comportamientos requeridos de un visitor
 * @author Roberto Tapia
 */
public interface Visitor {
    /**
     * Visita un level.
     * @param l level a visitar.
     */
    void visitLevel(Level l);

    /**
     * Visita un brick.
     * @param b brick a visitar.
     */
    void visitBrick(Brick b);
}
