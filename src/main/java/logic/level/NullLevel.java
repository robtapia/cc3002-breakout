package logic.level;

import logic.brick.Brick;

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
}
