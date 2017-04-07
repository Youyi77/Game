package game.State;

import game.Entity.CrazyTank;
import game.Entity.Player;
import game.Manager.Direction;
import game.Manager.StateManager;
import game.Map.Map;
import game.Map.Tile;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Yasmeen Trifiss
 */
public class Level3State extends PlayState{

    private CrazyTank enemy;

    public Level3State(StateManager manager) {
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
        player = new Player(map,playerStartTile,Direction.RIGHT);
        enemy = new CrazyTank(map,enemyStartTile, Direction.LEFT,player);
        
    }

    @Override
    public void update() {
        
        enemy.setAction();
        enemy.updateTarget(player);

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
        
        // Draw enemy path
        if(enemy.path != null){
            Graphics2D g2d =(Graphics2D) g.create();
            for(Tile tile : enemy.path){
                g2d.setColor(Color.red);
                g2d.draw(tile.getRectangle());
            }
            g2d.dispose();
        }
        

        if (enemy.isDead) {
            g.drawString("LEVEL 3 COMPLETED", 350, 15);
            if (enemy.timeAfterDeath == 10) {
                goToNextLevel(StateManager.GAMEOVER);
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
