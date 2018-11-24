package logic.brick;

import controller.Game;
import logic.level.Level;

/**
 * Modela los comportamientos particulares de un WoodenBrick
 * @author Roberto Tapia
 */
public class WoodenBrick extends BrickClass {
    /**
     * Construye un brick con las caracteristicas propias de un woodenBrick
     */
    public WoodenBrick(){
        super(3,200);
    }
    public void beObserved(Game g){
        g.observeWoodenBrick();
    }

    @Override
    public void beObservedByLevel(Level l) {
        l.levelObserveWoodenBrick(this);
    }
}
