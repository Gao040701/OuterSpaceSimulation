import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * The little prince will walk around the planet and jump out of the planets
 * when all the baobab trees are destroyed.
 * @Zhiyu (Jennifer) Zhou & Angela Gao 
 * @Jan 7, 2022 - v1.0
 */
public class LittlePrince extends Moving
{
    
    private SuperStatBar princeHpBar;
    private int totalHP;
    private int decreaseHP;
    private boolean appear;
    /**
     * Act - do whatever the LittlePrince wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        checkCollision();
    }

    public LittlePrince(GreenfootImage[] walk, GreenfootImage[] fly, GreenfootImage[] dig, GreenfootImage[] flyInverted){
        super(walk, fly, dig, flyInverted);
        totalHP = Galaxy.hpPerPlanet();
        decreaseHP=Galaxy.Rdecrease;
        appear=true;
        princeHpBar = new SuperStatBar(totalHP, totalHP, this, 100, 10, -50, Color.GREEN, Color.RED, false, Color.BLACK, 1);
    }

    public void checkCollision() {
        List<Asteroids> asteroidsList = getWorld().getObjects(Asteroids.class);
        Actor actor = getOneIntersectingObject(Asteroids.class);
        if (actor instanceof Asteroids) {
            Asteroids a = (Asteroids) actor;
            totalHP -= decreaseHP;
            princeHpBar.update(totalHP);
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
        w.addObject(princeHpBar, getX(), getY());
    }

    public int getPrinceX(){
        return getX();
    }

    public int getPrinceY(){
        return getY();
    }
}
