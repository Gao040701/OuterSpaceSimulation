import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.awt.Point;

/**
 * Write a description of class Moving here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Moving extends Being
{
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

    public void act()
    {
        /**
         * Add if (energy > 0)
         */
    }

    public Moving(GreenfootImage[] walk, GreenfootImage[] fly, GreenfootImage[] dig, GreenfootImage[] flyInverted){
        this.walk = walk; 
        this.fly = fly;
        this.dig = dig;
        this.flyInverted = flyInverted;
    }

    public static double getDistance (Actor a, Actor b){
        double distanceBetween = Math.hypot (Math.abs(a.getX() - b.getX()), Math.abs(a.getY() - b.getY()));
        return distanceBetween;
    }

    public void targetClosestPlanet(){
        double closestTargetDistance = 0;
        double distanceToActor;
        planets = (ArrayList<Planet>)getObjectsInRange(40, Planet.class);

        if (planets.size() == 0){
            planets = (ArrayList<Planet>)getObjectsInRange(140, Planet.class);
        }

        if (planets.size() > 0){
            targetPlanet = planets.get(0);
            closestTargetDistance = getDistance (littlePrince, targetPlanet);

            for (Planet o : planets){
                distanceToActor = getDistance(littlePrince, o);
                if (distanceToActor < closestTargetDistance){
                    targetPlanet = o;
                    closestTargetDistance = distanceToActor;
                }
            }
            turnTowards(targetPlanet.getX(), targetPlanet.getY());
        }
    }

    public void prepareAnimation(GreenfootImage[] imgs, String frameName){
        for (int i = 0; i < imgs.length; i++){
            imgs[i] = new GreenfootImage(frameName+i+".png");
        }
    }

    public void prepareAnimation(GreenfootImage[] imgs, String frameName, int width, int height){
        for (int i = 0; i < imgs.length; i++){
            imgs[i] = new GreenfootImage(frameName+i+".png");
            imgs[i].scale(width, height);
        }
    }

    public void flipHorizontally(GreenfootImage[] imgs){
        for (int i = 0; i < imgs.length; i++){
            imgs[i].mirrorHorizontally();
        }
    }

    public void flipVertically(GreenfootImage[] imgs){
        for (int i = 0; i < imgs.length; i++){
            imgs[i].mirrorVertically();
        }
    }

    public void animate(GreenfootImage[] imgs){
        if (index < imgs.length){
            if (count == COUNT_NUM){
                setImage(imgs[index]);
                index++;
                count = 0;
            }else{
                count++;
            }
        }else{
            index = 0;
        }
    }

    private boolean closeLeft, closeRight, closeTop, closeBottom;
    private void checkPosition(){
        numAtEdge = 0;
        closeLeft = getX() < 400;
        closeTop = getY() < 60;
        closeRight = getX() > (getWorld().getBackground().getWidth() - 100);
        closeBottom = getY() > (getWorld().getBackground().getHeight() - 60);
        if (closeLeft) numAtEdge++;
        if (closeRight) numAtEdge++;
        if (closeTop) numAtEdge++;
        if (closeBottom) numAtEdge++;
    }

    private int numAtEdge, preNumAtEdge;
    public void moveRandomly(){
        checkPosition();
        if (closeLeft && closeTop){
            degree = Greenfoot.getRandomNumber(90);
        }else if (closeLeft && closeBottom){
            degree = Greenfoot.getRandomNumber(90) - 90;
        }else if (closeRight && closeTop){
            degree = Greenfoot.getRandomNumber(90) + 90;
        }else if (closeRight && closeBottom){
            degree = Greenfoot.getRandomNumber(90) + 180;
        }else if (closeLeft){
            degree = Greenfoot.getRandomNumber(180) - 90;
        }else if (closeRight){
            degree = Greenfoot.getRandomNumber(180) + 90;
        }else if (closeTop){
            degree = Greenfoot.getRandomNumber(180);
        }else if (closeBottom){
            degree = Greenfoot.getRandomNumber(180) + 180;
        }else{
            degree = Greenfoot.getRandomNumber(180) + 90;
        }
        if (((closeLeft || closeRight || closeTop || closeBottom) &&  numAtEdge != preNumAtEdge)){
            setRotation(degree);
            preNumAtEdge = numAtEdge;
            move(mySpeed * 5);
        }else if(Greenfoot.getRandomNumber(100) == 0){
            setRotation(degree);
            preNumAtEdge = numAtEdge;
        }
        move(mySpeed);
    }

    public void setIsStaying(boolean x){
        isStaying = x;
    }
}
