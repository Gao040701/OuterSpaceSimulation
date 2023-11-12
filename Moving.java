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
    private int speed, index, count = 0;
    private final int countNum = 7;
    private Planet targetPlanet;
    private boolean rotateDetection = false;
    private double angle = 0;
    private ArrayList<Planet> planets;
    //private Planet targetPlanet;
    protected LittlePrince littlePrince;
    private int mySpeed = 1;
    private int hit;
    private int point;
    /**
     * Act - do whatever the Character wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        /**
         * Add if (energy > 0)
         */
        //if (targetPlanet != null){ //&& targetPlanet.getWorld() == null
        //if(getOneIntersectingObject(Planet.class) != null){
        if (getOneIntersectingObject(Planet.class) != null){
            World world = getWorld();
            Planet touchingPlanet= (Planet)getOneIntersectingObject(Planet.class);
            targetPlanet = null;
            rotateDetection = true;
            double y = touchingPlanet.getY();
            // if(y == touchingPlanet.getRadius()-30){
                // point++;
                // System.out.println("Point #: " + point);
            // }
            // if(point == 2){
                // System.out.println("2!!!");
            // }
            if (getOneIntersectingObject(Hitbox.class) != null){
                hit++;
                System.out.println("Hit #: " + hit);
            }
        }
        else{
            hit = 0;
            rotateDetection = false;
        }

        if (rotateDetection == true){
            rotate();
            //pointOnCircle();
        }
        else{
            moveRandomly();
        }
    }

    public static double getDistance (Actor a, Actor b){
        double distanceBetween = Math.hypot (Math.abs(a.getX() - b.getX()), Math.abs(a.getY() - b.getY()));
        return distanceBetween;
    }

    private void targetClosestPlanet(){
        double closestTargetDistance = 0;
        double distanceToActor;
        planets = (ArrayList<Planet>)getObjectsInRange(40, Planet.class);
        System.out.println("SIZE: " + planets.size());

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

    private void moveRandomly(){
        if((getX() > 30 || getX() < 970) && (getY() > 30 || getY() < 540)){
            if (Greenfoot.getRandomNumber (100) == 50){
                turn (Greenfoot.getRandomNumber(360));
            }
            else{
                move (mySpeed);
            }
        }
        if (getX() < 30 ){
            setLocation(getX() + 50, getY());
            turn (Greenfoot.getRandomNumber(1));
            move (-mySpeed);
        }
        if (getX() > 970 ){
            setLocation(getX() - 10, getY());
            turn (Greenfoot.getRandomNumber(180));
            move (mySpeed);
        }
        if (getY() < 30 ){
            setLocation(getX(), getY() + 10);
            turn (Greenfoot.getRandomNumber(270));
            move (mySpeed);
        }
        if (getY() >540 ){
            setLocation(getX(), getY() - 10);
            turn (Greenfoot.getRandomNumber(90));
            move (mySpeed);
        }
    }

    public void rotate(){
        Planet touchingPlanet= (Planet)getOneIntersectingObject(Planet.class);
        int radius = touchingPlanet.getRadius();
        double speed = touchingPlanet.getSpeed();
        double radians = Math.toRadians(angle);
        double x = touchingPlanet.getX() + (double) ((radius+30) * Math.cos(radians));
        double y = touchingPlanet.getY() + (double) ((radius+30) * Math.sin(radians));
        //setLocation(x, y);
        //setLocation(x - speed/100, y);
        angle -= 1.5;
        
        turnTowards (touchingPlanet);
        //setRotation(180);
        System.out.println("Moving Planet x: " + x + " Moving Planet y: " + y);
        setLocation(x+speed, y);
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

    public void animate(GreenfootImage[] imgs){
        if (index < imgs.length){
            if (count == countNum){
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
}
