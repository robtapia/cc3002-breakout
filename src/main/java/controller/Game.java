package controller;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import Visitor.BrickCounter;
import logic.brick.*;
import logic.level.Level;
import logic.level.NullLevel;
import logic.level.PlayableLevel;

/**
 * Game logic controller class.
 *
 * @author Juan-Pablo Silva
 */
public class Game implements Observer {
    private int balls;
    private Level level;
    private int currentScore;
    private int previousLevelsScore;
    public Game(int balls) {
        this.balls=balls;
        level=new NullLevel();
    }

    /**
     * This method is just an example. Change it or delete it at wish.
     * <p>
     * Checks whether the game has a winner or not
     *
     * @return true if the game has a winner, false otherwise
     */
    public boolean winner() {
        Level clevel=getCurrentLevel();
        Level nextLevel=clevel.getNextLevel();
        boolean a=clevel.isPlayableLevel();
        boolean b=!nextLevel.isPlayableLevel();
        boolean c=currentScore-previousLevelsScore==clevel.getPoints();
        boolean d=!clevel.isPlayableLevel();
        boolean e=currentScore>0;
        return ((d && e) ||(a && b && c));


    }

    public Level getCurrentLevel(){
        return level;
    }
    public void setCurrentLevel(Level l){
        level=l;

    }

    public int getCurrentPoints(){
        return currentScore;
    }
    public int getBallsLeft(){
        return this.balls;
    }
    public int dropBall(){
        if(this.balls>0) {
            this.balls =this.balls- 1;
            return balls;
        }
        return balls;
    }
    public boolean isGameOver(){
        return (getBallsLeft()==0||winner());
    }

    public Level newLevel(String name, int numberOfBricks, double probOfGlass, double probOfMetal, int seed){
        PlayableLevel newLevel =new PlayableLevel(name,numberOfBricks,probOfGlass,probOfMetal,seed);

        newLevel.addObserver(this);
        for(Brick b:newLevel.getBricks()){
            BrickClass bc= (BrickClass)b;
            bc.addObserver(this);
        }
        return newLevel;
    }

    @Override
    public void update(Observable o, Object arg) {
        if( o instanceof ObservableByGame){
            ((ObservableByGame) o).beObserved(this);
        }
    }
    public void observeGlassBrick(){
        currentScore=currentScore+50;
    }
    public void observeWoodenBrick(){
        currentScore=currentScore+200;}
    public void observeMetalBrick(){
        balls=balls+1;
    }
    public void observePlayableLevel(){
        previousLevelsScore=currentScore;
        setCurrentLevel(getCurrentLevel().getNextLevel());
    }
    public int numberOfBricks(){
        BrickCounter counter=new BrickCounter();
        counter.visitLevel(getCurrentLevel());
        return counter.getCounter();
    }

}
