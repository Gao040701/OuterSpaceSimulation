import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Image class represents an actor that displays a GreenfootImage.
 * It provides constructors to set the image with or without specifying dimensions.
 * 
 * Usage:
 * <li>Create an instance of Image with a GreenfootImage and optional width and height.</li>
 * <li>The act method sets the image for display.</li>
 * 
 * @author Angela Gao
 * @version November 2023
 */
public class Image extends Actor
{
    private GreenfootImage img; // The GreenfootImage to be displayed
    private int width; // Width of the displayed image
    private int height; // Height of the displayed image
    
    /**
     * Act method for the Image class.
     * Sets the image for display.
     */
    public void act()
    {
        setImage(img);
    }
    
    /**
     * Constructor for Image with specified dimensions.
     * 
     * @param img The GreenfootImage to be displayed.
     * @param width The width of the displayed image.
     * @param height The height of the displayed image.
     */
    public Image(GreenfootImage img, int width, int height){
        this.img = img;
        this.width = width;
        this.height = height;
        img.scale(width, height);
    }
    
    /**
     * Constructor for Image without specified dimensions.
     * 
     * @param img The GreenfootImage to be displayed.
     */
    public Image(GreenfootImage img){
        this.img = img;
    }
}

