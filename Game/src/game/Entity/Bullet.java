package game.Entity;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 *
 * @author Yasmeen
 */
public class Bullet implements Serializable{
    
    private static final long serailVersionUID =1L;
    /*private final long timeBetweenNewBullets = 1;
    private final long timeOfLastCreatedBullet = 0;*/
    private int x, y;
    private final int speed = 3;
    private final BufferedImage image;
    private double movingXspeed, movingYspeed;
    private final Point direction;
    public boolean isDead;
    private int distance;


    public Bullet(BufferedImage image, int x, int y, Point direction) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.isDead=false;
        this.direction=direction;
        setDirectionAndSpeed();
        distance =0 ;
    }
    

private void setDirectionAndSpeed()
    {
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
    
    public void update()
    {
        x += movingXspeed;
        y += movingYspeed;
        distance ++;
        //System.out.println((int)x);
        if (distance > 200){
            isDead = true;
            System.out.println("is dead");
        }
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
    

    public void draw(Graphics2D g)
    {
        update();
        //g.drawImage(image, (int)x, (int)y, 10, 12, null);
        g.drawRect(x, y, 1, 1);
        
    }
    
    
}
