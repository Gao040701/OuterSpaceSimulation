import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HitBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class HitBox extends Stationary
{
    private BaobabTree tree;
    private GreenfootImage img;
    public HitBox(BaobabTree tree){
        this.tree = tree;
        img = new GreenfootImage(tree.getImage().getWidth()/3, tree.getImage().getHeight());
        img.setColor(Color.RED);
        img.fill();
        setImage(img);
        //img.setTransparency(0);
    }
    
    public void act(){
        setLocation(getX() + tree.getPlanet().getSpeed(), getY());
        if (getBaobabTree() == null){
            getWorld().removeObject(this);
        }
        //setRotation(90);
    }
    
    public BaobabTree getBaobabTree(){
        return tree;
    }
}
