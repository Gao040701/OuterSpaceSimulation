import greenfoot.*;
import java.util.List;

/**
 * The class HomePlanet represents the home planet in the game.
 * It extends the abstract class Planet and implements specific behavior for the home planet.
 * The home planet has health points, a health bar, and interacts with asteroids in the world.
 * 
 * Usage:
 * <li>Create an instance of HomePlanet in the game world.</li>
 * <li>The home planet has a health bar that decreases when colliding with asteroids.</li>
 * <li>When the health points reach zero, the home planet is removed from the world.</li>
 * <li>An explosion effect is triggered on collision with asteroids.</li>
 * <li>New asteroids are added to the world to maintain the total number.</li>
 * 
 * @author Jiayi Li
 * @version November 2023
 */
public class HomePlanet extends Planet {
    private GreenfootImage img;
    private int planetImgIndex, length;
    private SuperStatBar homeHpBar;

    /**
     * Constructor for the HomePlanet class.
     * Initializes the home planet's image, size, health, speed, and health bar.
     */
    public HomePlanet() {
        planetImgIndex = 1; // Assuming you want to use planet1.png as the image
        /**
         * Image credit
         * Artist: diluck
         * Title: Pixel planets set, pixel art solar system.
         * Link: https://img.freepik.com/premium-vector/pixel-planets-set-pixel-art-solar-system_158677-386.jpg
         */
        img = new GreenfootImage("planets/planet" + planetImgIndex + ".png");

        length = 200 + Greenfoot.getRandomNumber(41); // 200 + 0 to 40
        img.scale(length, length);
        totalHP = Galaxy.hpPerPlanet();
        decreaseHP = Galaxy.Rdecrease;
        setLocation(0, Greenfoot.getRandomNumber(276) + 150);
        speed = Greenfoot.getRandomNumber(1) + 1;
        setImage(img);
        homeHpBar = new SuperStatBar(totalHP, totalHP, this, 50, 10, -20, Color.GREEN, Color.RED, false, Color.BLACK, 1);
    }

    /**
     * Checks for collisions with asteroids and updates health points.
     * Triggers an explosion effect and adds new asteroids to the world on collision.
     */
    public void checkCollision() {
        List<Asteroids> asteroidsList = getWorld().getObjects(Asteroids.class);
        Actor actor = getOneIntersectingObject(Asteroids.class);
        if (actor instanceof Asteroids) {
            Asteroids a = (Asteroids) actor;
            totalHP -= decreaseHP;
            homeHpBar.update(totalHP);
            getWorld().removeObject(a);
            Explosion explosion = new Explosion(5);  // Create an explosion object
            getWorld().addObject(explosion, getX(), getY()); // Add the explosion to the world
            
            // Add new Asteroids to the world to maintain the total number
            int currentAsteroids = asteroidsList.size();
            int asteroidsToAdd = Galaxy.getNumOfAsteroids() - currentAsteroids;

            for (int i = 0; i < asteroidsToAdd + 1; i++) {
                int x = Greenfoot.getRandomNumber(getWorld().getWidth());
                int y = Greenfoot.getRandomNumber(getWorld().getHeight());
                getWorld().addObject(new Asteroids(), x, y);
            }
        }
    }

    /**
     * Adds the health bar to the world when the HomePlanet is added.
     */
    public void addedToWorld(World w) {
        w.addObject(homeHpBar, getX() / 2, getY() / 2);
    }

    /**
     * Checks if the HomePlanet should be removed from the world.
     */
    public void checkAndRemove() {
        if (getWorld() != null && totalHP <= 0 && appear) {
            getWorld().removeObject(homeHpBar);
            getWorld().removeObject(this); // Remove the HomePlanet from the world
            appear = false;
        }
    }

    /**
     * Act method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     * Moves the home planet, checks for collisions, updates the health bar, and checks if
     * the home planet should be removed.
     */
    public void act() {
        if (appear) {
            super.act();
        }

        if (appear) {
            if (getX() > getWorld().getWidth()) {
                getWorld().removeObject(this); // Remove the current planet object
            }
            homeHpBar.moveMe();
        }
        checkAndRemove();
    }
}

