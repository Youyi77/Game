package game.State;

import game.Manager.StateManager;
import game.Entity.LazyTank;
import game.Entity.Player;
import game.Map.Map;
import game.Map.Tile;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author Yasmeen
 */
public class Level2State extends State {

    private Map map;
    private Player player;
    private LazyTank enemy;

    public Level2State(StateManager manager) {
        super(manager);
    }

    @Override
    public void init() {

        final int[][] mapArray = new int[][]{
            {0, 0, 0, 0, 0, 0, 0, 0, 2, 2},
            {0, 0, 0, 0, 0, 0, 0, 0, 2, 2},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 3, 4, 3, 4, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {2, 2, 0, 0, 0, 0, 0, 0, 2, 2},
            {2, 2, 0, 0, 0, 0, 0, 0, 2, 2},};

        map = new Map(mapArray);

        // Set tanks position
        Tile playerStartTile = map.getTile(3, 0);
        Tile enemyStartTile = map.getTile(6, 9);

        // Initialize tanks
        player = new Player(playerStartTile.getX(), playerStartTile.getY());
        enemy = new LazyTank(enemyStartTile.getX(), enemyStartTile.getY());
        
        player.setMap(map);
        enemy.setMap(map);
    }

    @Override
    public void update() {
        enemy.move();
    }

    @Override
    public void draw(Graphics2D g) {
        map.draw(g);
        player.draw(g);
        enemy.draw(g);
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
