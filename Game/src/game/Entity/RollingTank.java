package game.Entity;

import game.Interface.AI;
import game.Main.GamePanel;
import game.Manager.Direction;
import game.Manager.GameColor;
import game.Map.Map;
import game.Map.Tile;

/**
 *
 * @author Yasmeen
 */
public class RollingTank extends Tank implements AI {

    public Tank target;
    public int count;
    public boolean up;

    public RollingTank(Map map, Tile position, Direction direction, Tank target) {
        super(map, position, direction, GameColor.BLACK);
        this.name = "Rolling";
        this.speed = 2;
        this.target = target;
        this.count = 0;
        this.up = true;
    }

    /**
     * Move up and down, shoot when is up and when is down
     */
    @Override
    public void setAction() {
        if (up) {
            moveUp();
        } else {
            moveDown();
        }
        
        // tank is up
        if (y < 5) {
            up = false;
            fire();
        }
        
        // tank is down
        if (y > GamePanel.HEIGHT - 45) {
            up = true;
            fire();
        }
    }

    @Override
    public void updateTarget(Tank target) {
        this.target = target;
    }

}
