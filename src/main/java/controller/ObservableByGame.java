package controller;

/**
 * Comportamientos necesarios de una elemento que puede ser observado por un game (Levels y Bricks)
 * @author Roberto Tapia
 */

public interface ObservableByGame {
    /**
     * Informa a un game que tipo de objeto lo ha notificado para que este responda de la forma adecuada
     * @param g game que observa
     */
    void beObserved(Game g);
}
