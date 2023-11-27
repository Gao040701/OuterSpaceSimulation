import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Rose here.
 * 
 * @author Angela Gao, Jiayi Li 
 * @version November 2023
 */
public class Rose extends Stationary
{
    private GreenfootImage rose = new GreenfootImage("rose.png");
    /**
     * Act - do whatever the Rose wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move(1);
    }
    public Rose(){
        setImage(rose);
    }
}
