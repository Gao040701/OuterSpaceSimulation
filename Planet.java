import greenfoot.*;

/**
 * The abstract class Planet represents planets in the game world.
 * Planets rotate on their own and move to the right.
 * Specific types of planets should extend this class and implement the abstract
 * methods for collision checking.
 * 
 * Usage:
 * - Create subclasses of Planet for different types of planets.
 * - Override checkCollision() method to implement collision logic for each planet type.
 * 
 * @author Jiayi Li
 * @version November 2023
 */
public abstract class Planet extends SuperSmoothMover {
    protected int radius;       // Radius of the planet
    protected double speed;     // Speed at which the planet moves
    protected int totalHP;      // Total health points of the planet
    protected int decreaseHP;   // Amount of health points to decrease on collision
    protected boolean appear;   // Flag indicating if the planet should appear in the world
    protected boolean visited;  // Flag indicating if the planet has been visited

    /**
     * Act method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     * Moves the planet to the right if it has appeared and calls the checkCollision() method.
     */
    public void act() {
        if (appear) {
            move(speed);
            checkCollision();
        }
    }

    /**
     * Constructor for the Planet class.
     * Initializes the appear flag to true.
     */
    public Planet() {
        appear = true;
    }

    /**
     * Abstract method to be implemented by subclasses.
     * Checks for collisions with other objects.
     */
    public abstract void checkCollision();

    /**
     * Gets the radius of the planet.
     * 
     * @return The radius of the planet.
     */
    public int getRadius() {
        return getImage().getHeight() / 2;
    }

    public int getPlanetX(){
        return getX();
    }

    public int getPlanetY(){
        return getY();
    }
    
    /**
     * Gets the speed of the planet.
     * 
     * @return The speed of the planet.
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Checks if the planet has been visited.
     * 
     * @return true if the planet has been visited, false otherwise.
     */
    public boolean hasVisited() {
        return visited;
    }
}
