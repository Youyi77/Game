package game.State;

import game.Entity.Bullet;
import game.Manager.StateManager;
import game.Entity.LazyTank;
import game.Entity.Player;
import game.Entity.Tank;
import game.Map.Map;
import game.Map.Tile;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yasmeen
 */
public class Level1State extends State {

    private Map map;
    private Player player;
    private LazyTank enemy;

    public Level1State(StateManager manager) {
        super(manager);
    }

    @Override
    public void init() {

        final int[][] mapArray = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 3, 3, 3, 0, 0, 0, 0, 0},
            {1, 1, 3, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 4, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 3, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 3, 4, 4, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},};

        map = new Map(mapArray);

        // Set tanks position
        Tile playerStartTile = map.getTile(3, 0);
        Tile enemyStartTile = map.getTile(6, 9);

        // Initialize tanks
        player = new Player(playerStartTile.getX(), playerStartTile.getY());
        enemy = new LazyTank(enemyStartTile.getX(), enemyStartTile.getY());

        // Initialize map
        player.setMap(map);
        enemy.setMap(map);

    }

    @Override
    public void update() {
        enemy.move();

        // Check whether a bullet cross another bullet
        Bullet bullet;
        Iterator<Bullet> it = player.bullets.iterator();
        while (it.hasNext()) {
            bullet = it.next();
            for (Bullet b : enemy.bullets) {
                if (bullet.intersectsBullet(b)) {
                    bullet.isDead = true;
                    b.isDead = true;
                }
            }
        }

        hitByEnemyBullet(player, enemy);
        hitByEnemyBullet(enemy, player);

    }

    public void hitByEnemyBullet(Tank current, Tank enemy) {
        Bullet bullet;
        Iterator<Bullet> it = enemy.bullets.iterator();
        while (it.hasNext()) {
            bullet = it.next();
            if (current.intersectsBullet(bullet)) {
                current.isDead = true;
                bullet.isDead = true;
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        map.draw(g);
        player.draw(g);
        enemy.draw(g);

        if (enemy.isDead) {
            g.drawString("LEVEL 1 COMPLETED", 350, 15);
            if (enemy.timeAfterDeath == 10) {
                goToNextLevel();
            }
        }

    }

    public void goToNextLevel() {
        try {
            Thread.sleep(4000);
            manager.setState(manager.LEVEL2);
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
