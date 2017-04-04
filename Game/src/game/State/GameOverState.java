package game.State;

import game.Main.GamePanel;
import game.Manager.StateManager;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author Yasmeen
 */
public class GameOverState extends State {
    

    public GameOverState(StateManager manager) {
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
        g.setColor(Color.BLACK);
	g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
	g.setColor(Color.WHITE);	
	g.drawString("GAME OVER", 20, 36);

    }

    @Override
    public void keyTyped(int k) {
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            manager.setState(StateManager.MENU);
        }
    }

    @Override
    public void keyReleased(int k) {
    }

}
