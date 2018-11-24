package logic.level;

import visitor.Visitor;

/**
 * Modela los comportamientos necesarios de una entidad que puede ser visitada
 * @author Roberto Tapia
 */
public interface Visitable {
    /**
     * Indica al visitor v que debe visitar al presente Visitable
     * @param v visitor que interactua con el visitable
     *
     */
    void accept(Visitor v);
}
