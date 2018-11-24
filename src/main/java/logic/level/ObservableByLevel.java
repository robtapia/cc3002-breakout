package logic.level;


/**
 * Comportamientos necesarios de un elemento que puede ser observado por un Level (bricks)
 * @author Roberto Tapia
 */
public interface ObservableByLevel {
    /**
     * Informa a un level que tipo de objeto lo notifico para que este responda de manera adecuada
     * @param l
     */
    void beObservedByLevel(Level l);
}
