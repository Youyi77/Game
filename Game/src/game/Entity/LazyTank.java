package game.Entity;

import game.Interface.AI;
import game.Manager.Direction;
import static game.Manager.Direction.UP;
import game.Manager.GameColor;
import game.Map.Map;
import game.Map.Tile;

/**
 *
 * @author Yasmeen Trifiss
 */
public class LazyTank extends Tank implements AI {

    private Tank target;
    private boolean barrelUp;
    private final int timeUntilNextBullet = 100;
    private int count;

    public LazyTank(Map map, Tile position, Direction direction, Tank target) {
        super(map, position, direction, GameColor.BEIGE);
        this.name = "Lazy";
        this.target = target;
        setBarrelUp();
        count = 0;
    }

    /*
    * Set barrelUp true if the tank is heading up
     */
    private void setBarrelUp() {
        if (direction == UP) {
            this.barrelUp = true;
        } else {
            this.barrelUp = false;
        }

    }

    /**
     * Get the distance between target and tank, shoot according to the distance
     */
    @Override
    public void setAction() {

        int distanceBetweenTanks = target.x - x;

        // Tank heading up
        if (barrelUp) {
            if (distanceBetweenTanks > 0 && angle < 7) {
                turnBarrelRight();
            }
            if (distanceBetweenTanks < 0 && angle > -3) {
                turnBarrelLeft();
            }
            if (distanceBetweenTanks == 0) {
                angle = 0;
            }
        }

        // Tank heading down
        if (!barrelUp) {
            if (distanceBetweenTanks < 0 && angle < 35) {
                turnBarrelRight();
            }
            if (distanceBetweenTanks > 0 && angle > 25) {
                turnBarrelLeft();
            }
            if (distanceBetweenTanks == 0) {
                angle = 30;
            }
        }

        // If distance is inferior than 100, tank can shoot
        if (Math.abs(distanceBetweenTanks) < 100) {
            if (count == 0 || count == timeUntilNextBullet) {
                fire();
                count = 1;
            }
            count++;
        }

    }

    @Override
    public void updateTarget(Tank target) {
        this.target = target;
    }

}
