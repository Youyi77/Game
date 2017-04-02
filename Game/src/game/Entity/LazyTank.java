package game.Entity;

/**
 *
 * @author Yasmeen
 */
public class LazyTank extends Tank{
    
    private int p = 0;
    
    public LazyTank(int x, int y) {
        super(x, y, "BEIGE");
    }

    
    public void move(){
        //this.turnBarrelLeft();
        
        if((p%10)==0){
            this.fire();
        }
        p++;
        
        
    }
}
