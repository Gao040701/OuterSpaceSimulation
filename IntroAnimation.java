import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class IntroAnimation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntroAnimation extends World
{
    private GreenfootImage[] animation= new GreenfootImage[51];
    private int index = 0;
    private final int countNum = 7;
    private int count = 0;
    public IntroAnimation()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 576, 1); 
        for (int i = 0; i < animation.length; i++){
            animation[i] = new GreenfootImage("introAnimation/ani"+i+".png");
            animation[i].scale(1024, 576);
        }
    }
    
    public void act(){
        animate();
    }
    
    public void animate(){
        if (index < animation.length){
            if (count == countNum){
                setBackground(animation[index]);
                index++;
                count = 0;
            }else{
                count++;
            }
        }else{
            //go to game world 
            Greenfoot.setWorld(new Galaxy());
        }
    }
}
