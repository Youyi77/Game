package game.Entity;

import game.Map.Tile;
import java.util.ArrayList;

/**
 *
 * @author Yasmeen
 */
public class LazyTank extends Tank{
    
    private int p = 0;
    
    public LazyTank(int x, int y) {
        super(x, y, "BEIGE");
        this.name="Lazy";
    }

    
    public void move(){
        //this.turnBarrelLeft();
        
        if((p%30)==0){
            this.fire();
        }
        p++;
  
    }
    
}
