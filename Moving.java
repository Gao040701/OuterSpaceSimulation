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
    private SimpleTimer timer = new SimpleTimer();
    /**
     * Act - do whatever the Character wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        /**
         * Add if (energy > 0)
         */
        if (checkHitPlanet()){
            targetPlanet = null;
            rotateDetection = true;
        }
        else{
            rotateDetection = false;
        }

        if (rotateDetection == true){
            rotate();
        }
        else{
            angle = 0;
            rotateImage(90); //may need to adjust later
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

    public boolean checkHitPlanet () {
        RandomPlanet randomPlanet = (RandomPlanet) getOneIntersectingObject(RandomPlanet.class); //return true if intersects
        RosePlanet rosePlanet = (RosePlanet) getOneIntersectingObject(RosePlanet.class); //return true if intersects
        HomePlanet homePlanet = (HomePlanet) getOneIntersectingObject(HomePlanet.class); //return true if intersects
        if ((randomPlanet!= null)||(rosePlanet != null)||(homePlanet != null)){
            return true;
        }
        return false;
    }

    public void rotate(){
        Planet touchingPlanet= (Planet)getOneIntersectingObject(Planet.class);
        int radius = touchingPlanet.getRadius();
        double speed = touchingPlanet.getSpeed();
        double radians = Math.toRadians(angle);
        double x = touchingPlanet.getX() + (double) ((radius+30) * Math.cos(radians));
        double y = touchingPlanet.getY() + (double) ((radius+30) * Math.sin(radians));
        angle -= 1.5;
        System.out.println("Angle: " + (-360-angle));
        turnTowards (touchingPlanet);
        turn(-90);
        setLocation(x+speed, y);
        if ((-360-angle) == -360){
            rotateDetection = false;
            
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
