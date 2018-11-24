package logic.brick;
import visitor.*;
import controller.ObservableByGame;

import java.util.Observable;

/**
 * Clase abstracta que modela comportamientos genericos para los bricks especificos
 * @author Roberto Tapia
 */

public abstract class BrickClass extends Observable implements Brick, ObservableByGame {
    private int remainingHits;
    private int score;

    /**
     * Constructor de ladrillos
     * @param hits  Cantidad de golpes que el ladrillo podra recibir antes de destruirse
     * @param score Cantidad de puntos que el ladrillo entregara al ser destruido
     */
    BrickClass(int hits,int score){
        this.remainingHits=hits;
        this.score=score;
    }

    @Override
    public void accept(Visitor v) {
        v.visitBrick(this);
    }

    @Override
    public int getScore(){
        return this.score;
    }

    @Override
    public int remainingHits(){
        return this.remainingHits;
    }
    public void beHit(){this.remainingHits=this.remainingHits-1;}

    @Override
    public boolean isDestroyed(){
        if (this.remainingHits()==0) {
            return true;
        }
        return false;
    }
    @Override
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
