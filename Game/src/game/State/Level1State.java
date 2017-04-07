package game.State;

import game.Manager.StateManager;
import game.Entity.LazyTank;
import game.Entity.Player;
import game.Manager.Direction;
import game.Map.Map;
import game.Map.Tile;
import java.awt.Graphics2D;

/**
 *
 * @author Yasmeen
 */
public class Level1State extends PlayState {

    private LazyTank enemy1;
    private LazyTank enemy2;

    public Level1State(StateManager manager) {
        super(manager);
    }

    @Override
    public void init() {

        // Create map
        final int[][] mapArray = new int[][]{
            {1, 1, 4, 4, 0, 0, 0, 0, 4, 4},
            {1, 1, 4, 0, 0, 0, 0, 0, 0, 4},
            {1, 1, 1, 0, 0, 4, 0, 0, 0, 0},
            {1, 1, 1, 0, 0, 4, 0, 0, 0, 0},
            {1, 1, 1, 0, 0, 4, 0, 0, 0, 0},
            {1, 1, 4, 0, 0, 0, 0, 3, 0, 0},
            {1, 1, 4, 4, 3, 4, 4, 4, 0, 0},};

        map = new Map(mapArray);

        // Set tanks position
        Tile playerStartTile = map.getTile(3, 0);
        Tile enemy1StartTile = map.getTile(0, 5);
        Tile enemy2StartTile = map.getTile(6, 9);

        // Initialize tanks
        player = new Player(map, playerStartTile, Direction.RIGHT);
        enemy1 = new LazyTank(map, enemy1StartTile, Direction.DOWN, player);
        enemy2 = new LazyTank(map, enemy2StartTile, Direction.UP, player);

    }

    @Override
    public void update() {

        enemy1.setAction();
        enemy1.updateTarget(player);

        enemy2.setAction();
        enemy2.updateTarget(player);

        // Check whether a bullet cross another bullet
        handleBulletsIntersection(player, enemy1);
        handleBulletsIntersection(player, enemy2);

        handleTankHitByBullet(player, enemy1);
        handleTankHitByBullet(enemy1, player);

        handleTankHitByBullet(player, enemy2);
        handleTankHitByBullet(enemy2, player);

    }

    @Override
    public void draw(Graphics2D g) {
        map.draw(g);
        player.draw(g);
        enemy1.draw(g);
        enemy2.draw(g);

        // If player destroy all anemies, go to the next level
        if (enemy1.isDead && enemy2.isDead) {
            g.drawString("LEVEL 1 COMPLETED", 350, 15);
            if (enemy2.timeAfterDeath > 10 && enemy1.timeAfterDeath > 10) {
                goToNextLevel(StateManager.LEVEL2);
            }
        }

        // If player is dead, game is over
        if (player.isDead) {
            g.drawString("YOU LOSE", 350, 15);
            if (player.timeAfterDeath == 10) {
                goToNextLevel(StateManager.GAMEOVER);
            }
        }

    }

}
