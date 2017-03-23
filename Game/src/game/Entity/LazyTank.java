package game.Entity;

/**
 *
 * @author Yasmeen
 */
public class LazyTank extends Tank{
    
    public LazyTank(int x, int y) {
        super(x, y, "BEIGE");
    }

    
    public void move(){
        this.turnBarrelLeft();
    }
}
