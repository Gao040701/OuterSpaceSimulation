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
    private GreenfootImage birdImageInverted = new GreenfootImage("birds.png");
    private static int x;
    private static int y;
    public bird(){
        //birdImage.mirrorVertically();
        setImage(birdImage);
        birdImageInverted.mirrorVertically();
    }
    public void act()
    {
        // Add your action code here.
        LittlePrince littlePrince = getLittlePrince();
        if(littlePrince.getRotationDetection() == false){
            //LittlePrince TLP=(LittlePrince) getOneIntersectingObject(LittlePrince.class);
            if(littlePrince != null){
                getImage().setTransparency(255);
                int rotation=littlePrince.getRotation();
                x = littlePrince.getX()-(int) (50 * Math.sin(Math.toRadians(littlePrince.getRotation())));
                y = littlePrince.getY()-(int) Math.abs(80 * Math.cos(Math.toRadians(littlePrince.getRotation())));
                setLocation(x,y);
                setRotation(rotation);
                if(littlePrince.getRotation() < 270 && littlePrince.getRotation() > 90){
                    setImage(birdImageInverted);
                }else{
                    setImage(birdImage);
                }
            }
        }else{
            getImage().setTransparency(0);
        }
    }
    public static int birdX(){
        return x;
    }
    public static int birdY(){
        return y;
    }
    private LittlePrince getLittlePrince() {
        // Try to get an instance of LittlePrince in the world
        LittlePrince littlePrince = (LittlePrince) getWorld().getObjects(LittlePrince.class).get(0);
        return littlePrince;
    }
}
