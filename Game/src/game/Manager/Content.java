package game.Manager;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author Yasmeen
 */
public class Content {

    public static BufferedImage MENUBG = load("/Backgrounds/menubg1.jpg");
    public static BufferedImage BAR = load("/Smoke/smokeOrange0.png");

    public static BufferedImage SMOKE = load("/Smoke/smokeWhite5.png");
    
    // ENVIRONMENT
    public static BufferedImage GRASS = load("/Environment/grass.png");
    public static BufferedImage SAND = load("/Environment/sand.png");
    public static BufferedImage MUD = load("/Environment/dirt.png");
    public static BufferedImage BIGTREE = load("/Environment/treeLarge.png");
    public static BufferedImage SMALLTREE = load("/Environment/treeSmall.png");
    
    // TANK
    public static BufferedImage BARREL_BLUE = load("/Tanks/barrelBlue.png");
    public static BufferedImage TANK_BLUE_UP = load("/Tanks/tankBlue_up.png");
    public static BufferedImage TANK_BLUE_RIGHT = load("/Tanks/tankBlue_right.png");
    public static BufferedImage TANK_BLUE_LEFT = load("/Tanks/tankBlue_left.png");
    public static BufferedImage TANK_BLUE_DOWN = load("/Tanks/tankBlue_down.png");
    public static BufferedImage BULLET_BLUE = load("/Bullets/bulletBlueSilver_outline.png");
    
    public static BufferedImage BARREL_BEIGE = load("/Tanks/barrelBeige.png");
    public static BufferedImage TANK_BEIGE_UP = load("/Tanks/tankBeige_up.png");
    public static BufferedImage TANK_BEIGE_RIGHT = load("/Tanks/tankBeige_right.png");
    public static BufferedImage TANK_BEIGE_LEFT = load("/Tanks/tankBeige_left.png");
    public static BufferedImage TANK_BEIGE_DOWN = load("/Tanks/tankBeige_down.png");
    public static BufferedImage BULLET_BEIGE = load("/Bullets/bulletBeigeSilver_outline.png");
    
    public static BufferedImage BARREL_BLACK = load("/Tanks/barrelBlack.png");
    public static BufferedImage TANK_BLACK_UP = load("/Tanks/tankBlack_up.png");
    public static BufferedImage TANK_BLACK_RIGHT = load("/Tanks/tankBlack_right.png");
    public static BufferedImage TANK_BLACK_LEFT = load("/Tanks/tankBlack_left.png");
    public static BufferedImage TANK_BLACK_DOWN = load("/Tanks/tankBlack_down.png");
    public static BufferedImage BULLET_BLACK = load("/Bullets/bulletYellowSilver_outline.png");
    
    public static BufferedImage BARREL_GREEN = load("/Tanks/barrelGreen.png");
    public static BufferedImage TANK_GREEN_UP = load("/Tanks/tankGreen_up.png");
    public static BufferedImage TANK_GREEN_RIGHT = load("/Tanks/tankGreen_right.png");
    public static BufferedImage TANK_GREEN_LEFT = load("/Tanks/tankGreen_left.png");
    public static BufferedImage TANK_GREEN_DOWN = load("/Tanks/tankGreen_down.png");
    public static BufferedImage BULLET_GREEN = load("/Bullets/bulletGreenSilver_outline.png");
    
    public static BufferedImage BARREL_RED = load("/Tanks/barrelRed.png");
    public static BufferedImage TANK_RED_UP = load("/Tanks/tankRed_up.png");
    public static BufferedImage TANK_RED_RIGHT = load("/Tanks/tankRed_right.png");
    public static BufferedImage TANK_RED_LEFT = load("/Tanks/tankRed_left.png");
    public static BufferedImage TANK_RED_DOWN = load("/Tanks/tankRed_down.png");
    public static BufferedImage BULLET_RED = load("/Bullets/bulletRedSilver_outline.png");
    
    // TEXT
    public static BufferedImage INTRO = load("/Text/intro.png");
    public static BufferedImage font = load("/Smoke/smokeYellow0.png");
    
    public static BufferedImage load(String s) {
        try {
            BufferedImage image = ImageIO.read(Content.class.getResourceAsStream(s));
            return image;
        } catch (Exception e) {
            System.out.println("Error loading graphics.");
            System.exit(0);
        }
        return null;
    }



}
