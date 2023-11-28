import greenfoot.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class RandomPlanet represents a randomly generated planet in the game.
 * It extends the abstract class Planet and implements specific behavior for random planets.
 * Random planets can have different images, health points, and interact with asteroids in the world.
 * 
 * Usage:
 * <li>Create instances of RandomPlanet in the game world.</li>
 * <li>Random planets move to the right and may spawn new planets at a certain distance.</li>
 * <li>They have health points and a health bar that decreases on collision with asteroids.</li>
 * <li>On reaching zero health points, they may spawn a new planet and are removed from the world.</li>
 * <li>Random planets can generate trees or roses based on game conditions.</li>
 * 
 * @author Jiayi Li
 * @Editor: Angela Gao
 * @version November 2023
 */
public class RandomPlanet extends Planet {
    private static boolean canSpawnNext; // Flag indicating whether a new RandomPlanet can be spawned
    private GreenfootImage[] planets = new GreenfootImage[7]; // Array to store different planet images
    private int planetImgIndex, length; // Index for the current planet image and its length
    private GreenfootImage img; // Image of the RandomPlanet
    private int distance; // Distance variable (unused)
    private int num = 0; // Counter for planet spawning
    private SuperStatBar randomHpBar; // Health bar for the RandomPlanet
    private int ylocation; // Y-coordinate for planet placement
    private boolean firstGenerated = true; // Flag to track if the planet has been initially generated
    private ArrayList<BaobabTree> trees = new ArrayList<BaobabTree>(); // List to store BaobabTree objects
    private boolean roseAppear = false; // Flag indicating if roses appear in the game
    private RandomPlanet newPlanet; // Reference to the newly spawned RandomPlanet
    private double cos45, cos30, cos60; // Trigonometric constants for calculations (cosine values)
    private static boolean oneRose; // Flag indicating whether a rose has been generated
    
    /**
     * Constructor for the RandomPlanet class.
     * Initializes the random planet's image, size, health, speed, and health bar.
     */
    public RandomPlanet() {
        speed = Greenfoot.getRandomNumber(1) + 1;// Set random speed between 1 and 2
        canSpawnNext = false;// Initialize flag to prevent immediate spawning
        totalHP = Galaxy.hpPerPlanet();// Set total health points based on Galaxy configuration
        decreaseHP = Galaxy.Rdecrease;// Set health decrease amount

        // Load planet images into the array
        for (int i = 0; i < 7; i++) {
            planets[i] = new GreenfootImage("planets/planet" + i + ".png");
        }

        randomImage();// Set a random image for the RandomPlanet
        randomHpBar = new SuperStatBar(totalHP, totalHP, this, 50, 10, -20, Color.GREEN, Color.RED, false, Color.BLACK, 1);
        appear = true;// Set appear flag to true
        this.roseAppear = roseAppear;// Initialize rose appearance flag

    }

    /**
     * Act method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     * Moves the random planet, checks for collisions, updates the health bar, and checks if
     * the random planet should be removed.
     */
    public void act() {
        if (!appear) {
            return; // If the object should not appear, return
        }else{
            super.act(); // Call the base class's act() method to implement basic planet movement logic
        }

        if (getImage() == null) {
            appear = false; // If the image is null, the object has been removed, so set appear to false
            return;
        }

        if (appear) {
            if (getWorld() instanceof Galaxy) {
                Galaxy galaxy = (Galaxy) getWorld();
                roseAppear = galaxy.getRose();
            }
            // Check if the planet is beyond the right edge of the world
            if (getX() > getWorld().getWidth()) {
                removeRanPlanet();// Remove the random planet if it's beyond the right edge
                return;
            } else if (!canSpawnNext) {
                canSpawnNext = true;

                if (num == 2) {
                    num = 0;
                }
            } else if (getX() > 600 && canSpawnNext && num == 0) {
                num++;
                canSpawnNext = false;
                
                // Check if the world is an instance of Galaxy to access Galaxy-specific functionality
                

                newPlanet = new RandomPlanet();
                ylocation = Greenfoot.getRandomNumber(276) + 150;
                getWorld().addObject(newPlanet, 0, ylocation);
                getWorld().addObject(newPlanet.getHpBar(), 0, Greenfoot.getRandomNumber(276) + 150);
            }
            randomHpBar.moveMe();// Move the health bar with the planet
        }

        checkAndRemove();// Check if the planet should be removed based on health points

        if (appear && firstGenerated) {
            // Check if the world is an instance of Galaxy to access Galaxy-specific functionality
            if (getWorld() instanceof Galaxy) {
                Galaxy galaxy = (Galaxy) getWorld();
                roseAppear = galaxy.getRose();
            }

            // Generate trees or roses based on game configuration
            if (!roseAppear) {
                generateTrees();
            } else if(roseAppear && oneRose != true){
                generateRose();
                speed = 1;// Set speed to 1 when roses appear
                oneRose=true;// Set the flag to true to indicate that a rose has been generated
            }
            firstGenerated = false;// Set the flag to false after initial generation
        }
    }
    
    //private double cos30 = (double)radius * Math.sq
    /**
     * Checks for collisions with asteroids and updates health points.
     * Triggers an explosion effect and adds new asteroids to the world on collision.
     */
    public void checkCollision() {
        List<Asteroids> asteroidsList = getWorld().getObjects(Asteroids.class);
        Asteroids a = (Asteroids) getOneObjectAtOffset((int)-radius, 0, Asteroids.class); // Left
        if (a == null) {
            a = (Asteroids) getOneObjectAtOffset((int) radius, 0, Asteroids.class); // Right
        }else if (a == null) {
            a = (Asteroids) getOneObjectAtOffset(0, -radius, Asteroids.class); // North
        }else if(a == null) {
            a = (Asteroids) getOneObjectAtOffset(0, radius, Asteroids.class); // South
        }else if (a == null) {
            a = (Asteroids) getOneObjectAtOffset((int) (-cos45), (int) (-cos45), Asteroids.class); // WestNorth
            //getWorld().addObject(new Rose(), getX()-(int)cos45, getY() - (int)cos45);
        }else if (a == null) {
            a = (Asteroids) getOneObjectAtOffset((int) (cos45), (int) (cos45), Asteroids.class); // WestSouth
        }else if (a == null) {
            a = (Asteroids) getOneObjectAtOffset((int) (-cos45), (int) (cos45), Asteroids.class); // WestSouth
        }else if (a == null) {
            a = (Asteroids) getOneObjectAtOffset((int) (cos45),(int) (-cos45), Asteroids.class); // WestSouth
        }else if (a == null){
            a = (Asteroids) getOneObjectAtOffset((int) (-cos30),(int) (cos60), Asteroids.class);
        }else if (a == null){
            a = (Asteroids) getOneObjectAtOffset((int) (-cos30),(int) (-cos60), Asteroids.class);
        }else if (a == null){
            a = (Asteroids) getOneObjectAtOffset((int) (-cos60),(int) (cos30), Asteroids.class);
        }else if (a == null){
            a = (Asteroids) getOneObjectAtOffset((int) (-cos60),(int) (-cos30), Asteroids.class);
        }
        if (a != null) {
            a.explode();
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
        cos45 = (double)radius * Math.sqrt(2.0) / 2.0;
        cos30 = (double)radius * Math.sqrt(3.0) / 2.0;
        cos60 = (double)radius * 0.5;
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

