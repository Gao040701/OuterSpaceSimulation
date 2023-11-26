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
    public static final GreenfootSound explosion = new GreenfootSound("explosion.wav");
    private LittlePrince TLP;
    private boolean damaged = false;
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
        explosion.setVolume(70);
    }

    /**
     * Act - Perform the explosion animation.
     * This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        duration--;
        explode();
        causeDamage();
    }
    
    /**
     * Method called when the world is started. 
     * Starts playing the intro music.
     */
    public void started(){
        explosion.playLoop(); 
    }

    /**
     * Method called when the world is stopped. 
     * Pauses the intro music.
     */
    public void stopped(){
        explosion.pause();
    }

    /**
     * Displays the explosion animation.
     * The animation is updated based on a timer, and the explosion object is removed after completion.
     */
    private void explode() {
        // Check if it's time to update the animation
        started();
        if (animationTimer.millisElapsed() < 25) {
            return;
        } else if (animationTimer.millisElapsed() >= 25) {
            setImage(animation[count]);
            count++;

            // Check if the end of the animation is reached
            if (count == 20) {
                count = 0;
                stopped();
                getWorld().removeObject(this);
            }
            animationTimer.mark();  // Reset the timer for the next frame
        }
    }
    
    public void causeDamage(){
        if (isTouching(LittlePrince.class) && !damaged){
            TLP = (LittlePrince)getOneIntersectingObject(LittlePrince.class);
            TLP.changeHP(-5);
            damaged = true;
        }
    }
}
