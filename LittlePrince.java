import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The little prince will walk around the planet and jump out of the planets
 * when all the baobab trees are destroyed.
 * @Zhiyu (Jennifer) Zhou
 * @Jan 7, 2022 - v1.0
 */
public class LittlePrince extends SuperSmoothMover
{
    private int speed;
    /**
     * Act - do whatever the LittlePrince wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        rotate();
    }

    public void rotate(){
        move(0.5);
        turn(0.2);
        rotate(20);
    }
    
    public void rotate(int planet){
        // setLocation (getWorld().getWidth()/2, getWorld().getHeight()/2);
        // setRotation(20.5);
        // turn(speed - 90);
        // turn(90);
        //setLocation (getWorld().getWidth()/2, getWorld().getHeight()/2);
        move(planet);
        turn(20);
    }
}
