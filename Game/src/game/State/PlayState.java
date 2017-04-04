/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.State;

import game.Entity.Bullet;
import game.Entity.Player;
import game.Entity.Tank;
import game.Manager.StateManager;
import game.Map.Map;
import java.awt.event.KeyEvent;
import java.util.Iterator;

/**
 *
 * @author Yasmeen
 */
public abstract class PlayState extends State{
    
    public Map map;
    public Player player;
    
    public PlayState(StateManager manager) {
        super(manager);
    }
    
    public void handleBulletsIntersection(Tank current, Tank opposite){
        Bullet bullet;
        Iterator<Bullet> it = current.bullets.iterator();
        while (it.hasNext()) {
            bullet = it.next();
            for (Bullet b : opposite.bullets) {
                if (bullet.intersectsBullet(b)) {
                    bullet.isDead = true;
                    b.isDead = true;
                }
            }
        }
    }

    public void handleTankHitByBullet(Tank current, Tank opposite) {
        Bullet bullet;
        Iterator<Bullet> it = opposite.bullets.iterator();
        while (it.hasNext()) {
            bullet = it.next();
            if (current.intersectsBullet(bullet)) {
                current.isDead = true;
                bullet.isDead = true;
            }
        }
    }
    
    public void goToNextLevel(int level) {
        try {
            Thread.sleep(3000);
            manager.setState(level);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }
    
    @Override
    public void keyTyped(int k) {

    }

    @Override
    public void keyPressed(int k) {
        switch (k) {
            case KeyEvent.VK_ESCAPE:
                manager.setPaused(true);
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                player.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                player.moveRight();
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                player.moveUp();
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                player.moveDown();
                break;
            case KeyEvent.VK_J:
                player.turnBarrelLeft();
                break;
            case KeyEvent.VK_K:
                player.turnBarrelRight();
                break;
            case KeyEvent.VK_SPACE:
                player.fire();
                break;

        }
    }

    @Override
    public void keyReleased(int k) {

    }
    
}
