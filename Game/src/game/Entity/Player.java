package game.Entity;

import game.Manager.Direction;
import game.Manager.GameColor;
import game.Map.Map;
import game.Map.Tile;

/**
 *
 * @author Yasmeen Trifiss
 */
public class Player extends Tank {

    public Player(Map map, Tile position, Direction direction) {
        super(map, position, direction, GameColor.BLUE);
        this.name = "Player";
        this.speed = 10;
    }
    

}
