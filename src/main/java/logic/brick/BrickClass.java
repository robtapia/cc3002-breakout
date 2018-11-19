package logic.brick;
import java.util.Observable;

public abstract class BrickClass extends Observable implements Brick {
    private int remainingHits;
    private int score;

    BrickClass(int hits,int score){
        this.remainingHits=hits;
        this.score=score;
    }

    public int getScore(){
        return this.score;
    }

    public int remainingHits(){
        return this.remainingHits;
    }

    public boolean isDestroyed(){
        if (this.remainingHits()==0) {
            return true;
        }
        return false;
    }
}
