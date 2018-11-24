package logic.brick;

import controller.Game;
import logic.level.Level;

public class MetalBrick extends BrickClass {

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
