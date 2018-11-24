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
    @Override
    public void beObserved(Game g){
        g.observeGlassBrick();
    }
    @Override
    public void beObservedByLevel(Level l){
        l.levelObserveGlassBrick(this);
    }








}
