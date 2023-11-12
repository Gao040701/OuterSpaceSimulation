import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hitbox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hitbox extends Planet
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

    public void checkCollision() {
        //check if the hit box intersects any objects
        //getOneIntersectingObject(LittlePrince.class);
    }

    public void act()
    {
        if (getX() > getWorld().getWidth()){
            
            getWorld().removeObject(this); 
        }
    }
}
