import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HomePlanet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HomePlanet extends Planet
{
    /**
     * Act - do whatever the HomePlanet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage img;
    private int planetImgIndex, length;
    private SuperStatBar homeHpBar;
    private Hitbox hitbox;
    public HomePlanet(){
        planetImgIndex = 1; // Assuming you want to use planet1.png as the image
        img = new GreenfootImage("planets/planet" + planetImgIndex + ".png");

        length = 200 + Greenfoot.getRandomNumber(41); // 200 + 0 to 40
        img.scale(length, length);

        setLocation(0, Greenfoot.getRandomNumber(276) + 150);
        speed = Greenfoot.getRandomNumber(1) + 1;
        setImage(img);
        homeHpBar = new SuperStatBar(100, 100, this, 50, 10, -20, Color.GREEN, Color.RED, false, Color.BLACK, 1);
        hitbox = new Hitbox(1, 1);
    }

    public void addedToWorld (World w){
        w.addObject(homeHpBar, getX() / 2, getY() / 2);
        System.out.println("Added homeHpBar");
        w.addObject(hitbox, getX(), getY() - getRadius());
        System.out.println("Home X coord: " + getX()+ "Home Y coord: "+ (getY() - getRadius()));
    }

    public void checkCollision(){
        Asteroids a = (Asteroids) getOneObjectAtOffset((int) speed + getImage().getWidth() / 2, 0, Asteroids.class);

    }

    public void act()
    {
        super.act();
        // Add your action code here.
        if (getX() > getWorld().getWidth()) {
            getWorld().removeObject(this); // 移除当前星球对象
        }
        homeHpBar.moveMe();
        hitbox.move((int)speed);
    }
}
