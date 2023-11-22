import greenfoot.*;

public class SetValuePage extends World {
    public static int numOfAsteroids;
    public static int amountOfClues;
    public static int asteroidSpeed;
    public static int hpPerPlanet;

    private ImageButton plusAsteroidsButton;
    private ImageButton minusAsteroidsButton;
    private ImageButton plusCluesButton;
    private ImageButton minusCluesButton;
    private ImageButton plusSpeedButton;
    private ImageButton minusSpeedButton;
    private ImageButton plusHPButton;
    private ImageButton minusHPButton;
    private ImageButton startButton;

    private TextButton cluesLabel;
    private TextButton speedLabel;
    private TextButton hpLabel;
    private TextButton asteroidsLabel;

    private GreenfootImage setValueBG = new GreenfootImage("setValuePage.png");

    public SetValuePage() {
        super(1024, 576, 1);
        setValueBG.scale(1024, 576);
        setBackground(setValueBG);
        prepare();
    }

    public void prepare() {
        numOfAsteroids = 1;
        amountOfClues = 10;
        asteroidSpeed = 1;
        hpPerPlanet = 90;
        //plusAsteroidsButton = new ImageButton("plusButton.png", "plusButton.png", "plus");
        //addObject(plusAsteroidsButton, 849, 171);
        // 创建显示数量的 TextButton
        asteroidsLabel = new TextButton("Number of Asteroids: " + numOfAsteroids, 40);
        addObject(asteroidsLabel, 515, 171);

        // 创建加减号按钮和显示数量的 TextButton
        plusAsteroidsButton = new ImageButton("plusButton.png", "plusButton.png", "plus");
        addObject(plusAsteroidsButton, 849, 171);
        minusAsteroidsButton = new ImageButton("minusButton.png", "minusButton.png", "minus");
        addObject(minusAsteroidsButton, 181, 171);
        cluesLabel = new TextButton("Amount of Clues: " + amountOfClues, 40);
        addObject(cluesLabel, 515, 261);
        // ...
        
        plusCluesButton = new ImageButton("plusButton.png", "plusButton.png", "plus");
        addObject(plusCluesButton, 849, 261);
        minusCluesButton = new ImageButton("minusButton.png", "minusButton.png", "minus");
        addObject(minusCluesButton, 181, 261);
        speedLabel = new TextButton("Asteroid Speed: " + asteroidSpeed, 40);
        addObject(speedLabel, 515, 351);
        // ...
        
        plusSpeedButton = new ImageButton("plusButton.png", "plusButton.png", "plus");
        addObject(plusSpeedButton, 849, 351);
        minusSpeedButton = new ImageButton("minusButton.png", "minusButton.png", "minus");
        addObject(minusSpeedButton, 181, 351);
        hpLabel = new TextButton("HP per Planet: " + hpPerPlanet, 40);
        addObject(hpLabel, 515, 441);
        // ...
        
        plusHPButton = new ImageButton("plusButton.png", "plusButton.png", "plus");
        addObject(plusHPButton, 849, 441);
        minusHPButton = new ImageButton("minusButton.png", "minusButton.png", "minus");
        addObject(minusHPButton, 181, 441);

        // 创建开始按钮
        startButton = new ImageButton("startButton.png","startButtonPressed.png","start");
        addObject(startButton, getWidth() / 2, 510);
    }

    public void act() {
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
        }
    }

    private void updateLabel(ImageButton plusButton, ImageButton minusButton, TextButton label, String labelText, int value) {
        label.updateMe(labelText + value);
    }
}

