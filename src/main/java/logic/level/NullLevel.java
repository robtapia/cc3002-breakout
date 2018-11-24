package logic.level;

import Visitor.Visitor;
import controller.Game;
import logic.brick.Brick;
import logic.brick.WoodenBrick;

import java.util.ArrayList;
import java.util.List;

public class NullLevel implements Level {
    public NullLevel(){

    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public int getNumberOfBricks() {
        return 0;
    }

    public List<WoodenBrick> getWoodenBricks(){return null;}

    @Override
    public List<Brick> getBricks() {
        return new ArrayList<>();
    }

    @Override
    public Level getNextLevel() {
        return this;
    }

    @Override
    public boolean isPlayableLevel() {
        return false;
    }

    @Override
    public boolean hasNextLevel() {
        return false;
    }

    @Override
    public int getPoints() {
        return 0;
    }

    @Override
    public Level addPlayingLevel(Level level) {
        return level;
    }

    @Override
    public void setNextLevel(Level level) {

    }

    @Override
    public void levelObserveGlassBrick(Brick b) {
        //Does nothing, there are no glass bricks in a null level
    }

    @Override
    public void levelObserveWoodenBrick(Brick b) {
        //Does nothing, there are no wooden bricks in a null level
    }

    public void accept(Visitor v){
        //Does nothing.
    }
    public void beObserved(Game g){
        //Does nothing.
    }
}
