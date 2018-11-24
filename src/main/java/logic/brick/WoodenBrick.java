package logic.brick;

import controller.Game;
import logic.level.Level;

public class WoodenBrick extends BrickClass {

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
