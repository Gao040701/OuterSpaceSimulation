import greenfoot.*;  

/**
 * The BaobabTree class represents a stationary tree in the game that provides clues to the player.
 * It includes methods for tree generation, positioning, and interaction with the Little Prince.
 * 
 * @author Angela Gao
 * @version November 2023
 */
public class BaobabTree extends Stationary
{
    private GreenfootImage baobabTree= new GreenfootImage("baobabTree.png");  // Image of the BaobabTree
    private Planet planet;  // The associated Planet
    private int rotation;  // Rotation of the BaobabTree
    private int count;  // Counter for removal delay
    private LittlePrince TLP;  // Reference to the Little Prince
    private HitBox box;  // Hitbox associated with the BaobabTree
    private SuperStatBar clueBar;  // SuperStatBar displaying the remaining clue count
    private boolean firstGenerated = true;  // Flag indicating if the hitbox has been generated
    private boolean isRotated = false;  // Flag indicating if the BaobabTree is rotated
    private int clue;  // Clue count for the BaobabTree

    /**
     * Act method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     * Updates the BaobabTree's position based on the associated Planet's speed.
     * Generates the hitbox for the BaobabTree upon first generation.
     * Counts down and removes the BaobabTree after a delay.
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

    /**
     * Constructor for the BaobabTree class.
     * 
     * @param planet The associated Planet.
     * @param rotation The rotation of the BaobabTree.
     */
    public BaobabTree(Planet planet, int rotation){
        baobabTree.scale(200,200);
        setImage(baobabTree);
        this.planet = planet;

        // Set the rotation based on the input parameter
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

    /**
     * Checks if the BaobabTree is hitting the Little Prince.
     */
    public void checkHitingTLP(){
        TLP = (LittlePrince)getOneObjectAtOffset(-getImage().getWidth()/6, 0, LittlePrince.class);
        if (TLP == null){
            TLP = (LittlePrince)getOneObjectAtOffset(getImage().getWidth()/6, 0, LittlePrince.class);
        }
    }
    
    /**
     * Gets the rotation of the BaobabTree.
     * 
     * @return The rotation of the BaobabTree.
     */
    public int getRotation(){
        return rotation;
    }
    
    /**
     * Gets the Y offset of the BaobabTree's image.
     * 
     * @return The Y offset of the BaobabTree's image.
     */
    public int getYOffset(){
        return getImage().getHeight()/2 - 15;
    }
    
    /**
     * Gets the X offset of the BaobabTree's image.
     * 
     * @return The X offset of the BaobabTree's image.
     */
    public int getXOffset(){
        return getImage().getWidth()/2 - 15;
    }
    
    /**
     * Gets the associated Planet.
     * 
     * @return The associated Planet.
     */
    public Planet getPlanet(){
        return planet;
    }
    
    /**
     * Removes the BaobabTree after a delay, updating the clue count in the Galaxy.
     */
    public void removeBaobabTree(int countNum){
        if (count == countNum){
            if (getWorld() instanceof Galaxy){
                Galaxy galaxy = (Galaxy)getWorld();
                galaxy.changeClue(galaxy.getAmountOfClues());
            }
            getWorld().removeObject(box);
            getWorld().removeObject(this);
            return;
        }else{
            count++;
        }
    }
    
    /**
     * Gets the HitBox associated with the BaobabTree.
     * 
     * @return The HitBox associated with the BaobabTree.
     */
    public HitBox getBox(){
        return box;
    }
}
