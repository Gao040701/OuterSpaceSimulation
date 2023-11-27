import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Image here.
 * 
 * @author Angela Gao 
 * @version November 2023
 */
public class Image extends Actor
{
    private GreenfootImage img;
    private int width;
    private int height;
    public void act()
    {
        // Add your action code here.
        setImage(img);
    }
    
    public Image(GreenfootImage img, int width, int height){
        this.img = img;
        this.width = width;
        this.height = height;
        img.scale(width, height);
    }
    
    public Image(GreenfootImage img){
        this.img = img;
    }
}
