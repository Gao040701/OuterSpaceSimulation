import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Galaxy extends World
{
    public static int numOfAsteroids;
    private int numOfPlanets = 1; 
    private SuperStatBar clueBar;
    private int clueCount = 0;
    private GreenfootImage background = new GreenfootImage("galaxyBackground.png");
    public static final int Rhp=100;
    public static final int Rdecrease=10;
    
    //animations
    private GreenfootImage[] TLPwalk = new GreenfootImage[8];
    private GreenfootImage[] TLPfly = new GreenfootImage[6];
    private GreenfootImage[] TLPdig = new GreenfootImage[9];
    private GreenfootImage[] foxRun = new GreenfootImage[8];
    private GreenfootImage[] foxFly = new GreenfootImage[5];
    private GreenfootImage[] foxDig = new GreenfootImage[11];
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Galaxy(int numOfAsteroids, int amountOfClues, int asteroidSpeed, int hpPerPlanet)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 576, 1, false);
        this.numOfAsteroids = numOfAsteroids;
        background.scale(1024, 576);
        setBackground(background);
        setPaintOrder (SuperStatBar.class, Asteroids.class, Moving.class, BaobabTree.class, Planet.class); 
        clueBar = new SuperStatBar(110, clueCount, null, 110, 10, 0, Color.RED, Color.BLACK, false, Color.BLACK, 1);
        addObject(clueBar, getWidth() - 60, 80);
        prepare();
    }
    
    /**
     * Credit: Method created by Jiayi Li, modified by Zhiyu (Jennifer) Zhou
     */
    private void prepare() { 
        for (int i = 0; i < this.numOfAsteroids; i++) {
            addObject(new Asteroids(), getAsteroidsX(), getAsteroidsY());
        }
        LittlePrince TLP = new LittlePrince(TLPwalk, TLPfly, TLPdig);
        addObject(TLP, 100, 100);
        TLP.prepareAnimation(TLPwalk, "walkAnimation/walk");
        TLP.prepareAnimation(TLPfly, "flyAnimation/fly");
        TLP.prepareAnimation(TLPdig, "digAnimation/dig");
        addObject(new HomePlanet(), getWidth() / 2, getHeight() / 2);
        addObject(new RandomPlanet(), 0, Greenfoot.getRandomNumber(276) + 150);
        if (Greenfoot.getRandomNumber (2) == 0){
            Fox fox = new Fox(foxRun, foxFly, foxDig);
            addObject (fox, 0 + 50, Greenfoot.getRandomNumber(276) + 200);
            fox.prepareAnimation(foxRun, "foxRun/run", fox.getImage().getWidth()*2, fox.getImage().getHeight()*2);
            fox.prepareAnimation(foxFly, "foxFly/fly", fox.getImage().getWidth()*2, fox.getImage().getHeight()*2);
            fox.prepareAnimation(foxDig, "foxDig/dig", fox.getImage().getWidth()*2, fox.getImage().getHeight()*2);
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
