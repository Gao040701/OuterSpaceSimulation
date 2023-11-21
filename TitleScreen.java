import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @Angela Gao
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{
    private GreenfootImage[] imgs = new GreenfootImage[17];
    private int index = 0;
    private final int COUNT_NUM = 9;
    private int count = 0;
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 576, 1); 
        //setBackround(new GreenfootImage("titleScreen.png");
        for (int i = 0; i < 17; i++){
            imgs[i] = new GreenfootImage("introAnimation/ani"+i+".png");
            imgs[i].scale(1024, 576);
        }
    }
    
    public void act(){
        animate();
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
}
