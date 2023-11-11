import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The little prince will walk around the planet and jump out of the planets
 * when all the baobab trees are destroyed.
 * @Zhiyu (Jennifer) Zhou & Angela Gao 
 * @Jan 7, 2022 - v1.0
 */
public class LittlePrince extends Moving
{
    private GreenfootImage[] walk = new GreenfootImage[8];
    private GreenfootImage[] fly = new GreenfootImage[6];
    private GreenfootImage[] dig = new GreenfootImage[9];
    private int index, count;
    private final int countNum = 7;
    /**
     * Act - do whatever the LittlePrince wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        if (Greenfoot.isKeyDown("space")){
            animate(fly);
        }else if (Greenfoot.isKeyDown("enter")){
            animate(dig);
        }else animate(walk);
    }
    
    public LittlePrince(){
        prepareAnimation(walk, "walkAnimation/walk");
        prepareAnimation(fly, "flyAnimation/fly");
        prepareAnimation(dig, "digAnimation/dig");
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
}
