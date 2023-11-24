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
    private GreenfootImage titleText = new GreenfootImage("titleText.png");
    private GreenfootImage titleStar = new GreenfootImage("textStar.png");
    private GreenfootImage start = new GreenfootImage("startButton.png");
    private Image star1;
    private Image star2;
    private Image startButton;
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {
        super(1024, 576, 1); 
        for (int i = 0; i < 17; i++){
            imgs[i] = new GreenfootImage("introAnimation/ani"+i+".png");
            imgs[i].scale(1024, 576);
        }
        addObject(new Image(titleText, (int)(titleText.getWidth() * 1.3), (int)(titleText.getHeight() * 1.3)), 300, 200);
        star1 = new Image(titleStar);
        star2 = new Image(titleStar);
        startButton = new Image(start, start.getWidth() * 2, start.getHeight() * 2);
        addObject(star1, 125, 130);
        addObject(star2, 420, 130);
        addObject(startButton, 300, 400);
    }
    
    public void act(){
        animate();
        star1.turn(2);
        star2.turn(2);
        toNextWorld();
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

    private void toNextWorld(){
        if (Greenfoot.mouseClicked(startButton)){
            Greenfoot.setWorld(new IntroAnimation());
        }
    }
}
