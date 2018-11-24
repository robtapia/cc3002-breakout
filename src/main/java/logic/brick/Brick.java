package logic.brick;

import logic.level.Visitable;
import visitor.Visitor;
import controller.ObservableByGame;
import logic.level.ObservableByLevel;

/**
 * Interface that represents a brick object.
 * <p>
 * All bricks should implement this interface.
 *
 * @author Juan-Pablo Silva
 */
public interface Brick extends ObservableByGame,ObservableByLevel,Visitable {
    /**
     * Defines that a brick has been hit.
     * Implementations should consider the events that a hit to a brick can trigger.
     */
    void hit();

    /**
     * Gets whether the brick object is destroyed or not.
     *
     * @return true if the brick is destroyed, false otherwise
     */
    boolean isDestroyed();

    /**
     * Gets the points corresponding to the destroying of a brick object.
     *
     * @return the associated points of a brick object
     */
    int getScore();

    /**
     * Gets the remaining hits the brick has to receive before being destroyed.
     *
     * @return the remaining hits to destroy de brick
     */
    int remainingHits();

    /**
     * Indica al visitor v que debe visitar al presente ladrillo (this)
     * @param v visitor que interactua con el brick
     * @author Roberto Tapia
     */
    void accept(Visitor v);
}
