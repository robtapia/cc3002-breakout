package logic.brick;
import Visitor.*;
import controller.ObservableByGame;

import java.util.Observable;

public abstract class BrickClass extends Observable implements Brick, ObservableByGame {
    private int remainingHits;
    private int score;

    BrickClass(int hits,int score){
        this.remainingHits=hits;
        this.score=score;
    }

    @Override
    public void accept(Visitor v) {
        v.visitBrick(this);
    }

    public int getScore(){
        return this.score;
    }

    public int remainingHits(){
        return this.remainingHits;
    }
    public void beHit(){this.remainingHits=this.remainingHits-1;}

    public boolean isDestroyed(){
        if (this.remainingHits()==0) {
            return true;
        }
        return false;
    }
    public void hit() {
        if (this.isDestroyed() == false) {
            this.beHit();
            if (this.isDestroyed() == true) {

                this.setChanged();
                this.notifyObservers();
            }
        }
    }
}
