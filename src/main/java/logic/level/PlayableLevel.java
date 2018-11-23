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


public class PlayableLevel extends Observable implements Level,Observer {
    String name;
    Level nextlvl;
    List<Brick> bricks;
    List<GlassBrick> glassBricks;
    List<WoodenBrick>woodenBricks;
    int currentPoints;


    public PlayableLevel(String name, int numberOfBricks, double probOfGlass, double probOfMetal,int seed){
        this.name=name;
        bricks=new ArrayList<>();
        glassBricks=new ArrayList<>();
        woodenBricks=new ArrayList<>();
        currentPoints=0;
        nextlvl=new NullLevel();
        Random random=new Random(seed);
        for(int i=0;i<numberOfBricks;i++){
            if(random.nextDouble()<probOfGlass){
                GlassBrick gb=new GlassBrick();
                glassBricks.add(gb);
                bricks.add(gb);
            }
            else{
                WoodenBrick wb=new WoodenBrick();
                woodenBricks.add(wb);
                bricks.add(wb);
            }
            if(random.nextDouble()<probOfMetal){
                MetalBrick mb=new MetalBrick();
                bricks.add(mb);
            }
        }
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
        return true;
    }

    @Override
    public boolean hasNextLevel()
    {
        return getNextLevel().isPlayableLevel();
    }

    @Override
    public int getPoints() {
        return glassBricks.size()*50+woodenBricks.size()*200;

    }

    @Override
    public Level addPlayingLevel(Level level) {
        nextlvl=nextlvl.addPlayingLevel(level);
        return this;
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
