import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

/**
 * The LittlePrince class represents the character that walks around planets and jumps out of planets
 * when all the baobab trees are destroyed. It extends the Moving class and has additional behavior
 * related to health points, collision detection, and rotation.
 * <p>
 * @author Zhiyu (Jennifer) Zhou
 * @modified by Angela Gao
 * @version v1.0
 */
public class LittlePrince extends Moving
{
    private SuperStatBar princeHpBar;
    private int totalHP;
    private int decreaseHP;
    private boolean appear;

    private int index, count = 0, degree;
    private double speed;
    private final int COUNT_NUM = 7;
    private Planet targetPlanet;
    private RandomPlanet randomPlanet;
    private Planet planet;
    private HitBox box;
    protected boolean rotateDetection = false;
    private double angle = 0;
    private ArrayList<Planet> planets;
    protected LittlePrince littlePrince;
    private int mySpeed = 1;
    private int passCount = 0;
    private boolean justPassed = false;
    private boolean isStaying = false;

    private GreenfootImage[] walk;
    private GreenfootImage[] fly;
    private GreenfootImage[] dig;
    private GreenfootImage[] flyInverted;
    /**
     * Act - do whatever the LittlePrince wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        checkCollisionAsteriods();
        randomPlanet = (RandomPlanet) getOneIntersectingObject(RandomPlanet.class);
        planet = (Planet) getOneIntersectingObject(Planet.class);
        box = (HitBox) getOneIntersectingObject(HitBox.class);
        //System.out.println("PRINT!");
        
        if (targetPlanet != null && targetPlanet.getWorld() == null){
                targetPlanet = null;
        }
        if (targetPlanet == null || Galaxy.getDistance (this, targetPlanet) > 20){
            targetClosestPlanet();
        }
        
        if (checkHitPlanet()){
            rotateDetection = true;
            rotate();
        }else {
            passCount = 0;
            rotateDetection = false;
            isStaying = false;
            rotateImage(90);
            if (targetPlanet != null){
                moveTowardPlanet();
                //System.out.println("TOWARDS!");
                if (getRotation() < 270 && getRotation() > 90){
                    animate(flyInverted);
                }else animate(fly);
            }else{
                moveRandomly();
                if (getRotation() < 270 && getRotation() > 90){
                    animate(flyInverted);
                }else animate(fly);
            }
            //targetClosestPlanet();
        }
    }

    private void targetClosestPlanet(){
        double closestTargetDistance = 0;
        double distanceToActor;
        planets = (ArrayList<Planet>)getObjectsInRange(500, Planet.class);
        
        for (int i = 0; i < planets.size(); i++){
            if (planets.get(i).hasVisited()){
                planets.remove(i);
            }
        }

        if (planets.size() == 0){
            planets = (ArrayList<Planet>)getObjectsInRange(160, Planet.class);
        }

        if (planets.size() > 0){
            targetPlanet = planets.get(0);
            closestTargetDistance = getDistance (this, targetPlanet);

            for (Planet o : planets){
                distanceToActor = getDistance(this, o);
                if (distanceToActor < closestTargetDistance){
                    targetPlanet = o;
                    closestTargetDistance = distanceToActor;
                }
            }
            turnTowards(targetPlanet.getX(), targetPlanet.getY());
            //System.out.println("TARGET P!");
        }
    }

    private void moveTowardPlanet(){
        // if (Galaxy.getDistance(this, targetPlanet) < 30){

        // }
        move(mySpeed);
        //System.out.println("Move towards P!");
    }

    public int getDegree(){
        return degree;
    }

    public void setIsStaying(boolean x){
        isStaying = x;
    }

    public boolean canFly(Planet planet){
        if (planet.getX() - 10 <= getX() && getX() <= planet.getX() + 10 && !justPassed ){
            passCount++; 
            justPassed = true;
            return false;
        }
        if (planet.getX() - 10 > getX() || getX() > planet.getX() + 10){
            justPassed = false;
            return false;
        }
        if(passCount >= 3){
            rotateDetection = false;
            //setLocation(getX()-10, getY() - 10);
            return true;
        }
        return false;
    }

    public boolean checkHitTree(){
        if (box != null && box.getBaobabTree().getPlanet().equals(randomPlanet)){
            return true;
        }
        return false;
    }

    public LittlePrince(GreenfootImage[] walk, GreenfootImage[] fly, GreenfootImage[] dig, GreenfootImage[] flyInverted){
        super(walk, fly, dig, flyInverted);
        totalHP = Galaxy.hpPerPlanet();
        decreaseHP=Galaxy.Rdecrease;
        appear=true;
        princeHpBar = new SuperStatBar(totalHP, totalHP, this, 100, 10, -50, Color.GREEN, Color.RED, false, Color.BLACK, 1);
        this.walk = walk; 
        this.fly = fly;
        this.dig = dig;
        this.flyInverted = flyInverted;
    }

    public int getPassCount(){
        return passCount;
    }

    public boolean checkHitPlanet () {
        if (planet != null){
            return true;
        }
        else{
            return false;
        }
    }

    public void rotate(){
        planet.setVisited(false);
        speed = planet.getSpeed();
        turnTowards (planet);
        turn(-90);
        if (!checkHitTree()){
            int radius = planet.getRadius();
            double radians = Math.toRadians(angle);
            double x = planet.getX() + (double) ((radius+30) * Math.cos(radians));
            double y = planet.getY() + (double) ((radius+30) * Math.sin(radians));
            angle -= 1.5;
            setLocation(x+speed, y);
            //canFly(planet);
            animate(walk);
            if(canFly(planet)){
                setLocation(getX()-200, getY() - 10);
                targetPlanet = null;
            }
        }else{
            setLocation(getX() + speed, getY());
            animate(dig);
            if (isTouching(Fox.class)){
                box.getBaobabTree().removeBaobabTree(50);
            }else{
                box.getBaobabTree().removeBaobabTree(100);
            }
        }
        
        if (isTouching(Rose.class)){
            Greenfoot.setWorld(new EndScreen(true));
        }
    }

    public boolean getRotationDetection(){
        return rotateDetection;
    }

    public void checkCollisionAsteriods() {
        List<Asteroids> asteroidsList = getWorld().getObjects(Asteroids.class);
        Actor actor = getOneIntersectingObject(Asteroids.class);
        if (actor instanceof Asteroids) {
            Asteroids a = (Asteroids) actor;
            totalHP -= decreaseHP;
            princeHpBar.update(totalHP);
            getWorld().removeObject(a);
            if(totalHP<=0){
                Greenfoot.setWorld(new EndScreen(false));
            }
            // Added Asteroids to keep the amounts as three.
            int currentAsteroids = asteroidsList.size();
            int asteroidsToAdd = Galaxy.getNumOfAsteroids() - currentAsteroids;

            for (int i = 0; i < asteroidsToAdd+1; i++) {
                int x = Greenfoot.getRandomNumber(getWorld().getWidth());
                int y = Greenfoot.getRandomNumber(getWorld().getHeight());
                getWorld().addObject(new Asteroids(), x, y);
            }
        }
    }

    public void addedToWorld (World w){
        w.addObject(princeHpBar, getX(), getY());
    }

    public int getPrinceX(){
        return getX();
    }

    public int getPrinceY(){
        return getY();
    }
    
    public void changeHP(int amount){
        totalHP += amount;
        princeHpBar.update(totalHP);
        if(totalHP<=0){
            Greenfoot.setWorld(new EndScreen(false));
        }
    }
}
