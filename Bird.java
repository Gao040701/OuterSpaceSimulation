import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The bird class represents a bird actor in the world.
 * It follows the movement of the LittlePrince actor and changes its appearance based on the rotation.
 * The bird is transparent when the rotation detection of LittlePrince is active.
 * 
 * @author Jiayi Li 
 * @version November 2023
 */
public class Bird extends Moving
{
    private GreenfootImage birdImage = new GreenfootImage("birds.png");
    private GreenfootImage birdImageInverted = new GreenfootImage("birds.png");
    private static int x;
    private static int y;
    /**
     * Constructor for objects of class bird.
     * Initializes the bird with its default image and inverted image.
     */
    public Bird(){
        setImage(birdImage);
        birdImage.mirrorHorizontally();
        birdImageInverted.mirrorVertically();
        birdImageInverted.mirrorVertically();
    }
    
    /**
     * Act - do whatever the bird wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        LittlePrince littlePrince = getLittlePrince();
        if(littlePrince.getRotationDetection() == false){
            if(littlePrince != null){
                getImage().setTransparency(255);
                int rotation=littlePrince.getRotation();
                if (littlePrince.getRotation() < 270 && littlePrince.getRotation() > 90){
                    x = littlePrince.getX()-(int) (50 * Math.sin(Math.toRadians(littlePrince.getRotation())));
                    y = littlePrince.getY()-(int) Math.abs(80 * Math.cos(Math.toRadians(littlePrince.getRotation())));
                    setImage(birdImage);
                }else{
                    x = littlePrince.getX()-(int) (50 * Math.sin(Math.toRadians(littlePrince.getRotation())));
                    y = littlePrince.getY()-(int) Math.abs(80 * Math.cos(Math.toRadians(littlePrince.getRotation())));
                    setImage(birdImageInverted);
                }
                setLocation(x,y);
                setRotation(rotation);
            }
        }else{
            getImage().setTransparency(0);
        }
    }

    /**
     * Gets the instance of LittlePrince in the world.
     * @return The LittlePrince instance if found, otherwise null.
     */
    private LittlePrince getLittlePrince() {
        // Try to get an instance of LittlePrince in the world
        LittlePrince littlePrince = (LittlePrince) getWorld().getObjects(LittlePrince.class).get(0);
        return littlePrince;
    }
}
