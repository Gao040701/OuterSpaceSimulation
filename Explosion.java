import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Explosion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Explosion extends Actor
{
    private int duration, count;
    private SimpleTimer animationTimer;
    private GreenfootImage[] animation;
    /**
     * Act - do whatever the Explosion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Explosion(int duration)
    {
        this.duration = duration;
        //The variables below is to create a sequence of PNGs and timer to form animation 
        animationTimer = new SimpleTimer();
        animationTimer.mark();
        animation = new GreenfootImage[32];
        for(int i = 0; i < 32; i++)
        {
            animation[i] = new GreenfootImage("explosion/1_" + (i) + ".png");
        }
    
    }
    public void act()
    {
        // Add your action code here.
        duration--;
        explode();
    }
    public void explode()
    {
        
        if(animationTimer.millisElapsed() < 25)
        {
            return;
        }
        else if(animationTimer.millisElapsed() >= 25)
        {
            setImage(animation[count]);
            count++;
            if(count == 20)
            {
                count = 0;
                getWorld().removeObject(this);
            }
            animationTimer.mark();
        }
    }
}
