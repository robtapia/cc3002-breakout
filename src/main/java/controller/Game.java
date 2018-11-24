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
        //return (getCurrentLevel().isPlayableLevel()&&!(getCurrentLevel().getNextLevel().isPlayableLevel())&&numberOfBricks()==0);
        boolean a=getCurrentLevel().isPlayableLevel();
        boolean b=!getCurrentLevel().getNextLevel().isPlayableLevel();
        boolean c=currentScore-previousLevelsScore==getCurrentLevel().getPoints();




        return ((!getCurrentLevel().isPlayableLevel()&&currentScore>0) ||(a&&b && c));


    }
    public boolean hasNextLevel() {
        return getCurrentLevel().getNextLevel().isPlayableLevel();
    }
    public void goNextLevel(){
        setCurrentLevel(getCurrentLevel().getNextLevel());
    }

    public boolean hasCurrentLevel(){
        return getCurrentLevel().isPlayableLevel();
    }
    public String getLevelName(){
        return getCurrentLevel().getName();
    }
    public Level getCurrentLevel(){
        return level;
    }
    public void setCurrentLevel(Level l){
        level=l;

    }
    public void addPlayingLevel(Level l){
        getCurrentLevel().setNextLevel(getCurrentLevel().getNextLevel().addPlayingLevel(l));
    }
    public int getLevelPoints(){
        return getCurrentLevel().getPoints();
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
    public List<Brick> getBricks(){
        return getCurrentLevel().getBricks();
    }
    public Level newLevel(String name, int numberOfBricks, double probOfGlass, double probOfMetal, int seed){
        Level newLevel =new PlayableLevel(name,numberOfBricks,probOfGlass,probOfMetal,seed);

        ((PlayableLevel) newLevel).addObserver(this);
        for(Brick b:newLevel.getBricks()){
            BrickClass bc= (BrickClass)b;
            bc.addObserver(this);
        }
        return newLevel;
    }

    @Override
    public void update(Observable o, Object arg) {
        if( o instanceof GlassBrick){
            currentScore=currentScore+50;

        }
        if( o instanceof WoodenBrick){
            currentScore=currentScore+200;

        }
        if( o instanceof MetalBrick){
            balls=balls+1;
        }
        if( o instanceof PlayableLevel){
            previousLevelsScore=currentScore;

            goNextLevel();

        }

    }
    public int numberOfBricks(){
        BrickCounter counter=new BrickCounter();
        List<Brick> aux=getCurrentLevel().getBricks();
        for (Brick b:aux){
            b.accept(counter);
        }
        return counter.getCounter();
    }

}
