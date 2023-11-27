import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * The {@code Galaxy} class represents the game world in which the Little Prince
 * explores and interacts. It includes asteroids, planets, characters, and other elements.
 * 
 * <p><b>The Little Prince Simulation</b></p>
 * <p>List of features: </p>
 * <ol>
 *  <li> User can set values for the number of asteroids exist, amount of clues the little prince can get from removing a baobab tree, the speed of aseroid, and HP per planet. </li>
 *  <li> The little prince can target to the closest planet, go around a planet, remove baobab trees to gain clues, and fly out of the planet once he completed a full circle and removed all baobab trees, or when the planets disappear from the world. </li>
 *  <li> The fox will be randomly generated on a RandomPlanet; stay at rest or float in space until it touches the little prince and follows him forever. </li>
 *  <li> When the little fox follows the little prince, the speed of removing baobab tree will be as twice as fast as the original speed.
 *  <li> The little fox and the little prince will together do the movements as what the little prince was doing before.
 *  <li> The end screen will present different texts based on the result of the simulation. The user can choose to restart the simulation and reset the values. </li>
 *  <li> The asteroid can cause harm to the planet and little pricnce by directly hitting them. The explosion caused by an asteroid hitting a planet can also cuase harm to little prince. </li>
 * </ol>
 * <p></p>
 * <p>Credits: </p>
 * <ul>
 *  <li>Sound and music: </li>
 *   <ul>
 *    <li> 
 *    <p>Galaxy Music
 *    <p>Artists: Hans Zimmer, Richard Harvey</p>
 *    <p>Title: Trapped Star</p>
 *    <p>Link: https://youtu.be/afSOoPjDIaU?si=M_GgNh61WUnML8Mt</p></li>
 *   </ul> 
 *   <ul> 
 *    <li> 
 *    <p>Failing Page Music
 *    <p>Artists: Hans Zimmer, Richard Harvey </p>
 *    <p>Title: Farewell</p>
 *    <p>Link: https://youtu.be/wMprqGYGP2c?si=PBd2JGwk8DZBxAD6</p></li>
 *   </ul>
 *   <ul>
 *    <li>
 *    <p>Set Value Page Music
 *    <p>Artists: Hans Zimmer, Richard Harvey</p>
 *    <p>Title: Ascending</p>
 *    <p>Link: https://youtu.be/h1TsXfLDHrA?si=R4ghgY3tGaTa4Xw4</p></li>
 *   </ul>
 *   <ul>
 *    <li>
 *    <p>Intro Animation Music
 *    <p>Artists: Hans Zimmer, Richard Harvey</p>
 *    <p>Title: Ascending</p>
 *    <p>Link: https://youtu.be/h1TsXfLDHrA?si=R4ghgY3tGaTa4Xw4</p></li>
 *   </ul>
 *   <ul> 
 *    <li>
 *    <p>Explosion Music
 *    <p>Artist: Pixabay</p>
 *    <p>Title: RussianMeteorite_SFX</p>
 *    <p>Link: https://pixabay.com/sound-effects/russianmeteorite-sfx-76195/</p></li>
 *   </ul>
 *   <ul>
 *    <li>
 *    <p>Success Page Music
 *    <p>Artists: Hans Zimmer, Richard Harvey
 *    <p>Title: Recovery</p>
 *    <p>Link: https://youtu.be/KjUYaIxuz_Y?si=ZT8GiagzczfGzXp8</p></li>
 *   </ul>
 *  <li>Art and graphic: </li>
 *   <ul>
 *    <li><p>Artist: Elthen</p>
 *    <p>Title: 2D Pixel Art Fox Sprites</p>
 *    <p>Link: https://elthen.itch.io/2d-pixel-art-fox-spritesM</p></li>
 *   </ul>
 *   <ul>
 *    <li><p>Artist: FoozleCC</p>
 *    <p>Title: Void - Environment Pack (1.0)</p>
 *    <p>Link: https://foozlecc.itch.io/void-environment-pack</p></li>
 *   </ul>
 *  <li>Code: </li>
 *   <ul>
 *    <li><p> Programmer: Jordan Cohen </p>
 *    <p>classes: SuperSmoothMover, SuperStatBar</p>
 *    <p>methods: targetClosestPlanet</p></li>
 *   </ul>
 * </ul>
 * <p></p>
 * <p>Known bug£º</p>
 * <ol>
 *  <li>Asteroids sometimes hit the planet without exploding.</li>
 *  <li>After clicking the start button on the title screen, it will take a while to load to the introAnimation.</li>
 * </ol>
 * @authors: Angela Gao, Jiayi Li, Zhiyu (Jennifer) Zhou 
 * @version: November 24
 * 
 */

public class Galaxy extends World
{
    private static int numOfAsteroids;
    private static int amountOfClues;
    private static int asteroidSpeed;
    private static int hpPerPlanet;
    private int numOfPlanets = 1; 
    private SuperStatBar clueBar;
    private int clueCount = 0;
    private GreenfootImage background = new GreenfootImage("galaxyBackground.png");
    public static final int Rdecrease=15;

    // Arrays for Little Prince animation frames
    private GreenfootImage[] TLPwalk = new GreenfootImage[8];
    private GreenfootImage[] TLPfly = new GreenfootImage[6];
    private GreenfootImage[] TLPflyInverted = new GreenfootImage[6];
    private GreenfootImage[] TLPdig = new GreenfootImage[9];

    // Fox animation frames and related variables
    private int countFox = 0;
    private boolean hasFox = true;
    private GreenfootImage[] foxRun = new GreenfootImage[8];
    private GreenfootImage[] foxFly = new GreenfootImage[5];
    private GreenfootImage[] foxFlyInverted = new GreenfootImage[5];
    private GreenfootImage[] foxDig = new GreenfootImage[11];

    // Initial y-coordinate for planet placement
    private int y = Greenfoot.getRandomNumber(276) + 150;

    /**
     * Music credit
     * Artists: Hans Zimmer, Richard Harvey
     * Title: Trapped Stars
     * Link: https://youtu.be/afSOoPjDIaU?si=M_GgNh61WUnML8Mt
     */
    public static final GreenfootSound galaxyMusic = new GreenfootSound("galaxyMusic.mp3");

    private boolean roseAdded = false;// Flag to track whether a rose has been added 
    /**
     * Constructor for objects of class Galaxy.
     * 
     * @param numOfAsteroids The number of asteroids in the world.
     * @param amountOfClues The initial amount of clues.
     * @param asteroidSpeed The speed of asteroids.
     * @param hpPerPlanet The initial HP (health points) per planet.
     */
    public Galaxy(int numOfAsteroids, int amountOfClues, int asteroidSpeed, int hpPerPlanet)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 576, 1, false);
        this.numOfAsteroids = numOfAsteroids;
        this.amountOfClues=amountOfClues;
        this.asteroidSpeed=asteroidSpeed;
        this.hpPerPlanet=hpPerPlanet;
        background.scale(1024, 576);
        setBackground(background);
        setPaintOrder (SuperStatBar.class, Explosion.class, Asteroids.class, Rose.class, Moving.class, BaobabTree.class, Planet.class); 
        clueBar = new SuperStatBar(120, clueCount, null, 110, 10, 0, Color.RED, Color.GREEN, false, Color.BLACK, 1);
        addObject(clueBar, getWidth() - 60, 100);
        addObject(new Roseicon(), getWidth() - 60, 50);
        galaxyMusic.setVolume(50);
        prepare();
    }
    /**
     * Method called when the simulation starts, plays background music.
     */
    public void started(){
        galaxyMusic.playLoop(); 
    }
    /**
     * Method called when the simulation stops, pauses background music.
     */
    public void stopped(){
        galaxyMusic.pause();
    }

    /**
     * Prepares the initial configuration of the game world, including asteroids, characters, and planets.
     * Credit: Method created by Jiayi Li, modified by Zhiyu (Jennifer) Zhou
     */
    private void prepare() { 
        // Add asteroids to the world
        for (int i = 0; i < this.numOfAsteroids; i++) {
            addObject(new Asteroids(), getAsteroidsX(), getAsteroidsY());
        }
    
        // Initialize the Little Prince character
        LittlePrince TLP = new LittlePrince(TLPwalk, TLPfly, TLPdig, TLPflyInverted);
        addObject(TLP, 512, 300); // Set initial position for the Little Prince
        TLP.prepareAnimation(TLPwalk, "walkAnimation/walk");
        TLP.prepareAnimation(TLPfly, "flyAnimation/fly");
        TLP.prepareAnimation(TLPdig, "digAnimation/dig");
        TLP.prepareAnimation(TLPflyInverted, "flyAnimation/fly");
        TLP.flipVertically(TLPflyInverted);
    
        // Add a bird character to the world
        addObject(new Bird(), 100, 200);
    
        // Add a home planet to the center of the world
        addObject(new HomePlanet(), getWidth() / 2, getHeight() / 2);
    
        // Add a random planet to the left side of the world
        addObject(new RandomPlanet(), 0, y);
    
        // Generate a fox character based on random conditions
        if (Greenfoot.getRandomNumber(1) == 0 && hasFox == true && countFox < 1){
            generateFox();
            countFox++;
            hasFox = false;
        } else {
            countFox = 1;
            hasFox = false;
        }
    }
    
    /**
     * Generates a fox character in the world based on random conditions.
     */
    public void generateFox() {
        if (Greenfoot.getRandomNumber(1) == 0){
            Fox fox = new Fox(foxRun, foxFly, foxDig, foxFlyInverted);
            /**
             * Image credit
             * Artist: Elthen
             * Title: 2D Pixel Art Fox Sprites
             * Link: https://elthen.itch.io/2d-pixel-art-fox-sprites
             */
            fox.prepareAnimation(foxRun, "foxRun/run", fox.getImage().getWidth()*4, fox.getImage().getHeight()*4);
            fox.prepareAnimation(foxFly, "foxFly/fly", fox.getImage().getWidth()*4, fox.getImage().getHeight()*4);
            fox.prepareAnimation(foxFlyInverted, "foxFly/fly", fox.getImage().getWidth()*4, fox.getImage().getHeight()*4);
            fox.prepareAnimation(foxDig, "foxDig/dig", fox.getImage().getWidth()*4, fox.getImage().getHeight()*4);
            fox.flipHorizontally(foxRun);
            fox.flipHorizontally(foxDig);
            fox.flipVertically(foxFlyInverted);
            addObject(fox, 0, y);
        }
    }
    
    /**
     * Retrieves a random X-coordinate within the world boundaries.
     * 
     * @return A random X-coordinate.
     */
    public int getAsteroidsX(){
        int getAsteroidsX = Greenfoot.getRandomNumber(getWidth());
        return getAsteroidsX;
    }
    
    /**
     * Retrieves a random Y-coordinate within the world boundaries.
     * 
     * @return A random Y-coordinate.
     */
    public int getAsteroidsY(){
        int getAsteroidsY = Greenfoot.getRandomNumber(getHeight());
        return getAsteroidsY;
    }

    /**
     * Calculates the distance between two actors.
     * 
     * @param a The first actor.
     * @param b The second actor.
     * @return The distance between the two actors.
     */
    public static double getDistance (Actor a, Actor b){
        double distanceBetween = Math.hypot (a.getX() - b.getX(), a.getY() - b.getY());
        return distanceBetween;
    }
    /**
     * Method called during each frame update.
     */
    public void act() {
        started();
        if (clueCount == 120 && !roseAdded) {
            roseAdded = true;  // Set the flag to true so that you don't add more Roses
        }
    }
    /**
     * Retrieves the number of asteroids in the world.
     * 
     * @return The number of asteroids.
     */
    public static int getNumOfAsteroids(){
        return numOfAsteroids;
    }

    /**
     * Gets the initial amount of clues configured for the world.
     * 
     * @return The initial amount of clues.
     */
    public static int getAmountOfClues(){
        return amountOfClues;
    }
    /**
     * Retrieves the speed of asteroids in the world.
     * 
     * @return The speed of asteroids.
     */
    public static int asteroidSpeed(){
        return asteroidSpeed;
    }
    /**
     * Retrieves the initial health points per planet configured for the world.
     * 
     * @return The initial health points per planet.
     */
    public static int hpPerPlanet(){
        return hpPerPlanet;
    }

    /**
     * Changes the amount of clues and updates the clue count.
     * 
     * @param amountOfClues The amount of clues to add or subtract.
     */
    public void changeClue(int amount){
        //this.amountOfClues=amountOfClues;
        clueCount += amount;
        clueBar.update(clueCount);
    }

    /**
     * Checks if a rose has been added to the world.
     * 
     * @return True if a rose has been added, false otherwise.
     */
    public boolean getRose(){
        return roseAdded;
    }
}
