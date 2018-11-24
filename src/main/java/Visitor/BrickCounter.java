package Visitor;

import logic.brick.Brick;
import logic.level.Level;
import logic.level.PlayableLevel;

public class BrickCounter implements Visitor {
    private int brickcount;
    public BrickCounter(){
        brickcount=0;
    }
    public void visitBrick(Brick b){
        if (!b.isDestroyed()) {
            brickcount += 1;
        }
    }
    public void visitLevel(Level l){
        for (Brick b:l.getBricks()){
            b.accept(this);
        }
    }
    public int getCounter(){
        return brickcount;
    }

}
