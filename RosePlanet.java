import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RosePlanet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RosePlanet extends Planet
{
    /**
     * Act - do whatever the RosePlanet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public RosePlanet() {
        //super(radius); // ����RandomPlanet�İ뾶
        setLocation(0, Greenfoot.getRandomNumber(276) + 150);
        speed = Greenfoot.getRandomNumber(3) + 1;
        
    }
    public void act()
    {
        // Add your action code here.
    }
    public void checkCollision(){
        Asteroids a = (Asteroids) getOneObjectAtOffset((int) speed + getImage().getWidth() / 2, 0, Asteroids.class);

    }
}
