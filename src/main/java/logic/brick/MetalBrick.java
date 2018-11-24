package logic.brick;

import controller.Game;
import logic.level.Level;
/**
 * Modela los comportamientos particulares de un MetalBrick
 * @author Roberto Tapia
 */
public class MetalBrick extends BrickClass {
    /**
     * Construye un brick con las carateristicas particulares de un MetalBrick
     */
    public MetalBrick(){
        super(10,0);
    }
    public void beObserved(Game g){
        g.observeMetalBrick();
    }

    @Override
    public void beObservedByLevel(Level l) {
        //Does nothing.
    }
}
