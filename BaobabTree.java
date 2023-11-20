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
    private final int COUNT_NUM = 100;
    private int count;
    private LittlePrince TLP;
    private HitBox box;
    private SuperStatBar clueBar;
    private boolean firstGenerated = true;
    private boolean isRotated = false;
    private int clue;
    /**
     * Act - do whatever the BaobabTree wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setLocation(getX()+planet.getSpeed(), getY());
        if (firstGenerated){
            box = new HitBox(this);
            getWorld().addObject(box, getX(), getY());
            if (isRotated) getBox().setRotation(90);
            firstGenerated = false; 
        }
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
                isRotated = true;
                break;
            case 3:
                setRotation(180); //points down
                break;
            case 4:
                setRotation(-90); //points left 
                isRotated = true;
                break;
        }
        clueBar = new SuperStatBar(110, clue, null, 110, 10, 0, Color.RED, Color.GREEN, false, Color.BLACK, 1);
    }

    public void checkHitingTLP(){
        TLP = (LittlePrince)getOneObjectAtOffset(-getImage().getWidth()/6, 0, LittlePrince.class);
        if (TLP == null){
            TLP = (LittlePrince)getOneObjectAtOffset(getImage().getWidth()/6, 0, LittlePrince.class);
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
    
    public Planet getPlanet(){
        return planet;
    }
    
    public void removeBaobabTree(){
        if (count == COUNT_NUM){
            if (getWorld() instanceof Galaxy){
                Galaxy galaxy = (Galaxy)getWorld();
                galaxy.changeClue(galaxy.getAmountOfClues());
                //clueBar.update(clue);
            }
            getWorld().removeObject(box);
            getWorld().removeObject(this);
            return;
        }else{
            count++;
        }
    }
    /*
    public void addedToWorld (World w){
        w.addObject(clueBar, 964, 100);
        clueBar.update(clue);
    }
    */
    public HitBox getBox(){
        return box;
    }
}
