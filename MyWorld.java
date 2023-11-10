import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private int numOfAsteriods=3;
    private int numOfPlanets = 1; 
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 576, 1, false);
        prepare();
        
    }
    
    private void prepare() {
        for (int i = 0; i < numOfAsteriods; i++) {
            addObject(new Asteroids(), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        }
        addObject(new LittlePrince(), 100, 100);

        HomePlanet homePlanet = new HomePlanet();
        addObject(homePlanet, getWidth() / 2, getHeight() / 2);
        addObject(new RandomPlanet(), 0, Greenfoot.getRandomNumber(276) + 150);
    }

}
