package game.Entity;

import game.Manager.Content;
import game.Main.GamePanel;
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
 * @author Yasmeen
 */
public abstract class Tank {

    public int timeAfterDeath = 0;
    private int x, y, xdest, ydest;
    private double angle;
    private BufferedImage tank, tankUp, tankDown, tankLeft, tankRight, bullet;
    private Bullet b;
    public ArrayList<Bullet> bullets;
    public boolean isDead, gotonextlevel;
    private Color color;
    public Map map;

    public Tank(int x, int y, String tankColor) {
        this.x = x;
        this.y = y;
        this.xdest = x;
        this.ydest = y;
        setColor(tankColor);
        tank = tankUp;
        angle = 0;
        b = null;
        map = null;
        isDead = false;
        gotonextlevel = false;
        bullets = new ArrayList();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private void setColor(String tankColor) {
        switch (tankColor) {
            case "BLUE":
                tankUp = Content.TANK_BLUE_UP;
                tankRight = Content.TANK_BLUE_RIGHT;
                tankLeft = Content.TANK_BLUE_LEFT;
                tankDown = Content.TANK_BLUE_DOWN;
                bullet = Content.BULLET_BLUE;
                color = Color.BLUE;
                break;
            case "BEIGE":
                tankUp = Content.TANK_BEIGE_UP;
                tankRight = Content.TANK_BEIGE_RIGHT;
                tankLeft = Content.TANK_BEIGE_LEFT;
                tankDown = Content.TANK_BEIGE_DOWN;
                bullet = Content.BULLET_BEIGE;
                color = Color.LIGHT_GRAY;
                break;
            case "BLACK":
                tankUp = Content.TANK_BLACK_UP;
                tankRight = Content.TANK_BLACK_RIGHT;
                tankLeft = Content.TANK_BLACK_LEFT;
                tankDown = Content.TANK_BLACK_DOWN;
                bullet = Content.BULLET_BLACK;
                color = Color.BLACK;
                break;
            case "RED":
                tankUp = Content.TANK_RED_UP;
                tankRight = Content.TANK_RED_RIGHT;
                tankLeft = Content.TANK_RED_LEFT;
                tankDown = Content.TANK_RED_DOWN;
                bullet = Content.BULLET_RED;
                color = Color.RED;
                break;
            case "GREEN":
                tankUp = Content.TANK_RED_UP;
                tankRight = Content.TANK_RED_RIGHT;
                tankLeft = Content.TANK_RED_LEFT;
                tankDown = Content.TANK_RED_DOWN;
                bullet = Content.BULLET_RED;
                color = Color.GREEN;
                break;

        }
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    private void update() {

        Bullet bullet;
        Iterator<Bullet> it = bullets.iterator();
        while (it.hasNext()) {
            bullet = it.next();
            if (bullet.isDead) {
                it.remove();
            }
        }

    }

    public void moveRight() {

        xdest = x + 10;
        ydest = y;

        if (map != null) {

            try {

                Tile tile = map.getTileFromPosition(xdest + 45, ydest);
                if (xdest < GamePanel.WIDTH - 50 && !tile.isBlocked()) {
                    x = xdest;
                }
                tank = tankRight;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

    }

    public void moveLeft() {

        xdest = x - 10;
        ydest = y;

        if (map != null) {

            try {
                Tile tile = map.getTileFromPosition(xdest, ydest);
                if (0 < xdest && !tile.isBlocked()) {
                    x = xdest;
                }
                tank = tankLeft;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

    }

    public void moveUp() {

        xdest = x;
        ydest = y - 10;

        if (map != null) {

            try {
                Tile tile = map.getTileFromPosition(xdest, ydest);
                if (0 < ydest && !tile.isBlocked()) {
                    y = ydest;
                }
                tank = tankUp;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

    }

    public void moveDown() {

        xdest = x;
        ydest = y + 10;

        if (map != null) {

            try {
                Tile tile = map.getTileFromPosition(xdest, ydest + 45);
                if (ydest < GamePanel.HEIGHT - 50 && !tile.isBlocked()) {
                    y = ydest;
                }
                tank = tankDown;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

    }

    public void turnBarrelLeft() {
        angle -= 1;
    }

    public void turnBarrelRight() {
        angle += 1;
    }

    public void fire() {

        Point p = new Point(
                (int) (x + 45 / 2 + 30 * Math.cos((angle - 15) * 2.0 * Math.PI / 60)),
                (int) (y + 45 / 2 + 30 * Math.sin((angle - 15) * 2.0 * Math.PI / 60)));

        Bullet b = new Bullet(
                bullet,
                (int) (x + 45 / 2 + 20 * Math.cos((angle - 15) * 2.0 * Math.PI / 60)),
                (int) (y + 45 / 2 + 20 * Math.sin((angle - 15) * 2.0 * Math.PI / 60)), p);
        b.setMap(map);
        bullets.add(b);

    }

    public boolean intersectsBullet(Bullet b) {
        return getRectangle().intersects(b.getRectangle());
    }

    public boolean intersectsTank(Tank t) {
        return getRectangle().intersects(t.getRectangle());
    }

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
        }
        else{
            timeAfterDeath++;
        }

        /* g.setColor(Color.blue);
        g.drawLine((int)xBarrel, (int)yBarrel,
        (int) (10 + 70*Math.cos((angle-15) * 2.0 * Math.PI / 60)), 
        (int) (10 + 70*Math.sin((angle-15) * 2.0 * Math.PI / 60)));
        g.drawRect(i,j, 1, 1);
        g.setColor(Color.red);*/
    }
}
