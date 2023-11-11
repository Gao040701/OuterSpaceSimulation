import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Moving here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Moving extends Being
{
    private int speed;
    private Planet touchingPlanet;
    private boolean rotateDetection = false;
    private int angle = 0;
    private ArrayList<Planet> planets;
    private Planet targetPlanet;
    private int mySpeed = 5;
    /**
     * Act - do whatever the Character wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        rotateDetection();
    }

    public void rotateDetection(){
        if (targetPlanet != null && targetPlanet.getWorld() == null){
            targetPlanet = null;
        }
        
        if (targetPlanet != null){
            moveTowardPlanet();
        }
        else{
            moveRandomly();
        }
        
        
        if (getOneIntersectingObject(Planet.class) != null){
            rotateDetection = true;
            touchingPlanet = (Planet)getOneIntersectingObject(Planet.class);
        }
        if (rotateDetection == true){
            //rotate(touchingPlanet.getSpeed());
            rotate();
            move(touchingPlanet.getSpeed());
        }
        //rotate(119/100);
    }
    
    private void moveTowardPlanet(){
        if (Galaxy.getDistance(this, targetPlanet) < 18){
            rotate();
        }
        else{
            move (mySpeed);
        }
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
            closestTargetDistance = Galaxy.getDistance (this, targetPlanet);
            
            for (Planet o : planets){
                distanceToActor = Galaxy.getDistance(this, o);
                
                if (distanceToActor < closestTargetDistance){
                    targetPlanet = o;
                    closestTargetDistance = distanceToActor;
                }
            }
            turnTowards(targetPlanet.getX(), targetPlanet.getY());
        }
    }
    
    private void moveRandomly(){
        if (Greenfoot.getRandomNumber (100) == 50){
            turn (Greenfoot.getRandomNumber(360));
        }
        else{
            move (mySpeed);
        }
    }

    public void rotate(){
        int radius = touchingPlanet.getRadius();
        double radians = Math.toRadians(angle);
        int x = getX() + (int) (radius/100 * Math.cos(radians));
        int y = getY() + (int) (radius/100 * Math.sin(radians));
        setLocation(x + touchingPlanet.getSpeed()/2, y);
        angle += 100.0;
        turn(touchingPlanet.getRadius()/100);
        // move(planet);
        // setLocation(getX(), getY());
        // setLocation(getX() + touchingPlanet.getSpeed(), getY());
        //move(getPlanetSpeed(this));
    }
}
