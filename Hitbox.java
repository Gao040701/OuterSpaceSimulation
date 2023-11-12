import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hitbox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hitbox extends Actor
{
    private Actor target;
    private int offset;
    private GreenfootImage hitbox;
    //private int height, width;
    public Hitbox (int width, int height){  
        hitbox = new GreenfootImage (width, height);
        hitbox.setColor(Color.RED);
        hitbox.fillRect(0, 0, width-1, height - 1);
        setImage(hitbox);
    }

    public boolean checkCollision() {
        //check if the hit box intersects any objects
        return getIntersectingObjects(LittlePrince.class).size() > 0; //return true if intersects
    }

    /**
     * credit: borrowed from Mr. Cohen's SuperStatBar; modified by Zhiyu (Jennifer) Zhou
     */
    public void moveHitbox (){
        if (target != null && getWorld() != null){
            if (target.getWorld() != null)
            {
                setLocation (target.getX(), target.getY() + offset);
            }
            else
            {
                getWorld().removeObject(this);
                return;
            }
        }    
    }

    public void addedToWorld (World w){
        moveHitbox();
    }

    public void act()
    {
        moveHitbox();
    }
}
