package visitor;

import logic.brick.Brick;
import logic.level.Level;

/**
 * Visitor que cuenta la cantidad de bricks sin romper en un level
 * @author Roberto Tapia
 */
public class BrickCounter implements Visitor {
    private int brickcount;

    /**
     * Crea un BrickCounter con un contador inicial en 0.
     */
    public BrickCounter(){
        brickcount=0;
    }
    @Override
    public void visitBrick(Brick b){
        if (!b.isDestroyed()) {
            brickcount += 1;
        }
    }
    @Override
    public void visitLevel(Level l){
        for (Brick b:l.getBricks()){
            b.accept(this);
        }
    }

    /**
     * Entrega la cantidad de bricks visitados que no estaban rotos
     * @return  cantidad de bricks que no estaban rotos
     */
    public int getCounter(){
        return brickcount;
    }

}
