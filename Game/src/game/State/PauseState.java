package game.State;

import game.Manager.StateManager;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author Yasmeen
 */
public class PauseState extends State {

    public PauseState(StateManager manager) {
        super(manager);
    }

    @Override
    public void init() {
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics2D g) {
        /*g.drawString( "paused", 40, 30);

        g.drawString("arrow", 12, 76);
        g.drawString("keys", 16, 84);
        g.drawString(": move", 52, 80);

        g.drawString("space", 12, 96);
        g.drawString(": action", 52, 96);

        g.drawString("F1:", 36, 112);
        g.drawString("return", 68, 108);
        g.drawString("to menu", 68, 116);*/
    }

    
    @Override
    public void keyTyped(int k) {
    }
    
    @Override
    public void keyPressed(int k) {
        switch (k) {
            case KeyEvent.VK_ESCAPE:
                manager.setPaused(false);
                break;
            case KeyEvent.VK_F1:
                manager.setPaused(false);
                manager.setState(StateManager.MENU);
                break;

        }
    }

    @Override
    public void keyReleased(int k) {
    }

}
