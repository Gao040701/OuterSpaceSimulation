import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BaobabTree here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BaobabTree extends Stationary
{
    private GreenfootImage baobabTree= new GreenfootImage("baobabTree.png");
    private Planet planet;
    /**
     * Act - do whatever the BaobabTree wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setLocation(getX()+planet.getSpeed(), getY());
    }
    public BaobabTree(Planet planet){
        baobabTree.scale(200,200);
        setImage(baobabTree);
        //System.out.println("BaobabTree created!");
        this.planet = planet;
    }
}
