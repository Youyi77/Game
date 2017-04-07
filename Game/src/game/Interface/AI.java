package game.Interface;

import game.Entity.Tank;

/**
 *
 * @author Yasmeen Trifiss
 */
public interface AI {
    
    public void setAction();
    public void updateTarget(Tank target);
}
