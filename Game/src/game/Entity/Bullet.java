package game.Entity;

import game.Map.Map;
import game.Map.Tile;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;

/**
 *
 * @author Yasmeen
 */
public class Bullet implements Serializable {

    private static final long serialVersionUID = 1L;
    private int x, y, xdest, ydest;
    private final int speed = 3;
    private double movingXspeed, movingYspeed;
    private final Point direction;
    public boolean isDead;
    private int distance;
    public Map map;

    public Bullet(int x, int y, Point direction) {
        this.x = x;
        this.y = y;
        this.xdest = x;
        this.ydest = y;
        this.isDead = false;
        this.direction = direction;
        init();
        distance = 0;
        map = null;
    }

    /**
     * Set direction and speed of the bullet
     */
    private void init() {
        // Unit direction vector of the bullet.
        double directionVx = direction.x - this.x;
        double directionVy = direction.y - this.y;
        double lengthOfVector = Math.sqrt(directionVx * directionVx + directionVy * directionVy);
        directionVx = directionVx / lengthOfVector;
        directionVy = directionVy / lengthOfVector;

        // Set speed.
        this.movingXspeed = speed * directionVx;
        this.movingYspeed = speed * directionVy;
    }

    /**
     * Update bullet position
     */
    public void update() {
        xdest += movingXspeed;
        ydest += movingYspeed;

        if (map != null) {

            try {
                Tile tile = map.getTileFromPosition(xdest, ydest);
                if (!tile.isBlocked()) {
                    x = xdest;
                    y = ydest;
                    distance++;
                } else {
                    isDead = true;
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

        if (isOut()) {
            isDead = true;
        }
    }

    /**
     * @return true if bullet is out of frame
     */
    public boolean isOut() {
        return distance > 200;
    }

    /**
     * @return true if bullet hit obstacle
     */
    public boolean intersectsObstacle(Tile obstacle) {
        return getRectangle().intersects(obstacle.getRectangle());
    }

    /**
     * @return true if bullet hit another bullet
     */
    public boolean intersectsBullet(Bullet other) {
        return getRectangle().intersects(other.getRectangle());
    }

    /**
     * @return rectangle representing bullet in the drawing
     */
    public Rectangle getRectangle() {
        return new Rectangle(x, y, 10, 12);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point getPoint() {
        return direction;
    }

    /**
     * Draw bullet
     */
    public void draw(Graphics2D g) {
        update();
        g.setStroke(new BasicStroke(6, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.drawRect(x, y, 1, 1);
    }

}
