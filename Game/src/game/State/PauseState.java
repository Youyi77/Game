/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.State;

import game.Image.Content;
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
        /*Content.drawString(g, "paused", 40, 30);

        Content.drawString(g, "arrow", 12, 76);
        Content.drawString(g, "keys", 16, 84);
        Content.drawString(g, ": move", 52, 80);

        Content.drawString(g, "space", 12, 96);
        Content.drawString(g, ": action", 52, 96);

        Content.drawString(g, "F1:", 36, 112);
        Content.drawString(g, "return", 68, 108);
        Content.drawString(g, "to menu", 68, 116);*/
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
