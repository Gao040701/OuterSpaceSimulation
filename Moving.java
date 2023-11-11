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
    private RandomPlanet targetPlanet;
    private boolean rotateDetection = false;
    private int angle = 0;
    private ArrayList<RandomPlanet> planets;
    //private RandomPlanet targetPlanet;
    private LittlePrince littlePrince;
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
        //if (targetPlanet != null){ //&& targetPlanet.getWorld() == null
        if(getOneIntersectingObject(RandomPlanet.class) != null){
            //targetPlanet = null;
            moveTowardPlanet();
            System.out.println("Touched!??");
        }
        else{
            moveRandomly();
        }
    }

    private void moveTowardPlanet(){
        //if (getDistance(littlePrince, targetPlanet) < 2){
        RandomPlanet targetPlanet = (RandomPlanet)getOneIntersectingObject(RandomPlanet.class);
        if(targetPlanet.getX() < 500 ){
            System.out.println("NOT NULL TARGET!!!");
            rotate();
            System.out.println("Touched the planet!");
        }
        else{
            move (mySpeed);
        }
    }
    
    public static double getDistance (Actor a, Actor b){
        double distanceBetween = Math.hypot (Math.abs(a.getX() - b.getX()), Math.abs(a.getY() - b.getY()));
        return distanceBetween;
     }

    private void targetClosestPlanet(){
        double closestTargetDistance = 0;
        double distanceToActor;
        planets = (ArrayList<RandomPlanet>)getObjectsInRange(40, RandomPlanet.class);
        System.out.println("SIZE: " + planets.size());
        if (planets.size() == 0){
            planets = (ArrayList<RandomPlanet>)getObjectsInRange(140, RandomPlanet.class);
        }
        if (planets.size() == 0){
            planets = (ArrayList<RandomPlanet>)getObjectsInRange(350, RandomPlanet.class);
        } 
        if (planets.size() == 0){
            planets = (ArrayList<RandomPlanet>)getWorld().getObjects(RandomPlanet.class);
        } 

        if (planets.size() > 0){
            targetPlanet = planets.get(0);
            closestTargetDistance = getDistance (littlePrince, targetPlanet);

            for (RandomPlanet o : planets){
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
            move (mySpeed);
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

    public void rotateDetection(){
        if (getOneIntersectingObject(RandomPlanet.class) != null){
            rotateDetection = true;
            RandomPlanet touchingPlanet= (RandomPlanet)getOneIntersectingObject(RandomPlanet.class);
        }
        if (rotateDetection == true){
            //rotate(touchingPlanet.getSpeed());
            rotate();
            move(targetPlanet.getSpeed());
        }
        //rotate(119/100);
    }

    public void rotate(){
        RandomPlanet touchingPlanet= (RandomPlanet)getOneIntersectingObject(RandomPlanet.class);
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
