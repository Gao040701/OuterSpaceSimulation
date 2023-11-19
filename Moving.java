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
    private int speed, index, count = 0, degree;
    private final int COUNT_NUM = 7;
    private Planet targetPlanet;
    private RandomPlanet randomPlanet;
    private Planet planet;
    private HitBox box;
    private boolean rotateDetection = false;
    private double angle = 0;
    private ArrayList<Planet> planets;
    //private Planet targetPlanet;
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
        /*
        if (checkHitPlanet() && passCount != 3 && !checkHitTree()){
            rotateDetection = true;
            rotate();
            animate(walk);
        }
        if (checkHitPlanet() && checkHitTree()){
            animate(dig);
            stay();
        }
        if (!checkHitPlanet() && !checkHitTree()){
            passCount = 0;
            rotateDetection = false;
            rotateImage(90); //may need to adjust later
            moveRandomly();
            if (getRotation() < 270 && getRotation() > 90){
                animate(flyInverted);
            }else animate(fly);
        }
        */
        randomPlanet = (RandomPlanet) getOneIntersectingObject(RandomPlanet.class);
        planet = (Planet) getOneIntersectingObject(Planet.class); //return true if intersects
        box = (HitBox) getOneIntersectingObject(HitBox.class);

        if (checkHitPlanet()){
            rotateDetection = true;
            rotate();
        }else {
            passCount = 0;
            rotateDetection = false;
            isStaying = false;
            rotateImage(90);
            moveRandomly();
            if (getRotation() < 270 && getRotation() > 90){
                animate(flyInverted);
            }else animate(fly);
        }
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
    /*
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
    */
    /*
    private void moveRandomly(){
        animate(fly);
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
    */
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
            move(mySpeed * 2);
        }else if(Greenfoot.getRandomNumber(100) == 0){
            setRotation(degree);
            preNumAtEdge = numAtEdge;
        }
        move(mySpeed);
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
    
    public void stay(){
        double speed = planet.getSpeed();
        move(speed);
        isStaying = true;
    }
    
    public void rotate(){
        double speed = planet.getSpeed();
        turnTowards (planet);
        turn(-90);
        if (!checkHitTree()){
            int radius = planet.getRadius();
            double radians = Math.toRadians(angle);
            double x = planet.getX() + (double) ((radius+30) * Math.cos(radians));
            double y = planet.getY() + (double) ((radius+30) * Math.sin(radians));
            angle -= 1.5;
            setLocation(x+speed, y);
            canFly(planet);
            animate(walk);
        }else{
            setLocation(planet.getX() + speed, getY());
            animate(dig);
            box.getBaobabTree().removeBaobabTree();
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
    
    public void setIsStaying(boolean x){
        isStaying = x;
    }
}
