import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class roseicon here.
 * 
 * @author Jiayi Li 
 * @version November 2023
 */
public class Roseicon extends Stationary
{
    /**
     * Act - do whatever the roseicon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage rose = new GreenfootImage("roseCovered.png");
    public void act()
    {
        // Add your action code here.
        //add an object on the blue bar
    }
    public Roseicon(){
        setImage(rose);
    }
}
