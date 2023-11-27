import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The EndScreen class represents the end screen of the game, displaying either a success or failure message.
 * It includes an animation for successful completion and a restart button.
 * 
 * @author Angela Gao
 * @version November 2023
 */
public class EndScreen extends World
{
    private GreenfootImage[] imgs = new GreenfootImage[17];
    private GreenfootImage fail = new GreenfootImage("EndScreenFailed.png");
    private GreenfootImage congradulation = new GreenfootImage("successText.png");
    private GreenfootImage waiting = new GreenfootImage("failText.png");
    private GreenfootImage restart = new GreenfootImage("restartButton.png");
    private int index = 0;
    private final int COUNT_NUM = 9;
    private int count = 0;
    private boolean success;
    private Image text;
    private Image restartButton;
    /**
     * Music credit
     * Artists: Hans Zimmer, Richard Harvey
     * Title: Farewell
     * Link: https://youtu.be/wMprqGYGP2c?si=PBd2JGwk8DZBxAD6
     */
    private GreenfootSound failedMusic;
    /**
     * Music credit
     * Artists: Hans Zimmer, Richard Harvey
     * Title: Recovery
     * Link: https://youtu.be/KjUYaIxuz_Y?si=ZT8GiagzczfGzXp8
     */
    private GreenfootSound successMusic;

    /**
     * Constructor for objects of class EndScreen.
     * 
     * @param success Indicates whether the game was successful or not.
     */
    public EndScreen(boolean success)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 576, 1); 
        // Load animation images
        for (int i = 0; i < 17; i++){
            imgs[i] = new GreenfootImage("introAnimation/ani"+i+".png");
            imgs[i].scale(1024, 576);
        }
        // Scale the failure image
        fail.scale(1024, 576);
        this.success = success;
        // Display success or failure message
        if (success){
            text = new Image(congradulation);
            addObject(text, 400, 200);
        }else{
            setBackground(fail);
            text = new Image(waiting);
            addObject(text, 400, 150);
        }
        // Add a restart button
        restartButton = new Image(restart, restart.getWidth() * 2, restart.getHeight() * 2);
        addObject(restartButton, 300, 400);
        failedMusic = new GreenfootSound("failedMusic.mp3");
        successMusic = new GreenfootSound("successMusic.mp3");
        failedMusic.setVolume(80);
        successMusic.setVolume(80);
    }
    
    /**
     * Act method for the EndScreen. 
     * Handles animation and restart button events.
     */
    public void act(){
        Galaxy.galaxyMusic.stop();
        Explosion.explosion.stop();
        if (success){
            startedSuccess();
            animate();
        }else{
            startedFailed();
        }
        senseButton();
    }
    
    public void startedFailed(){
        failedMusic.playLoop(); 
    }

    public void stoppedFailed(){
        failedMusic.pause();
    }
    
    public void startedSuccess(){
        successMusic.playLoop(); 
    }

    public void stoppedSuccess(){
        successMusic.pause();
    }
    
    /**
     * Animates the background images for successful completion.
     */
    public void animate(){
        if (index < imgs.length){
            if (count == COUNT_NUM){
                setBackground(imgs[index]);
                index++;
                count = 0;
            } else {
                count++;
            }
        } else {
            index = 0;
        }
    }
    
    /**
     * Checks if the restart button is clicked and restarts the game accordingly.
     */
    public void senseButton(){
        if (Greenfoot.mouseClicked(restartButton)){
            stoppedFailed();
            stoppedSuccess();
            Greenfoot.setWorld(new TitleScreen());
        }
    }
}
