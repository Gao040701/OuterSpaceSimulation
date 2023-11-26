import greenfoot.*;  // (World, Actor, GreenfootImage, GreenfootSound)

/**
 * The IntroAnimation class represents the introduction animation of the game.
 * It displays a sequence of images with background music.
 * 
 * @author Gao
 * @version 11/12/2023
 */
public class IntroAnimation extends World
{
    private GreenfootImage[] animation = new GreenfootImage[135];
    private int index = 0;
    private final int COUNT_NUM = 9;
    private int count = 0;
    private GreenfootSound introMusic;
    
    /**
     * Constructor for objects of class IntroAnimation.
     */
    public IntroAnimation()
    {    
        // Create a new world with 1024x576 cells and a cell size of 1x1 pixels.
        super(1024, 576, 1); 
        /**
         * Music credit: Hans Zimmer
         * Title: Ascending
         */
        introMusic = new GreenfootSound("introAnimationMusic.mp3");
        introMusic.setVolume(30);
        // Load animation images
        for (int i = 0; i < animation.length; i++){
            animation[i] = new GreenfootImage("introAnimation/ani"+i+".png");
            animation[i].scale(1024, 576);
        }
    }
    
    /**
     * Act method for the IntroAnimation. 
     * Handles animation and music playback.
     */
    public void act(){
        animate();
    }
    
    /**
     * Method called when the world is started. 
     * Starts playing the intro music.
     */
    public void started(){
        introMusic.playLoop(); 
    }

    /**
     * Method called when the world is stopped. 
     * Pauses the intro music.
     */
    public void stopped(){
        introMusic.pause();
    }
    
    /**
     * Animates the background images and transitions to the next world when animation is complete.
     */
    public void animate(){
        if (index < animation.length){
            if (count == COUNT_NUM){
                setBackground(animation[index]);
                index++;
                count = 0;
            } else {
                count++;
            }
        } else {
            Greenfoot.setWorld(new SetValuePage());
            stopped();
        }
    }
}
