package game.State;

import game.Behaviour.Astar;
import game.Manager.StateManager;
import game.Entity.LazyTank;
import game.Entity.Player;
import game.Manager.Direction;
import game.Map.Map;
import game.Map.Tile;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Yasmeen
 */
public class Level1State extends PlayState {

    
    private LazyTank enemy;
    private int count;
    private ArrayList<Tile> path;

    public Level1State(StateManager manager) {
        super(manager);
    }

    

    @Override
    public void init() {

        final int[][] mapArray = new int[][]{
            {1, 1, 4, 4, 0, 0, 0, 0, 0, 0},
            {1, 1, 4, 3, 0, 0, 0, 4, 4, 0},
            {1, 1, 1, 0, 0, 4, 0, 3, 0, 0},
            {1, 1, 1, 0, 0, 4, 0, 0, 0, 0},
            {1, 1, 1, 0, 0, 4, 0, 0, 0, 0},
            {1, 1, 4, 0, 0, 0, 0, 3, 0, 0},
            {1, 1, 4, 4, 3, 4, 4, 4, 0, 0},};

        map = new Map(mapArray);

        // Set tanks position
        Tile playerStartTile = map.getTile(3, 0);
        Tile enemyStartTile = map.getTile(4, 9);

        // Initialize tanks
        player = new Player(playerStartTile.getX(), playerStartTile.getY());
        enemy = new LazyTank(enemyStartTile.getX(), enemyStartTile.getY());
        player.setDirection(Direction.RIGHT);
        enemy.setDirection(Direction.LEFT);

        // Initialize map
        player.setMap(map);
        enemy.setMap(map);
        
        Astar pathfinding = new Astar(map);
        boolean tamere = pathfinding.shortestPath(enemyStartTile, playerStartTile);
        if(tamere)
        path = pathfinding.getBestPath();
        System.out.println(path);
        this.count=0;
        
        

    }

    @Override
    public void update() {
        //enemy.move();

        if(count>=path.size()){
        count=0;
        }
        enemy.setX(path.get(count).getX());
        enemy.setY(path.get(count).getY());
       /* System.out.println(path.get(0));
        System.out.println(path.get(count).);*/
        // Check whether a bullet cross another bullet
        handleBulletsIntersection(player, enemy);

        handleTankHitByBullet(player, enemy);
        handleTankHitByBullet(enemy, player);
        count++;

    }
    
    

    @Override
    public void draw(Graphics2D g) {
        map.draw(g);
        player.draw(g);
        enemy.draw(g);

        if (enemy.isDead) {
            g.drawString("LEVEL 1 COMPLETED", 350, 15);
            if (enemy.timeAfterDeath == 10) {
                goToNextLevel(StateManager.LEVEL2);
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
