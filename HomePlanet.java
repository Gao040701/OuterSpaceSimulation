import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

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
        totalHP=Galaxy.Rhp;
        decreaseHP=Galaxy.Rdecrease;
        setLocation(0, Greenfoot.getRandomNumber(276) + 150);
        speed = Greenfoot.getRandomNumber(1) + 1;
        setImage(img);
        homeHpBar = new SuperStatBar(100, 100, this, 50, 10, -20, Color.GREEN, Color.RED, false, Color.BLACK, 1);
        hitbox = new Hitbox(1, 1);
    }

    public void checkCollision() {
        List<Asteroids> asteroidsList = getWorld().getObjects(Asteroids.class);
        Actor actor = getOneIntersectingObject(Asteroids.class);
        if (actor instanceof Asteroids) {
            Asteroids a = (Asteroids) actor;
            totalHP -= decreaseHP;
            homeHpBar.update(totalHP);
            getWorld().removeObject(a);

            // 在这里添加新的 Asteroids，以保持总数为三个
            int currentAsteroids = asteroidsList.size();
            int asteroidsToAdd = 3 - currentAsteroids;

            for (int i = 0; i < asteroidsToAdd+1; i++) {
                int x = Greenfoot.getRandomNumber(getWorld().getWidth());
                int y = Greenfoot.getRandomNumber(getWorld().getHeight());
                getWorld().addObject(new Asteroids(), x, y);
            }
        }
    }

    public void addedToWorld (World w){
        w.addObject(homeHpBar, getX() / 2, getY() / 2);
        System.out.println("Added homeHpBar");
        w.addObject(hitbox, getX(), getY() - getRadius());
        System.out.println("Home X coord: " + getX()+ "Home Y coord: "+ (getY() - getRadius()));
    }

    public void checkAndRemove ()
    {
        if (getWorld() != null && totalHP <= 0 && appear) {
            getWorld().removeObject(homeHpBar);
            getWorld().removeObject(hitbox);
            getWorld().removeObject(this); // 从世界中移除我
            appear=false;
            System.out.println(appear);
        }
    }

    public void act()
    {
        if(appear){super.act();
            // Add your action code here.
            if (getX() > getWorld().getWidth()) {
                getWorld().removeObject(this); // 移除当前星球对象
            }
            homeHpBar.moveMe();
            hitbox.move((int)speed);
            checkAndRemove();
        }
    }
}
