package game.State;

import game.Manager.StateManager;
import game.Entity.Player;
import game.Entity.RollingTank;
import game.Manager.Direction;
import game.Map.Map;
import game.Map.Tile;
import java.awt.Graphics2D;

/**
 *
 * @author Yasmeen
 */
public class Level2State extends PlayState {

    private RollingTank enemy;

    public Level2State(StateManager manager) {
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
        Tile enemyStartTile = map.getTile(3, 9);

        // Initialize tanks
        player = new Player(map,playerStartTile,Direction.RIGHT);
        enemy = new RollingTank(map,enemyStartTile, Direction.LEFT,player);

    }

    @Override
    public void update() {
        enemy.setAction();

        // Check whether a bullet cross another bullet
        handleBulletsIntersection(player, enemy);

        handleTankHitByBullet(player, enemy);
        handleTankHitByBullet(enemy, player);

    }

    @Override
    public void draw(Graphics2D g) {
        map.draw(g);
        player.draw(g);
        enemy.draw(g);

        if (enemy.isDead) {
            g.drawString("LEVEL 2 COMPLETED", 350, 15);
            if (enemy.timeAfterDeath == 10) {
                goToNextLevel(StateManager.LEVEL3);
            }
        }
        
        if (player.isDead) {
            g.drawString("YOU LOSER", 350, 15);
            if (player.timeAfterDeath == 10) {
                goToNextLevel(StateManager.GAMEOVER);
            }
        }

    }
}

   