import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fox extends Being
{
    private int speed;
    private Planet touchingPlanet;
    /**
     * Act - do whatever the Fox wants to do. This method is called whenever
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
