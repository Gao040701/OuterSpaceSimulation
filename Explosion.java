import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Explosion class represents an explosion animation.
 * The explosion is created with a specified duration and consists of a sequence of PNG images.
 * The animation is displayed for the specified duration, and then the explosion object is removed
 * from the world.
 * 
 * @author Jiayi Li
 * @version November 2023
 */
public class Explosion extends Actor {
    private int duration, count;
    private SimpleTimer animationTimer;
    private GreenfootImage[] animation;

    /**
     * Constructs an explosion with the specified duration.
     * 
     * @param duration The duration (in act cycles) for which the explosion animation is displayed.
     */
    public Explosion(int duration) {
        this.duration = duration;
        // The variables below are used to create a sequence of PNGs and a timer to form an animation
        animationTimer = new SimpleTimer();
        animationTimer.mark();
        animation = new GreenfootImage[32];
        for (int i = 0; i < 32; i++) {
            animation[i] = new GreenfootImage("explosion/1_" + (i) + ".png");
            animation[i].scale(getImage().getWidth()*25, getImage().getHeight()*25);
        }
    }

    /**
     * Act - Perform the explosion animation.
     * This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        duration--;
        explode();
    }

    /**
     * Displays the explosion animation.
     * The animation is updated based on a timer, and the explosion object is removed after completion.
     */
    public void explode() {
        // Check if it's time to update the animation
        if (animationTimer.millisElapsed() < 25) {
            return;
        } else if (animationTimer.millisElapsed() >= 25) {
            setImage(animation[count]);
            count++;

            // Check if the end of the animation is reached
            if (count == 20) {
                count = 0;
                getWorld().removeObject(this);
            }
            
            animationTimer.mark();  // Reset the timer for the next frame
        }
    }
}
