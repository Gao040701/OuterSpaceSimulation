import greenfoot.*;

/**
 * The Asteroids class represents asteroids in the game world. 
 * Asteroids move with a random speed in both x and y directions, and their 
 * appearance and size are randomized. They also have a periodic change in speed
 * and rotation.
 * 
 * Usage:
 * <li>Create an instance of Asteroids in the world to represent asteroids.</li>
 * 
 * @author Jiayi Li
 * @version November 2023
 */
public class Asteroids extends SuperSmoothMover {
    private int xSpeed;
    private int ySpeed;
    private int count;
    /**
     * Image credit
     * Artist: FoozleCC
     * Title: Void - Environment Pack (1.0)
     * Link: https://foozlecc.itch.io/void-environment-pack
     */
    private GreenfootImage asteroid = new GreenfootImage("asteroid.png");
    private int worldWidth = 1024, worldHeight = 576;

    /**
     * Constructs an Asteroids object.
     * The initial x and y speeds, as well as the image size, are randomized.
     */
    public Asteroids() {
        xSpeed = Greenfoot.getRandomNumber(4) + Galaxy.asteroidSpeed(); // x-direction speed between 1 and 3
        ySpeed = Greenfoot.getRandomNumber(3) - Galaxy.asteroidSpeed(); // y-direction speed between -1 and 1
        count = 0; // counter initialized to 0
        asteroid.scale(75 + Greenfoot.getRandomNumber(30), 60 + Greenfoot.getRandomNumber(30));
        setImage(asteroid);
    }
    
    /**
     * Act method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     * Handles the movement, relocation, and periodic speed change of asteroids.
     */
    public void act() {
        // Move the asteroid based on x and y speeds
        setLocation(getX() + xSpeed, getY() + ySpeed);

        // If the asteroid moves to the screen edge, relocate it to the other side
        reLocation();

        // Change speed periodically
        randomSpeed();
    }

    /**
     * Sets a random rotation for the asteroid.
     */
    private void setRandomRotation() {
        double rotation = Greenfoot.getRandomNumber(360); // Generate a random angle between 0 and 359
        setRotation(rotation);
    }

    /**
     * Relocates the asteroid to the opposite side if it reaches the screen edge.
     */
    private void reLocation() {
        if (atEdge()) {
            setLocation(-10, Greenfoot.getRandomNumber(400) + 100);
        }
    }

    /**
     * Checks if the asteroid is at the screen edge.
     * 
     * @return true if the asteroid is at the edge, false otherwise.
     */
    public boolean atEdge() {
        return getX() > worldWidth || getY() > worldHeight || getY() < 0;
    }

    /**
     * Changes the speed of the asteroid periodically.
     */
    private void randomSpeed() {
        if (count == 100) {
            xSpeed = Greenfoot.getRandomNumber(4) + 1; // x-direction speed between 1 and 3
            ySpeed = Greenfoot.getRandomNumber(3) - 1; // y-direction speed between -1 and 1
            setRandomRotation();
            count = 0;
        }
        if (count < 100) {
            count++;
        }
    }
    
    public void explode(){
        Explosion explosion = new Explosion(5); // Create an explosion object
        getWorld().addObject(explosion, getX(), getY()); // Add the explosion to the world
    }
}