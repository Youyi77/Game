/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.State;

import game.Image.Content;
import game.Main.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
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
        //g.drawImage(background, 0, 0, null);
        
        g.setColor(new Color(30,144,112));
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

        g.setColor(Color.white);
        //g.setFont(new Font("Arial Black", Font.BOLD, 30));
        g.drawString("Tank Game", 200, 100);
       
        switch(currentOption){
            case 0:
                g.drawImage(Content.SMOKE,180, 125,50,50,  null);
                break;
            case 1:
                g.drawImage(Content.SMOKE,180, 175,50,50,   null);
                break;
            case 2:
                g.drawImage(Content.SMOKE,180, 225,50,50,   null);
                break;
        }
        
        g.setColor(Color.blue);
        
        g.drawString(options[0], 200, 150);
        g.drawString(options[1], 200, 200);
        g.drawString(options[2], 200, 250);
    }

    private void select() {
        if (currentOption == 0) {
            //manager.setState(StateManager.LEVEL1STATE);
            manager.setState(StateManager.PLAY);
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
        if (k == KeyEvent.VK_DOWN) {
            currentOption--;
            if (currentOption == -1) {
                currentOption = options.length - 1;
            }
        }
        if (k == KeyEvent.VK_UP) {
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
