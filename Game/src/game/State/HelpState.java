package game.State;

import game.Main.GamePanel;
import game.Manager.StateManager;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author Yasmeen Trifiss
 */
public class HelpState extends State {

    public HelpState(StateManager manager) {
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
        int y = 100;
        int scale = 20;
        g.drawString("Your mission is to destroy all of the enemy tanks.", 20, 36);
        g.drawString("Press [ Up ] or [ W ] to move up", 20, y);
        g.drawString("Press [ Down ] or [ S ] to move down", 20, y + scale);
        g.drawString("Press [ Left ] or [ A ] to move to the left", 20, y + scale * 2);
        g.drawString("Press [ Right ] or [ D ] to move to the right", 20, y + scale * 3);
        g.drawString("Press [ J ] to turn your gun to the left", 20, y + scale * 4);
        g.drawString("Press [ K ] to turn your gun to the right", 20, y + scale * 5);
        g.drawString("Press [ ESCAPE ] to pause the game", 20, y + scale * 6);
        g.drawString("Press [ F1 ] to stop pause in the game", 20, y + scale * 7);
        g.drawString("> Press Enter to return to menu.", 20, y + scale * 10);
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

    @Override
    public void keyTyped(int k) {
    }

}
