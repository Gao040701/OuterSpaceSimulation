import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The little prince will walk around the planet and jump out of the planets
 * when all the baobab trees are destroyed.
 * @Zhiyu (Jennifer) Zhou
 * @Jan 7, 2022 - v1.0
 */
public class LittlePrince extends Character
{
    private int speed;
    private Planet touchingPlanet;
    /**
     * Act - do whatever the LittlePrince wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (getOneIntersectingObject(Planet.class) != null){
            touchingPlanet = (Planet)getOneIntersectingObject(Planet.class);
            rotate(touchingPlanet.getRadius());
        }
    }

    public void rotate(double planet){
        move(planet);
        turn(0.5);
    }
}
