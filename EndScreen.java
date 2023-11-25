import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
     * Constructor for objects of class EndScreen.
     * 
     */
    public EndScreen(boolean success)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 576, 1); 
        for (int i = 0; i < 17; i++){
            imgs[i] = new GreenfootImage("introAnimation/ani"+i+".png");
            imgs[i].scale(1024, 576);
        }
        fail.scale(1024, 576);
        this.success = success;
        if (success){
            text = new Image(congradulation);
            addObject(text, 400, 200);
        }else{
            setBackground(fail);
            text = new Image(waiting);
            addObject(text, 400, 150);
        }
        restartButton = new Image(restart, restart.getWidth() * 2, restart.getHeight() * 2);
        addObject(restartButton, 300, 400);
    }
    
    public void act(){
        if (success){
            animate();
        }
        senseButton();
    }
    
    public void animate(){
        if (index < imgs.length){
            if (count == COUNT_NUM){
                setBackground(imgs[index]);
                index++;
                count = 0;
            }else{
                count++;
            }
        }else{
            index = 0;
        }
    }
    
    public void senseButton(){
        if (Greenfoot.mouseClicked(restartButton)){
            Greenfoot.setWorld(new TitleScreen());
        }
    }
}
