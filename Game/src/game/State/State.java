package game.State;

import game.Manager.StateManager;

/**
 *
 * @author Yasmeen
 */
public abstract class State{
    
    protected StateManager manager;

    public State(StateManager manager) {
        this.manager = manager;
    }
    
    public abstract void init();
    public abstract void update();
    public abstract void draw(java.awt.Graphics2D g);
    public abstract void keyPressed(int k);
    public abstract void keyReleased(int k);
    public abstract void keyTyped(int k);
    
}
