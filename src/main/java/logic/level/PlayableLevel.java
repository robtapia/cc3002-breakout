package logic.level;

import logic.brick.Brick;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Observable;
import java.util.Observer;


public class LevelClass extends Observable implements Level,Observer {
    String name;
    Level nextlvl;
    List<Brick> bricks;
    List<GlassBrick> glassBricks;
    List<WoodenBrick>woodenBricks;
    int currentPoints;


    public LevelClass(String name, int numberOfBricks, double probOfGlass, double probOfMetal,int seed){
        this.name=name;
        bricks=new ArrayList<>();
        glassBricks=new ArrayList<>();
        woodenBricks=new ArrayList<>();
        currentPoints=0;
        nextlvl=this;
    }
    public LevelClass(){
        this("",0,0,0,0);
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getNumberOfBricks() {
        return bricks.size();
    }

    @Override
    public List<Brick> getBricks() {
        return bricks;
    }

    @Override
    public Level getNextLevel() {
        return nextlvl;
    }

    @Override
    public boolean isPlayableLevel() {
        return false;
    }

    @Override
    public boolean hasNextLevel()
    {
        return nextlvl!=this;
    }

    @Override
    public int getPoints() {
        return glassBricks.size()*50+woodenBricks.size()*200;
    }

    @Override
    public Level addPlayingLevel(Level level) {
        if (nextlvl==null){setNextLevel(level);}
        else{
            nextlvl.addPlayingLevel(level);
        }
        return null; //WTF?
    }

    @Override
    public void setNextLevel(Level level) {
        nextlvl=level;

    }

    @Override
    public void update(Observable o, Object arg) {
        bricks.remove(o);
        this.notifyObservers();
    }
}
