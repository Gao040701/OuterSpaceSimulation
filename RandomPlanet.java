import greenfoot.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class RandomPlanet represents a randomly generated planet in the game.
 * It extends the abstract class Planet and implements specific behavior for random planets.
 * Random planets can have different images, health points, and interact with asteroids in the world.
 * 
 * Usage:
 * - Create instances of RandomPlanet in the game world.
 * - Random planets move to the right and may spawn new planets at a certain distance.
 * - They have health points and a health bar that decreases on collision with asteroids.
 * - On reaching zero health points, they may spawn a new planet and are removed from the world.
 * - Random planets can generate trees or roses based on game conditions.
 * 
 * @author Jiayi Li
 * @version November 2023
 */
public class RandomPlanet extends Planet {
    private static boolean canSpawnNext;
    private GreenfootImage[] planets = new GreenfootImage[7];
    private int planetImgIndex, length;
    private GreenfootImage img;
    private int distance;
    private int num = 0;
    private SuperStatBar randomHpBar;
    private int ylocation;
    private boolean firstGenerated = true;
    private ArrayList<BaobabTree> trees = new ArrayList<BaobabTree>();
    private boolean roseAppear = false;
    private RandomPlanet newPlanet;

    /**
     * Constructor for the RandomPlanet class.
     * Initializes the random planet's image, size, health, speed, and health bar.
     */
    public RandomPlanet() {
        speed = Greenfoot.getRandomNumber(1) + 1;
        canSpawnNext = false;
        totalHP = Galaxy.hpPerPlanet();
        decreaseHP = Galaxy.Rdecrease;

        for (int i = 0; i < 7; i++) {
            planets[i] = new GreenfootImage("planets/planet" + i + ".png");
        }

        randomImage();
        randomHpBar = new SuperStatBar(totalHP, totalHP, this, 50, 10, -20, Color.GREEN, Color.RED, false, Color.BLACK, 1);
        appear = true;
        this.roseAppear = roseAppear;
    }

    /**
     * Act method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     * Moves the random planet, checks for collisions, updates the health bar, and checks if
     * the random planet should be removed.
     */
    public void act() {
        if (!appear) {
            return; // If the object should not appear, return
        }

        if (appear) {
            super.act(); // Call the base class's act() method to implement basic planet movement logic
        }

        if (getImage() == null) {
            appear = false; // If the image is null, the object has been removed, so set appear to false
            return;
        }

        if (appear) {
            if (getX() > getWorld().getWidth()) {
                removeRanPlanet();
                return;
            } else if (!canSpawnNext) {
                canSpawnNext = true;

                if (num == 2) {
                    num = 0;
                }
            } else if (getX() > 600 && canSpawnNext && num == 0) {
                num++;
                canSpawnNext = false;

                if (getWorld() instanceof Galaxy) {
                    Galaxy galaxy = (Galaxy) getWorld();
                    roseAppear = galaxy.getRose();
                }

                newPlanet = new RandomPlanet();
                ylocation = Greenfoot.getRandomNumber(276) + 150;
                getWorld().addObject(newPlanet, 0, ylocation);
                getWorld().addObject(newPlanet.getHpBar(), 0, Greenfoot.getRandomNumber(276) + 150);
            }
            randomHpBar.moveMe();
        }

        checkAndRemove();

        if (appear && firstGenerated) {
            if (getWorld() instanceof Galaxy) {
                Galaxy galaxy = (Galaxy) getWorld();
                roseAppear = galaxy.getRose();
            }

            if (!roseAppear) {
                generateTrees();
            } else {
                generateRose();
                speed = 2;
            }
            firstGenerated = false;
        }
    }

    /**
     * Checks for collisions with asteroids and updates health points.
     * Triggers an explosion effect and adds new asteroids to the world on collision.
     */
    public void checkCollision() {
        List<Asteroids> asteroidsList = getWorld().getObjects(Asteroids.class);
        Asteroids a = (Asteroids) getOneObjectAtOffset((int) speed + -getImage().getWidth() / 2, 0, Asteroids.class); // Left
        if (a == null) {
            a = (Asteroids) getOneObjectAtOffset((int) speed + getImage().getWidth() / 2, 0, Asteroids.class); // Right
        }
        if (a == null) {
            a = (Asteroids) getOneObjectAtOffset((int) speed, -getImage().getWidth() / 2, Asteroids.class); // North
        }
        if (a == null) {
            a = (Asteroids) getOneObjectAtOffset((int) speed, getImage().getWidth() / 2, Asteroids.class); // South
        }
        if (a == null) {
            a = (Asteroids) getOneObjectAtOffset((int) (speed - getImage().getWidth() * Math.sqrt(2) / 2),
                    (int) (-getImage().getHeight() * Math.sqrt(2) / 2), Asteroids.class); // WestNorth
        }
        if (a == null) {
            a = (Asteroids) getOneObjectAtOffset((int) (speed + getImage().getWidth() * Math.sqrt(2) / 2),
                    (int) (getImage().getHeight() * Math.sqrt(2) / 2), Asteroids.class); // WestSouth
        }
        if (a == null) {
            a = (Asteroids) getOneObjectAtOffset((int) (speed - getImage().getWidth() * Math.sqrt(2) / 2),
                    (int) (getImage().getHeight() * Math.sqrt(2) / 2), Asteroids.class); // WestSouth
        }
        if (a == null) {
            a = (Asteroids) getOneObjectAtOffset((int) (speed + getImage().getWidth() * Math.sqrt(2) / 2),
                    (int) (-getImage().getHeight() * Math.sqrt(2) / 2), Asteroids.class); // WestSouth
        }
        if (a != null) {
            Explosion explosion = new Explosion(5); // Create an explosion object
            getWorld().addObject(explosion, getX(), getY()); // Add the explosion to the world
            totalHP -= decreaseHP;
            randomHpBar.update(totalHP);
            getWorld().removeObject(a);

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
     * Sets a random image for the random planet.
     * Scales the image size within a specific range.
     */
    public void randomImage() {
        planetImgIndex = Greenfoot.getRandomNumber(7);
        img = planets[planetImgIndex];
        length = 200 + Greenfoot.getRandomNumber(41); // 200 + 0 to 40
        img.scale(length, length);
        setImage(img);
        radius = length / 2;
    }

    /**
     * Returns the health bar associated with the random planet.
     * 
     * @return The SuperStatBar object representing the health bar.
     */
    public SuperStatBar getHpBar() {
        return randomHpBar;
    }

    /**
     * Adds the health bar to the world when the RandomPlanet is added.
     */
    public void addedToWorld(World w) {
        w.addObject(randomHpBar, getX() / 2, getY() / 2);
    }

    /**
     * Removes the random planet, its health bar, and associated trees from the world.
     */
    public void removeRanPlanet() {
        getWorld().removeObject(randomHpBar);

        for (int i = 0; i < trees.size(); i++) {
            if (trees.get(i) != null) {
                getWorld().removeObject(trees.get(i));
                getWorld().removeObject(trees.get(i).getBox());
            }
        }

        getWorld().removeObject(this);
    }

    /**
     * Generates four trees around the random planet based on its position.
     */
    public void generateTrees() {
        for (int i = 0; i < 4; i++) {
            BaobabTree tree = new BaobabTree(this, i + 1);
            trees.add(tree);

            switch (i + 1) {
                case 1:
                    getWorld().addObject(tree, getX(), getY() - radius - tree.getYOffset());
                    break;
                case 2:
                    getWorld().addObject(tree, getX() + radius + tree.getXOffset(), getY());
                    break;
                case 3:
                    getWorld().addObject(tree, getX(), getY() + radius + tree.getYOffset());
                    break;
                case 4:
                    getWorld().addObject(tree, getX() - radius - 3 * tree.getXOffset(), getY());
                    break;
            }
        }
    }
    /**
     * Checks if the random planet should be removed based on its health points.
     * If health points are less than or equal to zero, the planet is removed.
     * Additionally, if the planet is to the left of or at X-coordinate 600, it may spawn a new planet.
     */
    public void checkAndRemove() {
        if (getWorld() != null && totalHP <= 0) {
            if (getX() <= 600) {
                num++;
                canSpawnNext = false;
    
                newPlanet = new RandomPlanet();
                ylocation = Greenfoot.getRandomNumber(276) + 150;
                getWorld().addObject(newPlanet, 0, ylocation);
                getWorld().addObject(newPlanet.getHpBar(), 0, Greenfoot.getRandomNumber(276) + 15);
            }
            removeRanPlanet();
            appear = false;
            return;
        }
    }

    /**
     * Generates a rose at the top of the random planet when roses are enabled in the game.
     */
    public void generateRose() {
        Rose rose = new Rose();
        getWorld().addObject(rose, getX(), getY() - getRadius() - 20);
    }
}

