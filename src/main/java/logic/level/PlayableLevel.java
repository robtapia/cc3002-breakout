package logic.level;

import controller.Game;
import logic.brick.*;
import visitor.Visitor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Observable;
import java.util.Observer;

/**
 * Establece el comportamiento de un nivel jugable
 * @author Roberto Tapia
 */
public class PlayableLevel extends Observable implements Level,Observer {
    String name;
    Level nextlvl;
    List<Brick> bricks;
    List<GlassBrick> glassBricks;
    List<WoodenBrick>woodenBricks;
    int currentPoints;

    /**
     * Constructor, genera niveles de manera pseudo-aleatoria en base a las probabilidades y el seed que le son entregados.
     * @param name              nombre del nivel a crear.
     * @param numberOfBricks    cantidad total de glass y wooden bricks a crear.    
     * @param probOfGlass       probabilidad de que un brick en particular sea un GlassBrick.
     * @param probOfMetal       probabilidad de que por cada GlassBrick o WoodenBrick generado, se genera ademas un MetalBrick.
     * @param seed              seed que se usara para controlar la pseudo-aleatoriedad con el objetivo de poder testear.
     */
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
                gb.addObserver(this);
                glassBricks.add(gb);
                bricks.add(gb);
            }
            else{
                WoodenBrick wb=new WoodenBrick();
                woodenBricks.add(wb);
                wb.addObserver(this);
                bricks.add(wb);
            }
        }
        for (int j=0;j<numberOfBricks;j++){
            if(random.nextDouble()<probOfMetal){
                MetalBrick mb=new MetalBrick();
                mb.addObserver(this);
                bricks.add(mb);

            }
        }
    }
    @Override
    public void accept(Visitor visitor){
        visitor.visitLevel(this);

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
    public void beObserved(Game g){
        g.observePlayableLevel();
    }

    @Override
    public void update(Observable o, Object arg) {

        if(o instanceof ObservableByLevel){
            ((ObservableByLevel) o).beObservedByLevel(this);
        }
        if(glassBricks.size()==0 && woodenBricks.size()==0) {
            setChanged();
            notifyObservers();
        }
    }

    @Override
    public void levelObserveGlassBrick(Brick gb) {
        glassBricks.remove(gb);
    }

    @Override
    public void levelObserveWoodenBrick(Brick wb) {
        woodenBricks.remove(wb);
    }
}
