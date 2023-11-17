import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Galaxy extends World
{
    private static int numOfAsteroids;
    private static int amountOfClues;
    private static int asteroidSpeed;
    private static int hpPerPlanet;
    private int numOfPlanets = 1; 
    private SuperStatBar clueBar;
    private int clueCount = 0;
    private GreenfootImage background = new GreenfootImage("galaxyBackground.png");
    //public static final int Rhp=hpPerPlanet;
    //final variables should be in all capital letters 
    public static final int Rdecrease=15;
    
    //animations
    private GreenfootImage[] TLPwalk = new GreenfootImage[8];
    private GreenfootImage[] TLPfly = new GreenfootImage[6];
    private GreenfootImage[] TLPdig = new GreenfootImage[9];
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Galaxy(int numOfAsteroids, int amountOfClues, int asteroidSpeed, int hpPerPlanet)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 576, 1, false);
        this.numOfAsteroids = numOfAsteroids;
        this.amountOfClues=amountOfClues;
        this.asteroidSpeed=asteroidSpeed;
        this.hpPerPlanet=hpPerPlanet;
        background.scale(1024, 576);
        setBackground(background);
        setPaintOrder (SuperStatBar.class, Asteroids.class, Moving.class, BaobabTree.class, Planet.class); 
        clueBar = new SuperStatBar(110, clueCount, null, 110, 10, 0, Color.RED, Color.BLACK, false, Color.BLACK, 1);
        addObject(clueBar, getWidth() - 60, 80);
        prepare();
        System.out.println(hpPerPlanet);
    }
    
    /**
     * Credit: Method created by Jiayi Li, modified by Zhiyu (Jennifer) Zhou
     */
    private void prepare() { 
        for (int i = 0; i < this.numOfAsteroids; i++) {
            addObject(new Asteroids(), getAsteroidsX(), getAsteroidsY());
        }
        LittlePrince TLP = new LittlePrince(TLPwalk, TLPfly, TLPdig);
        addObject(TLP, 1000, 250);
        TLP.prepareAnimation(TLPwalk, "walkAnimation/walk");
        TLP.prepareAnimation(TLPfly, "flyAnimation/fly");
        TLP.prepareAnimation(TLPdig, "digAnimation/dig");
        addObject(new HomePlanet(), getWidth() / 2, getHeight() / 2);
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
    
    public static double getDistance (Actor a, Actor b){
        double distanceBetween = Math.hypot (a.getX() - b.getX(), a.getY() - b.getY());
        return distanceBetween;
    }

    public void act(){
        clueBar.update(clueCount);
    }
    
    public static int getNumOfAsteroids(){
        return numOfAsteroids;
    }
    
    public static int getAmountOfClues(){
        return amountOfClues;
    }
    
    public static int asteroidSpeed(){
        return asteroidSpeed;
    }
    
    public static int hpPerPlanet(){
        return hpPerPlanet;
    }
}
