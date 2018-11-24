package Visitor;

import logic.brick.Brick;

public class BrickCounter {
    private int brickcount;
    public BrickCounter(){
        brickcount=0;
    }
    public void visit(Brick b){
        if (!b.isDestroyed()) {
            brickcount += 1;
        }
    }
    public int getCounter(){
        return brickcount;
    }

}
