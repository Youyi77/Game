package game.State;

import game.Manager.StateManager;
import game.Manager.Content;
import game.Main.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author Yasmeen Trifiss
 */
public class IntroState extends State {

    private int alpha;
    private int ticks;

    private final int FADE_IN = 60;
    private final int LENGTH = 60;
    private final int FADE_OUT = 60;

    public IntroState(StateManager manager) {
        super(manager);
    }

    @Override
    public void init() {
        ticks = 0;
    }

    @Override
    public void update() {
        ticks++;
        if (ticks < FADE_IN) {
            alpha = (int) (255 - 255 * (1.0 * ticks / FADE_IN));
            if (alpha < 0) {
                alpha = 0;
            }
        }
        if (ticks > FADE_IN + LENGTH) {
            alpha = (int) (255 * (1.0 * ticks - FADE_IN - LENGTH) / FADE_OUT);
            if (alpha > 255) {
                alpha = 255;
            }
        }
        if (ticks > FADE_IN + LENGTH + FADE_OUT) {
            manager.setState(StateManager.MENU);
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        g.drawImage(Content.INTRO, 100, 100, null);
        g.setColor(new Color(0, 0, 0, alpha));
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
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
