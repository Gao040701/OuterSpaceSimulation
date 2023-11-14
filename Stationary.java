import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Stationary here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Stationary extends Being
{
    /**
     * Act - do whatever the Stationary wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    public boolean checkHitLittlePrince() {
        LittlePrince littlePrince = (LittlePrince) getOneIntersectingObject(LittlePrince.class); //return true if intersects
        if (littlePrince != null){
            return true;
        }
        return false;
    }
}
