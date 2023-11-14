import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fox extends Moving
{
    private GreenfootImage[] run = new GreenfootImage[8];
    private GreenfootImage[] fly = new GreenfootImage[5];
    private GreenfootImage[] dig = new GreenfootImage[11];
    /**
     * Act - do whatever the Fox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        //turnTowards(littlePrince);
        super.act();
        if (Greenfoot.isKeyDown("space")){
            animate(fly);
        }else if (Greenfoot.isKeyDown("enter")){
            animate(dig);
        }else animate(run);
    }
    
    
    public Fox(){
        prepareAnimation(run, "foxRun/run");
        prepareAnimation(fly, "foxFly/fly");
        prepareAnimation(dig, "foxDig/dig");
    }
}
