import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Fox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Fox extends Moving
{
    /**
     * Act - do whatever the Fox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int index, count = 0, degree;
    private double speed;
    private final int COUNT_NUM = 7;
    private Planet targetPlanet;
    private RandomPlanet randomPlanet;
    private Planet planet;
    private HitBox box;
    protected boolean rotateDetection = false;
    private double angle = 0;
    private ArrayList<Planet> planets;
    protected LittlePrince littlePrince;
    private int mySpeed = 1;
    private int passCount = 0;
    private boolean justPassed = false;
    private boolean isStaying = false;

    private GreenfootImage[] walk;
    private GreenfootImage[] fly;
    private GreenfootImage[] dig;
    private GreenfootImage[] flyInverted;
    private LittlePrince follow;
    public void act()
    {
        // if (getWorld().getObjects(LittlePrince.class) != null){
        // //turnTowards(littlePrince);
        // }
        littlePrince = (LittlePrince) getOneIntersectingObject(LittlePrince.class);
        randomPlanet = (RandomPlanet) getOneIntersectingObject(RandomPlanet.class);
        planet = (Planet) getOneIntersectingObject(Planet.class);
        box = (HitBox) getOneIntersectingObject(HitBox.class);
        
        if (checkHitPlanet()){
            setLocation (planet.getPlanetX() + planet.getRadius(), planet.getPlanetY());
            animate (dig);
        }
        else if (!checkCollisionLP()){
            passCount = 0;
            rotateDetection = false;
            isStaying = false;
            rotateImage(90);
            moveRandomly();
        }
        else if (checkCollisionLP()){
            //System.out.println("FOUND LP!");
            setLocation(littlePrince.getPrinceX() + 10, littlePrince.getPrinceY());
            if (getRotation() < 270 && getRotation() > 90){
                animate(flyInverted);
            }else animate(fly);
        }
        super.act();
    }

    public boolean checkHitPlanet () {
        return planet != null;
    }

    public Fox(GreenfootImage[] walk, GreenfootImage[] fly, GreenfootImage[] dig, GreenfootImage[] flyInverted){
        super(walk, fly, dig, flyInverted);
        this.walk = walk; 
        this.fly = fly;
        this.dig = dig;
        this.flyInverted = flyInverted;
    }

    public boolean checkCollisionLP(){
        LittlePrince lp = (LittlePrince) getOneIntersectingObject(LittlePrince.class);
        if (lp != null){
            return true;
        }
        else{
            return false;
        }
    }
}
