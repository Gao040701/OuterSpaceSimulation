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
    private int mySpeed = 1;
    /**
     * Act - do whatever the Character wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        /**
         * Add if (energy > 0)
         */
        if (targetPlanet != null){ //&& targetPlanet.getWorld() == null
            targetPlanet = null;
            moveTowardPlanet();
            System.out.println("Touched!??");
        }
        else{
            moveRandomly();
        }
    }

    private void moveTowardPlanet(){
        if (Galaxy.getDistance(this, targetPlanet) < 10){
            rotate();
            System.out.println("Touched the planet!");
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
        if((getX() > 30 || getX() < 970) && (getY() > 30 || getY() < 540)){
            if (Greenfoot.getRandomNumber (100) == 50){
                turn (Greenfoot.getRandomNumber(360));
            }
            else{
                move (mySpeed);
            }
        }
        if (getX() < 30 ){
            setLocation(getX() + 40, getY());
            turn (Greenfoot.getRandomNumber(1));
            move (mySpeed);
            System.out.println("move right");
        }
        if (getX() > 970 ){
            setLocation(getX() - 10, getY());
            turn (Greenfoot.getRandomNumber(180));
            move (mySpeed);
            System.out.println("move left");
        }
        if (getY() < 30 ){
            setLocation(getX(), getY() + 10);
            turn (Greenfoot.getRandomNumber(270));
            move (mySpeed);
            System.out.println("move down");
        }
        if (getY() >540 ){
            setLocation(getX(), getY() - 10);
            turn (Greenfoot.getRandomNumber(90));
            move (mySpeed);
            System.out.println("move up");
        }
    }
    
    public void rotateDetection(){
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
