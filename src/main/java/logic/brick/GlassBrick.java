package logic.brick;

import controller.Game;
import logic.level.Level;

public class GlassBrick extends BrickClass{

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
