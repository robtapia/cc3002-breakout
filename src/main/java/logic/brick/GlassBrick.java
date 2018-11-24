package logic.brick;

import controller.Game;
import logic.level.Level;
/**
 * Modela los comportamientos particulares de un GlassBrick
 * @author Roberto Tapia
 */
public class GlassBrick extends BrickClass{
    /**
     * Construye un brick con las caracteristicas particulares de un GlassBrick
     */
    public GlassBrick(){
        super(1,50);
    }
    public void beObserved(Game g){
        g.observeGlassBrick();
    }
    public void beObservedByLevel(Level l){
        l.levelObserveGlassBrick(this);
    }








}
