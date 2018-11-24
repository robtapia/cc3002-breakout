package controller;

import java.util.Observable;
import java.util.Observer;

import visitor.BrickCounter;
import logic.brick.*;
import logic.level.Level;
import logic.level.NullLevel;
import logic.level.PlayableLevel;

/**
 * Game logic controller class.
 *
 * @author Roberto Tapia
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
     * Checks whether the game has a winner or not
     *
     * @return true if the game has a winner, false otherwise
     *
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

    /**
     * Entrega el nivel actual del juego.
     * @return nivel actual.
     */
    public Level getCurrentLevel(){
        return level;
    }

    /**
     * Cambia el nivel actual del juego.
     * @param l nivel que sera asignado como actual.
     */
    public void setCurrentLevel(Level l){
        level=l;

    }

    /**
     * Entrega el puntaje total del juego.
     * @return  puntaje actual.
     */
    public int getCurrentPoints(){
        return currentScore;
    }

    /**
     * Entrega la cantidad de bolas restantes.
     * @return  cantidad de bolas restantes.
     */
    public int getBallsLeft(){
        return this.balls;
    }

    /**
     * Pierde una bola.
     * @return  cantidad de bolas luego de actualizar.
     */
    public int dropBall(){
        if(this.balls>0) {
            this.balls =this.balls- 1;
            return balls;
        }
        return balls;
    }

    /**
     * Revisa si el juego termino o no (se perdieron todas las bolas o se gano el juego).
     * @return  true si el juego termino, falso en caso contrario
     */
    public boolean isGameOver(){
        return (getBallsLeft()==0||winner());
    }

    /**
     * Crea un nuevo level, lo observa, observa sus ladrillos.
     *
     * @param name  nombre del level a crear.
     * @param numberOfBricks    cantidad total de ladrillos a crear.
     * @param probOfGlass       probabilidad de que un ladrillo cualquiera sea un glassBrick.
     * @param probOfMetal       Probabilidad de que por cada ladrillo generado, se genere ademas uno de metal.
     * @param seed              seed para la pseudo aleatoreidad de los ladrillos.
     * @return                  Se retorna el level creado con los parametros dados.
     */
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

    /**
     * Game responde a recibir una notificacion de que se rompio un GlassBrick
     */
    public void observeGlassBrick(){
        currentScore=currentScore+50;
    }
    /**
     * Game responde a recibir una notificacion de que se rompio un WoodenBrick
     */
    public void observeWoodenBrick(){
        currentScore=currentScore+200;
    }
    /**
     * Game responde a recibir una notificacion de que se rompio un MetalBrick
     */
    public void observeMetalBrick(){
        balls=balls+1;
    }
    /**
     * Game responde a recibir una notificacion de que se completo un level
     */
    public void observePlayableLevel(){
        previousLevelsScore=currentScore;
        setCurrentLevel(getCurrentLevel().getNextLevel());

    }

    /**
     * Cuenta cuantos ladrillos quedan por romper en el level actual
     * @return Cantidad de ladrillos sin romper en el level actual
     */
    public int numberOfBricks(){
        BrickCounter counter=new BrickCounter();
        counter.visitLevel(getCurrentLevel());
        return counter.getCounter();
    }

}
