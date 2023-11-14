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
    private TextButton speedLabel; // ����Ϊ��Ա����
    private TextButton hpLabel;    // ����Ϊ��Ա����
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

        // ������ʾ������ TextButton
        asteroidsLabel = new TextButton("Number of Asteroids: " + numOfAsteroids, 50);
        addObject(asteroidsLabel, 515, 141);

        // �����Ӽ��Ű�ť����ʾ������ TextButton
        plusAsteroidsButton = new TextButton("+", 50);
        addObject(plusAsteroidsButton, 181, 141);
        minusAsteroidsButton = new TextButton("-", 50);
        addObject(minusAsteroidsButton, 849, 141);

        // ������ʾ������ TextButton
        cluesLabel = new TextButton("Amount of Clues: " + amountOfClues, 50);
        addObject(cluesLabel, 515, 231);

        // �����Ӽ��Ű�ť����ʾ������ TextButton
        plusCluesButton = new TextButton("+", 50);
        addObject(plusCluesButton, 181, 231);
        minusCluesButton = new TextButton("-", 50);
        addObject(minusCluesButton, 849, 231);

        // ������ʾ������ TextButton
        speedLabel = new TextButton("Asteroid Speed: " + asteroidSpeed, 50);
        addObject(speedLabel, 515, 321);

        // �����Ӽ��Ű�ť����ʾ������ TextButton
        plusSpeedButton = new TextButton("+", 50);
        addObject(plusSpeedButton, 181, 321);
        minusSpeedButton = new TextButton("-", 50);
        addObject(minusSpeedButton, 849, 321);

        // ������ʾ������ TextButton
        hpLabel = new TextButton("HP per Planet: " + hpPerPlanet, 50);
        addObject(hpLabel, 515, 411);

        // �����Ӽ��Ű�ť����ʾ������ TextButton
        plusHPButton = new TextButton("+", 50);
        addObject(plusHPButton, 181, 411);
        minusHPButton = new TextButton("-", 50);
        addObject(minusHPButton, 849, 411);

        // ������ʼ��ť
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

