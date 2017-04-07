package game.Entity;

import game.Manager.Content;
import game.Main.GamePanel;
import game.Manager.Direction;
import game.Manager.GameColor;
import game.Map.Map;
import game.Map.Tile;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Yasmeen Trifiss
 */
public abstract class Tank {

    public String name;
    public Map map;
    protected Tile position;
    protected int x, y, xdest, ydest;
    protected Color color;
    protected int speed;
    protected double angle;
    public Direction direction;
    protected BufferedImage tank, tankUp, tankDown, tankLeft, tankRight;
    public ArrayList<Bullet> bullets;
    public boolean isDead;
    public int timeAfterDeath = 0;

    
    public Tank(Map map, Tile position, Direction direction, GameColor gcolor) {
        this.map = map;
        this.position = position;
        this.x = position.getX();
        this.y = position.getY();
        this.xdest = x;
        this.ydest = y;
        this.speed = 1;
        this.direction = direction;
        this.isDead = false;
        this.bullets = new ArrayList();

        setColor(gcolor);
        setDirection();
    }

    /*
    * Store all images of the tank with a defined color
     */
    private void setColor(GameColor gcolor) {
        switch (gcolor) {
            case BLUE:
                tankUp = Content.TANK_BLUE_UP;
                tankRight = Content.TANK_BLUE_RIGHT;
                tankLeft = Content.TANK_BLUE_LEFT;
                tankDown = Content.TANK_BLUE_DOWN;
                color = Color.BLUE;
                break;
            case BEIGE:
                tankUp = Content.TANK_BEIGE_UP;
                tankRight = Content.TANK_BEIGE_RIGHT;
                tankLeft = Content.TANK_BEIGE_LEFT;
                tankDown = Content.TANK_BEIGE_DOWN;
                color = Color.LIGHT_GRAY;
                break;
            case BLACK:
                tankUp = Content.TANK_BLACK_UP;
                tankRight = Content.TANK_BLACK_RIGHT;
                tankLeft = Content.TANK_BLACK_LEFT;
                tankDown = Content.TANK_BLACK_DOWN;
                color = Color.BLACK;
                break;
            case RED:
                tankUp = Content.TANK_RED_UP;
                tankRight = Content.TANK_RED_RIGHT;
                tankLeft = Content.TANK_RED_LEFT;
                tankDown = Content.TANK_RED_DOWN;
                color = Color.RED;
                break;
            case GREEN:
                tankUp = Content.TANK_RED_UP;
                tankRight = Content.TANK_RED_RIGHT;
                tankLeft = Content.TANK_RED_LEFT;
                tankDown = Content.TANK_RED_DOWN;
                color = Color.GREEN;
                break;

        }
    }

    /**
     * Set tank image
     */
    public void setDirection() {
        switch (direction) {
            case UP:
                tank = tankUp;
                angle = 0;
                break;
            case DOWN:
                tank = tankDown;
                angle = 30;
                break;
            case LEFT:
                tank = tankLeft;
                angle = 45;
                break;
            case RIGHT:
                tank = tankRight;
                angle = 15;
                break;
            default:
                break;
        }
    }

    /*
    * Update tile position and remove dead bullets
     */
    private void update() {
        try {
            this.position = map.getTileFromPosition(x, y);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        removeDeadBullets();
    }

    /*
    * Check if there are dead bullets and remove them from the list
     */
    private void removeDeadBullets() {
        Bullet currentBullet = null;
        Iterator<Bullet> it = bullets.iterator();
        while (it.hasNext()) {
            currentBullet = it.next();
            if (currentBullet.isDead) {
                it.remove();
            }
        }
    }

    /**
     * Move to the right at speed defined
     */
    public void moveRight() {

        xdest = x + speed;
        ydest = y;

        if (map != null) {

            try {

                Tile tile = map.getTileFromPosition(xdest + 45, ydest);
                if (xdest < GamePanel.WIDTH - 50 && !tile.isBlocked()) {
                    x = xdest;
                }
                tank = tankRight;
                direction = Direction.RIGHT;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

    }

    /**
     * Move to the left at speed defined
     */
    public void moveLeft() {

        xdest = x - speed;
        ydest = y;

        if (map != null) {

            try {
                Tile tile = map.getTileFromPosition(xdest, ydest);
                if (0 < xdest && !tile.isBlocked()) {
                    x = xdest;
                }
                tank = tankLeft;
                direction = Direction.LEFT;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

    }

    /**
     * Move up at speed defined
     */
    public void moveUp() {

        xdest = x;
        ydest = y - speed;

        if (map != null) {

            try {
                Tile tile = map.getTileFromPosition(xdest, ydest);
                if (0 < ydest && !tile.isBlocked()) {
                    y = ydest;
                }
                tank = tankUp;
                direction = Direction.UP;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

    }

    /**
     * Move down at speed defined
     */
    public void moveDown() {

        xdest = x;
        ydest = y + speed;

        if (map != null) {

            try {
                Tile tile = map.getTileFromPosition(xdest, ydest + 45);
                if (ydest < GamePanel.HEIGHT - 40 && !tile.isBlocked()) {
                    y = ydest;
                }
                tank = tankDown;
                direction = Direction.DOWN;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

    }

    /**
     * Turn barrel to the left
     */
    public void turnBarrelLeft() {
        angle -= 1;
    }

    /**
     * Turn barrel to the right
     */
    public void turnBarrelRight() {
        angle += 1;
    }

    /**
     * Create a bullet and put it in bullets list
     */
    public void fire() {

        Point p = new Point(
                (int) (x + 45 / 2 + 30 * Math.cos((angle - 15) * 2.0 * Math.PI / 60)),
                (int) (y + 45 / 2 + 30 * Math.sin((angle - 15) * 2.0 * Math.PI / 60)));

        Bullet b = new Bullet(
                (int) (x + 45 / 2 + 20 * Math.cos((angle - 15) * 2.0 * Math.PI / 60)),
                (int) (y + 45 / 2 + 20 * Math.sin((angle - 15) * 2.0 * Math.PI / 60)), p);
        b.map = map;
        bullets.add(b);

    }

    /**
     * Move to the next tile and avoid obstacles
     *
     * @param nextTile
     */
    public void moveToNextTile(Tile nextTile) {
        if (nextTile != null) {
            int nextX = nextTile.getX();
            int nextY = nextTile.getY();

            if (x < nextX) {
                moveRight();
            }
            if (nextX < x) {
                moveLeft();
            }
            if (y < nextY) {
                moveDown();
            }
            if (nextY < y) {
                moveUp();
            }
        }
    }

    /**
     * @return true if a bullet hit another bullet
     */
    public boolean intersectsBullet(Bullet other) {
        return getRectangle().intersects(other.getRectangle());
    }

    /**
     * @return true if a tank hit another tank
     */
    public boolean intersectsTank(Tank other) {
        return getRectangle().intersects(other.getRectangle());
    }

    /**
     * @return rectangle representing tank in the drawing
     */
    public Rectangle getRectangle() {
        return new Rectangle(x, y, 45, 45);
    }

    public void draw(Graphics2D g) {

        if (!isDead) {
            update();

            // DRAW TANK
            g.drawImage(tank, x, y, 45, 45, null);

            // DRAW BARREL
            g.setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g.setPaint(color);
            g.drawLine(
                    (int) x + 45 / 2,
                    (int) y + 45 / 2,
                    (int) (x + 45 / 2 + 20 * Math.cos((angle - 15) * 2.0 * Math.PI / 60)),
                    (int) (y + 45 / 2 + 20 * Math.sin((angle - 15) * 2.0 * Math.PI / 60))
            );

            // DRAW BULLET
            Bullet bullet;
            Iterator<Bullet> it = bullets.iterator();
            while (it.hasNext()) {
                bullet = it.next();
                bullet.draw(g);
            }
        } else {
            timeAfterDeath++;
            bullets.removeAll(bullets);
        }

    }
}
