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
    private int speed;
    private Planet targetPlanet;
    private boolean rotateDetection = false;
    private int angle = 0;
    private ArrayList<Planet> planets;
    //private Planet targetPlanet;
    protected LittlePrince littlePrince;
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
        //if(getOneIntersectingObject(Planet.class) != null){
        if (getOneIntersectingObject(Planet.class) != null){
            targetPlanet = null;
            rotateDetection = true;
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

    public void rotateDetection(){
        if (rotateDetection == true){
            //rotate(touchingPlanet.getSpeed());
            rotate();
            move(targetPlanet.getSpeed());
        }
        //rotate(119/100);
    }

    public void rotate(){
        Planet touchingPlanet= (Planet)getOneIntersectingObject(Planet.class);
        int radius = touchingPlanet.getRadius();
        double radians = Math.toRadians(angle);
        int x = getX() + (int) (-radius/100 * Math.cos(radians));
        int y = getY() + (int) (-radius/100 * Math.sin(radians));
        setLocation(x - touchingPlanet.getSpeed(), y);
        angle -= 120.0;
        turn(-radius/100);
        //move(touchingPlanet.getSpeed());
        // move(touchingPlanet.getSpeed());
        // setLocation(getX(), getY());
        // setLocation(getX() + touchingPlanet.getSpeed(), getY());
        // move(touchingPlanet.getSpeed());
    }

    // protected Point pointOnCircle() {
        // Planet touchingPlanet= (Planet)getOneIntersectingObject(Planet.class);
        // int radius = touchingPlanet.getRadius();
        // double rads = Math.toRadians(angle - 180); // Make 0 point out to the right...
        // int fullLength = Math.round(radius);

        // // Calculate the outter point of the line
        // int xPosy = Math.round((float) (Math.cos(rads) * fullLength));
        // int yPosy = Math.round((float) (Math.sin(rads) * fullLength));

        // return new Point(xPosy, yPosy);
    // }
}
