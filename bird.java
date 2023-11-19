import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class bird here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class bird extends Moving
{
    /**
     * Act - do whatever the bird wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage birdImage = new GreenfootImage("birds.png");
    private static int x;
    private static int y;
    public bird(GreenfootImage[] walk, GreenfootImage[] fly, GreenfootImage[] dig, GreenfootImage[] flyInverted){
        super(walk, fly, dig, flyInverted);
        setImage(birdImage);
    }
    public void act()
    {
        // Add your action code here.
        if(!rotateDetection){
            LittlePrince TLP=(LittlePrince) getOneIntersectingObject(LittlePrince.class);
            x=TLP.getX();
            y=TLP.getY()+20;
            int rotation=TLP.getRotation();
            setLocation(TLP.getX(),getY()+20);
            setRotation(rotation);
        }
    }
    public static int birdX(){
        return x;
    }
    public static int birdY(){
        return y;
    }
}
