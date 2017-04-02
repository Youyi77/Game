package game.Entity;

import game.Map.Map;
import game.Map.Tile;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 *
 * @author Yasmeen
 */
public class Bullet implements Serializable{
    
    private static final long serialVersionUID =1L;
    private int x, y, xdest, ydest;
    private final int speed = 3;
    private final BufferedImage image;
    private double movingXspeed, movingYspeed;
    private final Point direction;
    public boolean isDead;
    private int distance;
    public Map map;


    public Bullet(BufferedImage image, int x, int y, Point direction) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.xdest = x;
        this.ydest = y;
        this.isDead=false;
        this.direction=direction;
        setDirectionAndSpeed();
        distance =0 ;
        map =null;
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
        xdest += movingXspeed;
        ydest += movingYspeed;
        
        if (map != null) {
            
            try {
                Tile tile = map.getTileFromPosition(xdest, ydest);
                if (!tile.isBlocked()) {
                    x = xdest;
                    y = ydest;
                    distance ++;
                }
                else{
                    isDead = true;
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        
        if (isOut()){
            isDead = true;
        }
    }
    public void setMap(Map map) {
        this.map = map;
    }
    public boolean isOut(){
        return distance > 200 ;
    }
    
    public boolean intersectsObstacle(Tile t){
        return getRectangle().intersects(t.getRectangle());
    }
    
    public boolean intersectsBullet(Bullet b){
        return getRectangle().intersects(b.getRectangle());
    }
    
    public Rectangle getRectangle(){
        return new Rectangle(x,y,10,12);
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
        //g.drawImage(image, x, y, 10, 12, null);
        g.setStroke(new BasicStroke(6, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.drawRect(x, y, 1, 1);
        
    }
    
    
}
