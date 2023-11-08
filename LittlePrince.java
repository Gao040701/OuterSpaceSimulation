import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The little prince will walk around the planet and jump out of the planets
 * when all the baobab trees are destroyed.
 * @Zhiyu (Jennifer) Zhou
 * @Jan 7, 2022 - v1.0
 */
public class LittlePrince extends SuperSmoothMover
{
    private int rotationalSpeed;
    /**
     * Act - do whatever the LittlePrince wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        rotate();
    }

    public void rotate(){
        move(2);
        turn(1.5);
    }
}
