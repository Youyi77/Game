package game.Map;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Yasmeen
 */
public class Tile {

    public static int tilesize = 50;
    public final int row, col;

    private final BufferedImage image;
    private int x, y;               // top-left of tile
    private int xCenter, yCenter;   // center of tile
    private boolean blocked;

    private int gCost;       // cost of the path from the start tile to itself
    private int hCost;       // straight-line cost from itself to the end
    private int fCost;       // estimation of total distance using this tile
    private Tile predecessor;

    public Tile(BufferedImage image, int row, int col, int x, int y, boolean blocked) {
        this.image = image;
        this.blocked = blocked;
        this.row=row;
        this.col=col;
        this.x = x;
        this.y = y;
        this.xCenter = (int) (x + tilesize / 2);
        this.yCenter = (int) (y + tilesize / 2);
        this.gCost = 0;
        this.hCost = 0;
        this.fCost = 0;
        this.predecessor = null;
    }

    public Rectangle getRectangle(){
        return new Rectangle(x,y,tilesize,tilesize);
    }
    
    public BufferedImage getImage() {
        return image;
    }

    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public int getxCenter() {
        return xCenter;
    }

    public int getyCenter() {
        return yCenter;
    }

    public int getgCost() {
        return gCost;
    }

    public int gethCost() {
        return hCost;
    }

    public int getfCost() {
        return fCost;
    }

    public Tile getPredecessor() {
        return predecessor;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setxCenter(int xCenter) {
        this.xCenter = xCenter;
    }

    public void setyCenter(int yCenter) {
        this.yCenter = yCenter;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public void setgCost(int gCost) {
        this.gCost = gCost;
    }

    public void sethCost(int hCost) {
        this.hCost = hCost;
    }

    public void setfCost(int fCost) {
        this.fCost = fCost;
    }

    public void setPredecessor(Tile predecessor) {
        this.predecessor = predecessor;
    }
    
    

}
