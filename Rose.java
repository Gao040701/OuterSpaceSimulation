import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Rose here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rose extends Stationary
{
    private GreenfootImage rose = new GreenfootImage("rose.png");
    /**
     * Act - do whatever the Rose wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (isTouching(LittlePrince.class)){
            Greenfoot.setWorld(new EndScreen());
        }
    }
    public Rose(){
        setImage(rose);
        
    }
}
