import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Fox extends Moving
{
    /**
     * Act - do whatever the Fox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private LittlePrince follow;
    public void act()
    {
        // if (getWorld().getObjects(LittlePrince.class) != null){
            // //turnTowards(littlePrince);
        // }
        littlePrince = (LittlePrince) getOneIntersectingObject(LittlePrince.class);
        if (checkCollisionLP()){
            //System.out.println("FOUND LP!");
            setLocation(littlePrince.getPrinceX() + 10, littlePrince.getPrinceY());
        }
        else{
            //System.out.println("NOPE!");
        }
        super.act();
    }

    public Fox(GreenfootImage[] walk, GreenfootImage[] fly, GreenfootImage[] dig, GreenfootImage[] flyInverted){
        super(walk, fly, dig, flyInverted);
    }

    public boolean checkCollisionLP(){
        LittlePrince lp = (LittlePrince) getOneIntersectingObject(LittlePrince.class);
        if (lp != null){
            return true;
        }
        else{
            return false;
        }
    }
}
