import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Stationary class represents a stationary actor in the game.
 * It extends the Being class and provides a method to check for intersection with the Little Prince.
 * 
 * @Author Angela Gao
 * @version November 2023
 */
public class Stationary extends Being
{
    /**
     * Act method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     * No specific action is performed in this class.
     */
    public void act()
    {
        // Add your action code here.
    }
    /**
     * Checks if the Stationary actor is hitting the Little Prince.
     * 
     * @return True if the Stationary actor intersects with the Little Prince, false otherwise.
     */
    public boolean checkHitLittlePrince() {
        LittlePrince littlePrince = (LittlePrince) getOneIntersectingObject(LittlePrince.class); //return true if intersects
        if (littlePrince != null){
            return true;
        }
        return false;
    }
}
