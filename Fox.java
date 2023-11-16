import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fox extends Moving
{
    private GreenfootImage[] foxRun = new GreenfootImage[8];
    private GreenfootImage[] foxFly = new GreenfootImage[5];
    private GreenfootImage[] foxDig = new GreenfootImage[11];
    /**
     * Act - do whatever the Fox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        turnTowards(littlePrince);
        super.act();
    }
    
    public Fox(GreenfootImage[] walk, GreenfootImage[] fly, GreenfootImage[] dig){
        super(walk, fly, dig);
        
    }
}
