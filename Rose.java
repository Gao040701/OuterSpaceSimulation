import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/** The Rose class represents fixed objects in the game, specifically roses.
  * Roses are decorative elements and do not interact with other objects in the game.
  *The purpose of simulation is to let the little prince find the rose. When the clue bar is full, the rose will appear on the planet.
  *
  * Usage:
  * <li>Create Rose instances in the game world for decoration purposes. </li>
  * <li>The rose is stationary and does not perform any specific action. </li>
  * 
  * @author Angela Gao, Jiayi Li 
  * @version November 2023
 */
public class Rose extends Stationary
{
    private GreenfootImage rose = new GreenfootImage("rose.png");
    /**
     * Act - rose moves on planet with the speed of one
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move(1);
    }
    /**
     * Constructor for the Rose class.
     * Sets the image for the rose using the provided GreenfootImage.
     */
    public Rose(){
        setImage(rose);
    }
}
