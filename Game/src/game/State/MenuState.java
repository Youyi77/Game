package game.State;

import game.Manager.StateManager;
import game.Manager.Content;
import game.Main.GamePanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

/**
 *
 * @author Yasmeen
 */
public class MenuState extends State {

    private int currentOption = 0;
    private String[] options = {
        "START",
        "HELP",
        "QUIT"
    };

    public MenuState(StateManager manager) {
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

        // SET BACKGROUND
        g.drawImage(Content.MENUBG, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);

        // SET TITLE
        Graphics2D g2d=(Graphics2D) g.create();
        g2d.setColor(Color.white);
        g2d.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.drawString("GET THEM RIGHT", 110, 100);

        // DRAW SMOKE
        int y = 145;
        int yScale = 50;
        int x = 200;
        int xScale = 20;
        switch (currentOption) {
            case 0:
                g.drawImage(Content.SMOKE, x, y, 50, 50, null);
                break;
            case 1:
                g.drawImage(Content.SMOKE, x, y + yScale, 50, 50, null);
                break;
            case 2:
                g.drawImage(Content.SMOKE, x, y + yScale * 2, 50, 50, null);
                break;
        }

        // DRAW OPTIONS
        g2d.setColor(Color.blue);
        g2d.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        y = 170;
        g2d.drawString(options[0], x + xScale, y);
        g2d.drawString(options[1], x + xScale, y + yScale);
        g2d.drawString(options[2], x + xScale, y + yScale * 2);

    }

    private void select() {
        if (currentOption == 0) {
            manager.setState(StateManager.LEVEL1);
        }
        if (currentOption == 1) {
            // help
        }
        if (currentOption == 2) {
            System.exit(0);
        }
    }

    @Override
    public void keyTyped(int k) {
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            select();
        }
        if (k == KeyEvent.VK_UP) {
            currentOption--;
            if (currentOption == -1) {
                currentOption = options.length - 1;
            }
        }
        if (k == KeyEvent.VK_DOWN) {
            currentOption++;
            if (currentOption == options.length) {
                currentOption = 0;
            }
        }
    }

    @Override
    public void keyReleased(int k) {
    }

}
