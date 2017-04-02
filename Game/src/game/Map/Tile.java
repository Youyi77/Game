package game.Map;

import java.awt.image.BufferedImage;

/**
 *
 * @author Yasmeen
 */
public class Tile {

    public static int tilesize = 50;

    private final BufferedImage image;
    private int x, y;               // top-left of tile
    private int xCenter, yCenter;   // center of tile
    private boolean blocked;

    private double gCost;       // cost of the path from the start tile to itself
    private double hCost;       // straight-line cost from itself to the end
    private double fCost;       // estimation of total distance using this tile
    private Tile predecessor;

    public Tile(BufferedImage image, int x, int y, boolean blocked) {
        this.image = image;
        this.blocked = blocked;
        this.x = x;
        this.y = y;
        this.xCenter = (int) (x + tilesize / 2);
        this.yCenter = (int) (y + tilesize / 2);
        this.gCost = 0;
        this.hCost = 0;
        this.fCost = 0;
        this.predecessor = null;
    }

    public BufferedImage getImage() {
        return image;
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

    public double getgCost() {
        return gCost;
    }

    public double gethCost() {
        return hCost;
    }

    public double getfCost() {
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

    public void setgCost(double gCost) {
        this.gCost = gCost;
    }

    public void sethCost(double hCost) {
        this.hCost = hCost;
    }

    public void setfCost(double fCost) {
        this.fCost = fCost;
    }

    public void setPredecessor(Tile predecessor) {
        this.predecessor = predecessor;
    }
    
    

}
