import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.awt.Point;

/**
 * The Moving class consists of all the main characters in the game that are moving relative to the planets, including Bird, LittlePrince, and Fox subclasses. 
 * <p>
 * @author Zhiyu (Jennifer) Zhou
 * @modified by Angela Gao
 * @version 1.0
 */
public class Moving extends Being
{
    private int index, count = 0, degree;
    private double speed;
    private final int COUNT_NUM = 7;
    private RandomPlanet randomPlanet;
    private Planet planet;
    private Fox fox;
    private HitBox box;
    protected boolean rotateDetection = false;
    private double angle = 0;
    
    
    protected LittlePrince littlePrince;
    private int mySpeed = 1;
    private int passCount = 0;
    private boolean justPassed = false;
    private boolean isStaying = false;
    
    private GreenfootImage[] walk;
    private GreenfootImage[] fly;
    private GreenfootImage[] dig;
    private GreenfootImage[] flyInverted;
    /**
     * Act method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     * Includes placeholder content. Override this method to define the actor's behavior.
     */
    public void act()
    {
        /**
         * Add if (energy > 0)
         */
    }
    
    /**
     * Constructor for the Moving class.
     * 
     * @param walk Array of walking animation frames.
     * @param fly Array of flying animation frames.
     * @param dig Array of digging animation frames.
     * @param flyInverted Array of inverted flying animation frames.
     */    
    public Moving(GreenfootImage[] walk, GreenfootImage[] fly, GreenfootImage[] dig, GreenfootImage[] flyInverted){
        this.walk = walk; 
        this.fly = fly;
        this.dig = dig;
        this.flyInverted = flyInverted;
    }
    
    public Moving(){}
    
    /**
     * Gets the distance between two actors.
     * 
     * @param a The first actor.
     * @param b The second actor.
     * @return The distance between the two actors.
     */
    
    public static double getDistance (Actor a, Actor b){
        double distanceBetween = Math.hypot (Math.abs(a.getX() - b.getX()), Math.abs(a.getY() - b.getY()));
        return distanceBetween;
    }
    
    /**
     * Prepares animation frames from image files.
     * 
     * @param imgs Array to store the animation frames.
     * @param frameName The base name of the image files.
     */
    public void prepareAnimation(GreenfootImage[] imgs, String frameName){
        for (int i = 0; i < imgs.length; i++){
            imgs[i] = new GreenfootImage(frameName+i+".png");
        }
    }
    
    /**
     * Prepares scaled animation frames from image files.
     * 
     * @param imgs Array to store the animation frames.
     * @param frameName The base name of the image files.
     * @param width The width to scale the images to.
     * @param height The height to scale the images to.
     */
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
