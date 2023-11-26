import greenfoot.*;  

/**
 * The HitBox class represents a hitbox associated with a BaobabTree in the game. 
 * It allows for collision detection and tracking of the BaobabTree's position.
 * 
 * @author Angela Gao
 * @version November 2023
 */
public class HitBox extends Stationary
{
    private BaobabTree tree;  // The associated BaobabTree
    private GreenfootImage img;  // The image used for the hitbox

    /**
     * Constructor for the HitBox class.
     * 
     * @param tree The BaobabTree associated with the hitbox.
     */
    public HitBox(BaobabTree tree){
        this.tree = tree;
        img = new GreenfootImage(tree.getImage().getWidth()/3, tree.getImage().getHeight());
        img.setColor(Color.RED);
        img.fill();
        setImage(img);
        img.setTransparency(0);
    }
    
    /**
     * Act method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     * Updates the hitbox's position based on the associated BaobabTree's speed.
     * Removes the hitbox if the associated BaobabTree no longer exists.
     */
    public void act(){
        setLocation(getX() + tree.getPlanet().getSpeed(), getY());
        if (getBaobabTree() == null){
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Gets the associated BaobabTree.
     * 
     * @return The BaobabTree associated with the hitbox.
     */
    public BaobabTree getBaobabTree(){
        return tree;
    }
}
