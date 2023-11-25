import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * the animation introducing the story Le Petit Prince (The Little Prince)
 * 
 * @Angela Gao 
 * @v2 11/12/2023 
 */
public class IntroAnimation extends World
{
    private GreenfootImage[] animation= new GreenfootImage[135];
    private int index = 0;
    private final int COUNT_NUM = 9;
    private int count = 0;
    private GreenfootSound introMusic;
    
    public IntroAnimation()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 576, 1); 
        /**
         * Music credit: Hans Zimmer
         * Title: Ascending
         */
        introMusic = new GreenfootSound("introAnimationMusic.mp3");
        introMusic.setVolume(50);
        for (int i = 0; i < animation.length; i++){
            animation[i] = new GreenfootImage("introAnimation/ani"+i+".png");
            animation[i].scale(1024, 576);
        }
    }
    
    public void act(){
        animate();
    }
    
    public void started(){
        introMusic.playLoop(); 
    }

    public void stopped(){
        introMusic.pause();
    }
    
    public void animate(){
        if (index < animation.length){
            if (count == COUNT_NUM){
                setBackground(animation[index]);
                index++;
                count = 0;
            }else{
                count++;
            }
        }else{
            Greenfoot.setWorld(new SetValuePage());
            stopped();
        }
    }
}
