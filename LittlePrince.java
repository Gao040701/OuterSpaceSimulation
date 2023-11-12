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
    public static SuperStatBar PrinceHpBar;
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
        PrinceHpBar = new SuperStatBar(100, 100, this, 100, 10, -50, Color.GREEN, Color.RED, false, Color.BLACK, 1);
    }
    
    public int getPrinceX(){
        return getX();
    }
    
    public int getPrinceY(){
        return getY();
    }
}
