/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Interface;

import game.Entity.Tank;

/**
 *
 * @author Yasmeen
 */
public interface AI {
    
    public void setAction();
    public void updateTarget(Tank target);
}
