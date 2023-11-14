import greenfoot.*;

public class SetValuePage extends World {
    public static int numOfAsteroids;
    public static int amountOfClues;
    public static int asteroidSpeed;
    public static int hpPerPlanet;

    private TextButton plusAsteroidsButton;
    private TextButton minusAsteroidsButton;
    private TextButton plusCluesButton;
    private TextButton minusCluesButton;
    private TextButton plusSpeedButton;
    private TextButton minusSpeedButton;
    private TextButton plusHPButton;
    private TextButton minusHPButton;
    private TextButton startButton;
    
    private TextButton cluesLabel;
    private TextButton speedLabel; // 声明为成员变量
    private TextButton hpLabel;    // 声明为成员变量
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
        amountOfClues = 1;
        asteroidSpeed = 100;
        hpPerPlanet = 100;

        // 创建显示数量的 TextButton
        asteroidsLabel = new TextButton("Number of Asteroids: " + numOfAsteroids, 50);
        addObject(asteroidsLabel, 515, 141);

        // 创建加减号按钮和显示数量的 TextButton
        plusAsteroidsButton = new TextButton("+", 50);
        addObject(plusAsteroidsButton, 181, 141);
        minusAsteroidsButton = new TextButton("-", 50);
        addObject(minusAsteroidsButton, 849, 141);

        // 创建显示数量的 TextButton
        cluesLabel = new TextButton("Amount of Clues: " + amountOfClues, 50);
        addObject(cluesLabel, 515, 231);

        // 创建加减号按钮和显示数量的 TextButton
        plusCluesButton = new TextButton("+", 50);
        addObject(plusCluesButton, 181, 231);
        minusCluesButton = new TextButton("-", 50);
        addObject(minusCluesButton, 849, 231);

        // 创建显示数量的 TextButton
        speedLabel = new TextButton("Asteroid Speed: " + asteroidSpeed, 50);
        addObject(speedLabel, 515, 321);

        // 创建加减号按钮和显示数量的 TextButton
        plusSpeedButton = new TextButton("+", 50);
        addObject(plusSpeedButton, 181, 321);
        minusSpeedButton = new TextButton("-", 50);
        addObject(minusSpeedButton, 849, 321);

        // 创建显示数量的 TextButton
        hpLabel = new TextButton("HP per Planet: " + hpPerPlanet, 50);
        addObject(hpLabel, 515, 411);

        // 创建加减号按钮和显示数量的 TextButton
        plusHPButton = new TextButton("+", 50);
        addObject(plusHPButton, 181, 411);
        minusHPButton = new TextButton("-", 50);
        addObject(minusHPButton, 849, 411);

        // 创建开始按钮
        startButton = new TextButton("Start", 50);
        addObject(startButton, getWidth() / 2, 500);
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
            if (amountOfClues < 3) {
                amountOfClues++;
                updateLabel(plusCluesButton, minusCluesButton, cluesLabel,"Amount of Clues: ", amountOfClues);
            }
        } else if (Greenfoot.mousePressed(minusCluesButton)) {
            if (amountOfClues > 1) {
                amountOfClues--;
                updateLabel(plusCluesButton, minusCluesButton,cluesLabel, "Amount of Clues: ", amountOfClues);
            }
        } else if (Greenfoot.mousePressed(plusSpeedButton)) {
            if (asteroidSpeed < 300) {
                asteroidSpeed += 50;
                updateLabel(plusSpeedButton, minusSpeedButton, speedLabel, "Asteroid Speed: ", asteroidSpeed);
            }
        } else if (Greenfoot.mousePressed(minusSpeedButton)) {
            if (asteroidSpeed > 100) {
                asteroidSpeed -= 50;
                updateLabel(plusSpeedButton, minusSpeedButton, speedLabel, "Asteroid Speed: ", asteroidSpeed);
            }
        } else if (Greenfoot.mousePressed(plusHPButton)) {
            if (hpPerPlanet < 300) {
                hpPerPlanet += 50;
                updateLabel(plusHPButton, minusHPButton, hpLabel, "HP per Planet: ", hpPerPlanet);
            }
        } else if (Greenfoot.mousePressed(minusHPButton)) {
            if (hpPerPlanet > 100) {
                hpPerPlanet -= 50;
                updateLabel(plusHPButton, minusHPButton, hpLabel, "HP per Planet: ", hpPerPlanet);
            }
        } else if (Greenfoot.mousePressed(startButton)) {
            Galaxy galaxy = new Galaxy(numOfAsteroids, amountOfClues, asteroidSpeed, hpPerPlanet);
            Greenfoot.setWorld(galaxy);
        }
    }

    private void updateLabel(TextButton plusButton, TextButton minusButton, TextButton label, String labelText, int value) {
        label.updateMe(labelText + value);
    }
}

