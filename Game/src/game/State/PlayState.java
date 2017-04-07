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
 * @author Yasmeen Trifiss
 */
public abstract class PlayState extends State{
    
    public Map map;
    public Player player;
    
    public PlayState(StateManager manager) {
        super(manager);
    }
    
    /**
     * If a bullet hit another bullet, the two bullets die
     */
    public void handleBulletsIntersection(Tank current, Tank opponent){
        Bullet bullet;
        Iterator<Bullet> it = current.bullets.iterator();
        while (it.hasNext()) {
            bullet = it.next();
            for (Bullet b : opponent.bullets) {
                if (bullet.intersectsBullet(b)) {
                    bullet.isDead = true;
                    b.isDead = true;
                }
            }
        }
    }

    /**
     * If oppenent bullet hit current tank, current tank die
     */
    public void handleTankHitByBullet(Tank current, Tank opponent) {
        Bullet bullet;
        Iterator<Bullet> it = opponent.bullets.iterator();
        while (it.hasNext()) {
            bullet = it.next();
            if (current.intersectsBullet(bullet)) {
                current.isDead = true;
                bullet.isDead = true;
            }
        }
    }
    
    /**
     * Wait 3 seconds and go to the next level
     */
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

    /**
     * Set action for the player
     */
    @Override
    public void keyPressed(int k) {
        switch (k) {
            case KeyEvent.VK_ESCAPE:
                //manager.setPaused(true);
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
