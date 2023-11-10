import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Moving here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Moving extends Being
{
    private int speed;
    private Planet touchingPlanet;
    /**
     * Act - do whatever the Character wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (getOneIntersectingObject(Planet.class) != null){
            touchingPlanet = (Planet)getOneIntersectingObject(Planet.class);
            System.out.println("planet radius: " + touchingPlanet.getRadius());
            rotate(touchingPlanet.getRadius()/100);
        }
        //rotate(119/100);
    }

    public void rotate(double planet){
        move(planet);
        turn(1);
        //move(getPlanetSpeed(this));
    }
}
