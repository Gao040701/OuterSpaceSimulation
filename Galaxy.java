import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Galaxy extends World
{
    private int numOfAsteriods=3;
    private int numOfPlanets = 1; 
    private SuperStatBar clueBar;
    private int clueCount = 0;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Galaxy()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 576, 1, false);
        clueBar = new SuperStatBar(110, clueCount, null, 110, 10, 0, Color.RED, Color.BLACK, false, Color.BLACK, 1);
        addObject(clueBar, getWidth() - 60, 80);
        prepare();
    }

    private void prepare() {
        for (int i = 0; i < numOfAsteriods; i++) {
            addObject(new Asteroids(), getAsteroidsX(), getAsteroidsY());
        }
        addObject(new LittlePrince(), 100, 100);
        if (Greenfoot.getRandomNumber (2) == 0){
            addObject (new Fox(), 0 + 50, Greenfoot.getRandomNumber(276) + 200);
        }
        HomePlanet homePlanet = new HomePlanet();
        addObject(homePlanet, getWidth() / 2, getHeight() / 2);
        addObject(new RandomPlanet(), 0, Greenfoot.getRandomNumber(276) + 150);
    }
    
    public int getAsteroidsX(){
        int getAsteroidsX = Greenfoot.getRandomNumber(getWidth());
        return getAsteroidsX;
    }
    
    public int getAsteroidsY(){
        int getAsteroidsY = Greenfoot.getRandomNumber(getHeight());
        return getAsteroidsY;
    }

    public void act(){
        clueBar.update(clueCount);
    }
}
