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
    
    private GreenfootSound setValueMusic;
    private int musicCount = 0;
    
    private GreenfootImage setValueBG = new GreenfootImage("setValuePage.png");
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

    public void prepare() {
        numOfAsteroids = 1;
        amountOfClues = 10;
        asteroidSpeed = 1;
        hpPerPlanet = 90;

        // ������ʾ������ TextButton
        asteroidsLabel = new TextButton("Number of Asteroids: " + numOfAsteroids, 40);
        addObject(asteroidsLabel, 515, 171);

        // �����Ӽ��Ű�ť����ʾ������ TextButton
        plusAsteroidsButton = new TextButton("+", 40);
        addObject(plusAsteroidsButton, 849, 171);
        minusAsteroidsButton = new TextButton("-", 40);
        addObject(minusAsteroidsButton, 181, 171);

        // ������ʾ������ TextButton
        cluesLabel = new TextButton("Amount of Clues: " + amountOfClues, 40);
        addObject(cluesLabel, 515, 261);

        // �����Ӽ��Ű�ť����ʾ������ TextButton
        plusCluesButton = new TextButton("+", 40);
        addObject(plusCluesButton, 849, 261);
        minusCluesButton = new TextButton("-", 40);
        addObject(minusCluesButton, 181, 261);

        // ������ʾ������ TextButton
        speedLabel = new TextButton("Asteroid Speed: " + asteroidSpeed, 40);
        addObject(speedLabel, 515, 351);

        // �����Ӽ��Ű�ť����ʾ������ TextButton
        plusSpeedButton = new TextButton("+", 40);
        addObject(plusSpeedButton, 849, 351);
        minusSpeedButton = new TextButton("-", 40);
        addObject(minusSpeedButton, 181, 351);

        // ������ʾ������ TextButton
        hpLabel = new TextButton("HP per Planet: " + hpPerPlanet, 40);
        addObject(hpLabel, 515, 441);

        // �����Ӽ��Ű�ť����ʾ������ TextButton
        plusHPButton = new TextButton("+", 40);
        addObject(plusHPButton, 849, 441);
        minusHPButton = new TextButton("-", 40);
        addObject(minusHPButton, 181, 441);

        // ������ʼ��ť
        startButton = new TextButton("Start", 40);
        addObject(startButton, getWidth() / 2, 510);
    }
    
    public void started(){
        setValueMusic.playLoop(); 
    }

    public void stopped(){
        setValueMusic.pause();
    }

    public void act() {
        started();
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
                amountOfClues+=10;
                updateLabel(plusCluesButton, minusCluesButton, cluesLabel,"Amount of Clues: ", amountOfClues);
            }
        } else if (Greenfoot.mousePressed(minusCluesButton)) {
            if (amountOfClues > 10) {
                amountOfClues-=10;
                updateLabel(plusCluesButton, minusCluesButton,cluesLabel, "Amount of Clues: ", amountOfClues);
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

    private void updateLabel(TextButton plusButton, TextButton minusButton, TextButton label, String labelText, int value) {
        label.updateMe(labelText + value);
    }
}

