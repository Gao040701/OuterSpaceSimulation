import greenfoot.*;

/**
 * The {@code SetValuePage} class represents the configuration page where the player can set
 * initial values for the game, such as the number of asteroids, amount of clues, asteroid speed,
 * and HP per planet.
 * 
 * This class includes buttons to increment or decrement these values, as well as a start button
 * to initiate the game with the configured settings.
 * 
 * @author Jiayi Li
 * @version November 2023
 */
public class SetValuePage extends World {
    // Instance variables for initial game settings
    private int numOfAsteroids;
    private int amountOfClues;
    private int asteroidSpeed;
    private int hpPerPlanet;

    // Buttons for setting values
    private ImageButton plusAsteroidsButton;
    private ImageButton minusAsteroidsButton;
    private ImageButton plusCluesButton;
    private ImageButton minusCluesButton;
    private ImageButton plusSpeedButton;
    private ImageButton minusSpeedButton;
    private ImageButton plusHPButton;
    private ImageButton minusHPButton;
    private ImageButton startButton;

    // Labels to display the current values
    private TextButton cluesLabel;
    private TextButton speedLabel;
    private TextButton hpLabel;
    private TextButton asteroidsLabel;

    private GreenfootSound setValueMusic;
    private int musicCount = 0;
    
    // Background image
    private GreenfootImage setValueBG = new GreenfootImage("setValuePage.png");

    /**
     * Constructor for objects of class SetValuePage.
     */
    public SetValuePage() {
        super(1024, 576, 1);
        setValueBG.scale(1024, 576);
        setBackground(setValueBG);
        /**
         * Music credit: Hans Zimmer
         * Title: Ascending
         */
        setValueMusic = new GreenfootSound("setValueMusic.mp3");
        setValueMusic.setVolume(50);
        prepare();
    }

    /**
     * Prepares the initial state of the world by setting up buttons, labels, and default values.
     */
    public void prepare() {
        numOfAsteroids = 1;
        amountOfClues = 10;
        asteroidSpeed = 1;
        hpPerPlanet = 90;

        // Create labels to display the current values
        asteroidsLabel = new TextButton("Number of Asteroids: " + numOfAsteroids, 40);
        addObject(asteroidsLabel, 515, 191);

        cluesLabel = new TextButton("Amount of Clues: " + amountOfClues, 40);
        addObject(cluesLabel, 515, 271);

        speedLabel = new TextButton("Asteroid Speed: " + asteroidSpeed, 40);
        addObject(speedLabel, 515, 351);

        hpLabel = new TextButton("HP per Planet: " + hpPerPlanet, 40);
        addObject(hpLabel, 515, 431);

        // Create buttons to increment or decrement values
        plusAsteroidsButton = new ImageButton("plusButton.png", "plusButton.png", "plus", 80, 80);
        addObject(plusAsteroidsButton, 839, 191);
        minusAsteroidsButton = new ImageButton("minusButton.png", "minusButton.png", "minus", 80, 80);
        addObject(minusAsteroidsButton, 191, 191);

        plusCluesButton = new ImageButton("plusButton.png", "plusButton.png", "plus", 80, 80);
        addObject(plusCluesButton, 839, 271);
        minusCluesButton = new ImageButton("minusButton.png", "minusButton.png", "minus", 80, 80);
        addObject(minusCluesButton, 191, 271);

        plusSpeedButton = new ImageButton("plusButton.png", "plusButton.png", "plus", 80, 80);
        addObject(plusSpeedButton, 839, 351);
        minusSpeedButton = new ImageButton("minusButton.png", "minusButton.png", "minus", 80, 80);
        addObject(minusSpeedButton, 191, 351);

        plusHPButton = new ImageButton("plusButton.png", "plusButton.png", "plus", 80, 80);
        addObject(plusHPButton, 839, 431);
        minusHPButton = new ImageButton("minusButton.png", "minusButton.png", "minus", 80, 80);
        addObject(minusHPButton, 191, 431);

        // Create the start button
        startButton = new ImageButton("startButton.png", "startButtonPressed.png", "start", 80, 50);
        addObject(startButton, getWidth() / 2, 510);
    }

    public void started(){
        setValueMusic.playLoop(); 
    }

    public void stopped(){
        setValueMusic.pause();
    }
    
    /**
     * Handles user input and updates the values accordingly.
     */
    public void act() {
        started();
        // Increment or decrement values based on button clicks  
        if (Greenfoot.mousePressed(plusAsteroidsButton)) {
            if (numOfAsteroids < 6) {
                numOfAsteroids++;
                updateLabel(plusAsteroidsButton, minusAsteroidsButton, asteroidsLabel, "Number of Asteroids: ", numOfAsteroids);
            }
        } else if (Greenfoot.mousePressed(minusAsteroidsButton)) {
            if (numOfAsteroids > 1) {
                numOfAsteroids--;
                updateLabel(plusAsteroidsButton, minusAsteroidsButton, asteroidsLabel, "Number of Asteroids: ", numOfAsteroids);
            }
        } else if (Greenfoot.mousePressed(plusCluesButton)) {
            if (amountOfClues < 30) {
                amountOfClues += 10;
                updateLabel(plusCluesButton, minusCluesButton, cluesLabel, "Amount of Clues: ", amountOfClues);
            }
        } else if (Greenfoot.mousePressed(minusCluesButton)) {
            if (amountOfClues > 10) {
                amountOfClues -= 10;
                updateLabel(plusCluesButton, minusCluesButton, cluesLabel, "Amount of Clues: ", amountOfClues);
            }
        } else if (Greenfoot.mousePressed(plusSpeedButton)) {
            if (asteroidSpeed < 2) {
                asteroidSpeed += 1;
                updateLabel(plusSpeedButton, minusSpeedButton, speedLabel, "Asteroid Speed: ", asteroidSpeed);
            }
        } else if (Greenfoot.mousePressed(minusSpeedButton)) {
            if (asteroidSpeed > 1) {
                asteroidSpeed -= 1;
                updateLabel(plusSpeedButton, minusSpeedButton, speedLabel, "Asteroid Speed: ", asteroidSpeed);
            }
        } else if (Greenfoot.mousePressed(plusHPButton)) {
            if (hpPerPlanet < 180) {
                hpPerPlanet += 15;
                updateLabel(plusHPButton, minusHPButton, hpLabel, "HP per Planet: ", hpPerPlanet);
            }
        } else if (Greenfoot.mousePressed(minusHPButton)) {
            if (hpPerPlanet > 90) {
                hpPerPlanet -= 15;
                updateLabel(plusHPButton, minusHPButton, hpLabel, "HP per Planet: ", hpPerPlanet);
            }
        } else if (Greenfoot.mousePressed(startButton)) {
            Galaxy galaxy = new Galaxy(numOfAsteroids, amountOfClues, asteroidSpeed, hpPerPlanet);
            Greenfoot.setWorld(galaxy);
            stopped();
        }
    }   


    /**
     * Updates the displayed label text with the current value.
     * 
     * @param plusButton The button to increment the value.
     * @param minusButton The button to decrement the value.
     * @param label The label displaying the value.
     * @param labelText The text prefix for the label.
     * @param value The current value to be displayed.
     */
    private void updateLabel(ImageButton plusButton, ImageButton minusButton, TextButton label, String labelText, int value) {
        label.updateMe(labelText + value);
    }
}
