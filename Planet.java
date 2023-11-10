import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
//only have xSpeed and it can be random every 400 count
//there is a distance of 700 bewtween them
/**
 * Write a description of class Planet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Planet extends SuperSmoothMover
{
    /**
     * The planets will rotate on its own and move to the right
     */
    protected int radius;
    protected double speed;
    
    public void act() {
        /*{
            radius = 20;
        */
    }
    public Planet() {
        
    }

    public abstract void checkCollision();

    public int getRadius(){
        return radius;
    }
}
