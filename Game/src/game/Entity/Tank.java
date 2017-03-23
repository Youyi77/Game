package game.Entity;

import game.Image.Content;
import game.Main.GamePanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Yasmeen
 */
public abstract class Tank {

    public int count = 0;
    private int x, y;
    private double angle;
    private BufferedImage tank, tankUp, tankDown, tankLeft, tankRight, barrel, bullet;
    private Bullet b;
    private ArrayList<Bullet> bullets;
    private double xBarrel, yBarrel;
    private double xCenterBarrel, yCenterBarrel;
    private final int barrelWidth, barrelHeight;
    public boolean isDead;
    private Color color;

    public Tank(int x, int y, String tankColor) {
        this.x = x;
        this.y = y;
        setColor(tankColor);
        tank = tankUp;
        angle = 0;
        b = null;
        barrelWidth = 12;
        barrelHeight = 30;
        isDead = false;
        bullets = new ArrayList();
    }

    private void setColor(String tankColor) {
        switch (tankColor) {
            case "BLUE":
                tankUp = Content.TANK_BLUE_UP;
                tankRight = Content.TANK_BLUE_RIGHT;
                tankLeft = Content.TANK_BLUE_LEFT;
                tankDown = Content.TANK_BLUE_DOWN;
                barrel = Content.BARREL_BLUE;
                bullet = Content.BULLET_BLUE;
                color=Color.BLUE;
                break;
            case "BEIGE":
                tankUp = Content.TANK_BEIGE_UP;
                tankRight = Content.TANK_BEIGE_RIGHT;
                tankLeft = Content.TANK_BEIGE_LEFT;
                tankDown = Content.TANK_BEIGE_DOWN;
                barrel = Content.BARREL_BEIGE;
                bullet = Content.BULLET_BEIGE;
                color=Color.LIGHT_GRAY;
                break;
            case "BLACK":
                tankUp = Content.TANK_BLACK_UP;
                tankRight = Content.TANK_BLACK_RIGHT;
                tankLeft = Content.TANK_BLACK_LEFT;
                tankDown = Content.TANK_BLACK_DOWN;
                barrel = Content.BARREL_BLACK;
                bullet = Content.BULLET_BLACK;
                color=Color.BLACK;
                break;
            case "RED":
                tankUp = Content.TANK_RED_UP;
                tankRight = Content.TANK_RED_RIGHT;
                tankLeft = Content.TANK_RED_LEFT;
                tankDown = Content.TANK_RED_DOWN;
                barrel = Content.BARREL_RED;
                bullet = Content.BULLET_RED;
                color=Color.RED;
                break;
            case "GREEN":
                tankUp = Content.TANK_RED_UP;
                tankRight = Content.TANK_RED_RIGHT;
                tankLeft = Content.TANK_RED_LEFT;
                tankDown = Content.TANK_RED_DOWN;
                barrel = Content.BARREL_RED;
                bullet = Content.BULLET_RED;
                color=Color.GREEN;
                break;

        }
    }

    private void update() {
        this.xBarrel = this.x + 17;
        this.yBarrel = this.y + 17;
        this.xCenterBarrel = xBarrel + barrelWidth - 8;
        this.yCenterBarrel = yBarrel + barrelHeight;

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
        if (x < GamePanel.WIDTH - 50) {
            x = x + 10;
        }
        tank = tankRight;

    }

    public void moveLeft() {
        if (0 < x) {
            x = x - 10;
        }
        tank = tankLeft;
    }

    public void moveUp() {
        if (0 < y) {
            y = y - 10;
        }
        tank = tankUp;
    }

    public void moveDown() {
        if (y < GamePanel.HEIGHT - 50) {
            y = y + 10;
        }
        tank = tankDown;
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
        bullets.add(b);

    }

    public void draw(Graphics2D g) {
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

        /*  g.setColor(Color.blue);
        g.drawLine((int)xBarrel, (int)yBarrel,
        (int) (10 + 70*Math.cos((angle-15) * 2.0 * Math.PI / 60)), 
        (int) (10 + 70*Math.sin((angle-15) * 2.0 * Math.PI / 60)));
        g.drawRect(i,j, 1, 1);
        g.setColor(Color.red);*/

    }
}
