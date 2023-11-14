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
    protected int totalHP;
    protected int decreaseHP;
    protected boolean appear;
    protected boolean visited;
    public void act() {
       if(appear){
           move(speed); 
        /*{
            radius = 20;
        */
       checkCollision();
       //checkAndRemove();
    }
    }
    public Planet() {
        appear=true;
    }

    public abstract void checkCollision();
    //public abstract void checkAndRemove();
    public int getRadius(){
        return getImage().getHeight()/2;
    }
    
    public double getSpeed(){
        return speed;
    }
    
    public boolean hasVisited(){
        return visited;
    }
}
