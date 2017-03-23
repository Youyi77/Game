/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Map;

import java.awt.image.BufferedImage;

/**
 *
 * @author Yasmeen
 */
public class Tile {

    private final BufferedImage image;
    private int x, y;
    private final int type;

    // tile types
    public static final int NORMAL = 0;
    public static final int BLOCKED = 1;

    public Tile(BufferedImage image, int x, int y, int type) {
        this.image = image;
        this.type = type;
        this.x = x;
        this.y = y;
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

    public int getType() {
        return type;
    }

}
