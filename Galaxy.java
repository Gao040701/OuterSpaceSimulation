import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Galaxy extends World
{
    public static int numOfAsteriods=3;
    private int numOfPlanets = 1; 
    private SuperStatBar clueBar;
    private int clueCount = 0;
    public static final int Rhp=100;
    public static final int Rdecrease=20;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Galaxy()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 576, 1, false);
        setPaintOrder (Hitbox.class, SuperStatBar.class, Asteroids.class, Being.class, Planet.class); 
        clueBar = new SuperStatBar(110, clueCount, null, 110, 10, 0, Color.RED, Color.BLACK, false, Color.BLACK, 1);
        addObject(clueBar, getWidth() - 60, 80);
        prepare();
    }
    
    /**
     * Credit: Method created by Jiayi Li, modified by Zhiyu (Jennifer) Zhou
     */
    private void prepare() {
        for (int i = 0; i < numOfAsteriods; i++) {
            addObject(new Asteroids(), getAsteroidsX(), getAsteroidsY());
        }
        addObject(new LittlePrince(), 100, 100);
        addObject(new HomePlanet(), getWidth() / 2, getHeight() / 2);
        addObject(new RandomPlanet(), 0, Greenfoot.getRandomNumber(276) + 150);
        if (Greenfoot.getRandomNumber (2) == 0){
            addObject (new Fox(), 0 + 50, Greenfoot.getRandomNumber(276) + 200);
        }
    }
    
    public int getAsteroidsX(){
        int getAsteroidsX = Greenfoot.getRandomNumber(getWidth());
        return getAsteroidsX;
    }
    
    public int getAsteroidsY(){
        int getAsteroidsY = Greenfoot.getRandomNumber(getHeight());
        return getAsteroidsY;
    }
    
    public static double getDistance (Actor a, Actor b){
        double distanceBetween = Math.hypot (a.getX() - b.getX(), a.getY() - b.getY());
        return distanceBetween;
    }

    public void act(){
        clueBar.update(clueCount);
    }
}
