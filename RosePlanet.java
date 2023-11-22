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
    public static int planetImgIndex, length;
    private GreenfootImage RosePlanet = new GreenfootImage("planets/planet0.png");
    public RosePlanet() {
        //super(radius); // ÉèÖÃRandomPlanetµÄ°ë¾¶
        setLocation(0, Greenfoot.getRandomNumber(200) + 250);
        speed = Greenfoot.getRandomNumber(1) + 1;
        
        length = 200 + Greenfoot.getRandomNumber(41); // 200 + 0 to 40
        RosePlanet.scale(length, length);
        setImage(RosePlanet);
    }
    public void act()
    {
        // Add your action code here.
        super.act();
    }
    public void checkAndRemove ()
    {
        if (totalHP == 0) 
        {
            getWorld().removeObject(this); // ... remove me from the World
        }
    }
    public void checkCollision(){
        Asteroids a = (Asteroids) getOneObjectAtOffset((int) speed + getImage().getWidth() / 2, 0, Asteroids.class);

    }
}
