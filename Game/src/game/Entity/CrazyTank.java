package game.Entity;

import game.Interface.AI;
import game.Manager.Direction;
import game.Manager.GameColor;
import game.Map.Map;
import game.Map.Tile;
import game.Pathfinding.Astar;
import java.util.ArrayList;

/**
 *
 * @author Yasmeen Trifiss
 */
public class CrazyTank extends Tank implements AI {

    public Tank target;
    private Astar pathfinding;
    boolean pathFound;
    public ArrayList<Tile> path;
    public int count;
    public int time;

    public CrazyTank(Map map, Tile position, Direction direction, Tank target) {
        super(map, position, direction, GameColor.RED);
        this.name = "Crazy";
        this.speed = 1;
        this.target = target;
        this.pathfinding = null;
        this.pathFound = false;
        this.path = new ArrayList();
        this.count = 0;
        this.time = 0;
    }

    /**
     * Find best path using A*
     */
    private void initPath() {
        if (map != null) {
            pathfinding = new Astar(map);

            if (target.position != null) {
                this.pathFound = pathfinding.shortestPath(this.position, target.position);
                if (pathFound) {
                    path = pathfinding.getBestPath();
                }
            }
        }
    }

    @Override
    public void setAction() {

        // Find best path to reach target
        initPath();

        // Chase after target
        if (count < path.size() - 1) {
            if (path.get(count) != null) {

                this.moveToNextTile(path.get(count));
                setDirection();
                if (this.position == path.get(count)) {
                    count++;
                }
            }
        }

        // Shoot target when tank is near
        if ((count == path.size() - 1) && (time % 100 == 0)) {
            fire();
        }
        time++;
    }

    @Override
    public void updateTarget(Tank target) {
        this.target = target;
    }

}
