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
    private boolean follow = false;
    public void act()
    {
        // if (getWorld().getObjects(LittlePrince.class) != null){
        // //turnTowards(littlePrince);
        // }
        littlePrince = (LittlePrince) getOneIntersectingObject(LittlePrince.class);
        randomPlanet = (RandomPlanet) getOneIntersectingObject(RandomPlanet.class);
        planet = (Planet) getOneIntersectingObject(Planet.class);
        box = (HitBox) getOneIntersectingObject(HitBox.class);

        if (checkHitPlanet() && follow == false){
            setLocation (planet.getPlanetX() + planet.getRadius(), planet.getPlanetY());
            animate (dig);
            turnTowards(planet);
            turn(-90);
        }
        if (!checkHitPlanet() && follow == false){
            passCount = 0;
            //rotateDetection = false;
            isStaying = false;
            rotateImage(90);
            moveRandomly();
            if (getRotation() < 270 && getRotation() > 90){
                animate(flyInverted);
            }else animate(fly);
        }
        if(checkCollisionLP()){
            follow = true;
            if (checkHitPlanet()){
                //System.out.println("FOUND LP!");
                rotate();
                animate(walk);
            }
            if(!checkHitPlanet()){
                if (getRotation() < 270 && getRotation() > 90){
                    setLocation(littlePrince.getPrinceX(), littlePrince.getPrinceY());
                    
                    turnTowards(littlePrince);
                    animate(flyInverted);
                }else {
                    setLocation(littlePrince.getPrinceX(), littlePrince.getPrinceY());
                    turnTowards(littlePrince);
                    animate(fly);
                }
            }
        }
        super.act();
    }

    public boolean checkHitPlanet () {
        return planet != null;
    }

    public boolean checkHitTree(){
        if (box != null && box.getBaobabTree().getPlanet().equals(randomPlanet)){
            return true;
        }
        return false;
    }

    public void rotate(){
        speed = planet.getSpeed();
        turnTowards (planet);
        turn(-90);
        if (!checkHitTree()){
            int radius = planet.getRadius();
            double radians = Math.toRadians(angle);
            double x = planet.getX() + (double) ((radius+30) * Math.cos(radians));
            double y = planet.getY() + (double) ((radius+30) * Math.sin(radians));
            angle -= 1.5;
            setLocation(littlePrince.getX(), littlePrince.getY());
            //canFly(planet);
            animate(walk);
        }else{
            setLocation(littlePrince.getX(), littlePrince.getY());
            animate(dig);
            box.getBaobabTree().removeBaobabTree();
        }
    }

    public void canFly(Planet planet){
        if (planet.getX() - 10 <= getX() && getX() <= planet.getX() + 10 && !justPassed ){
            passCount++; 
            justPassed = true;
        }
        if (planet.getX() - 10 > getX() || getX() > planet.getX() + 10){
            justPassed = false;
        }
        if(passCount >= 3){
            rotateDetection = false;
            setLocation(getX()-10, getY() - 10);
            setLocation(getX()-200, getY() - 10);
        }
    }

    public boolean getRotationDetection(){
        return rotateDetection;
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
