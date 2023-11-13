import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BaobabTree here.
 * 
 * @Angela Gao
 * @version (a version number or a date)
 */
public class BaobabTree extends Stationary
{
    private GreenfootImage baobabTree= new GreenfootImage("baobabTree.png");
    private Planet planet;
    private int rotation;
    /**
     * Act - do whatever the BaobabTree wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setLocation(getX()+planet.getSpeed(), getY());
    }
    public BaobabTree(Planet planet, int rotation){
        baobabTree.scale(200,200);
        setImage(baobabTree);
        //System.out.println("BaobabTree created!");
        this.planet = planet;
        switch(rotation){
            case 1: 
                setRotation(0); //points up
                break;
            case 2:
                setRotation(90); //points right
                break;
            case 3:
                setRotation(180); //points down
                break;
            case 4:
                setRotation(-90); //points left 
                break;
        }
    }
    
    public int getRotation(){
        return rotation;
    }
    
    public int getYOffset(){
        return getImage().getHeight()/2 - 15;
    }
    
    public int getXOffset(){
        return getImage().getWidth()/2 - 15;
    }
}
