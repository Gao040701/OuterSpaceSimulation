import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * The Fox class represents a fox actor in the game that can move, interact with other objects,
 * and follow the Little Prince:
 * <p>
 * - The fox will initially wait on a planet or floast in space by itself. During floatinng, it will target the Little Prince
 * and move closer and closer to him.
 * <p>
 * - Once the Little Fox touches the Little Prince, the fox will follow him forever as his companion.
 * <p>
 * - When the fox helps the prince to remove the baobab trees together, they will be removing them at a faster rate.
 * <p>
 * - the Little Fox uses similar rotation and fly mechanism as the Little Prince
 * @author Zhiyu (Jennifer) Zhou
 * @version v1.0
 */

public class Fox extends Moving
{
    /**
     * Act - do whatever the Fox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
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
    private ArrayList<LittlePrince> littlePrinces;

    private GreenfootImage[] walk;
    private GreenfootImage[] fly;
    private GreenfootImage[] dig;
    private GreenfootImage[] flyInverted;
    private boolean follow = false;
    private LittlePrince targetLP;
    /**
     * Act method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     * Includes the behavior of the fox, such as movement and interaction with other objects.
     * <p>
     * - As along as the fox doesn't touches the LP, it wiill move by itself
     * <p>
     * - Once the fox touches LP, it will follow LP forever.
     */
    public void act()
    {
        littlePrince = (LittlePrince) getOneIntersectingObject(LittlePrince.class);
        randomPlanet = (RandomPlanet) getOneIntersectingObject(RandomPlanet.class);
        planet = (Planet) getOneIntersectingObject(Planet.class);
        box = (HitBox) getOneIntersectingObject(HitBox.class);
        if (targetPlanet != null && targetPlanet.getWorld() == null){
            targetPlanet = null;
        }
        if (targetPlanet == null || Galaxy.getDistance (this, targetPlanet) > 20){
            targetClosestPlanet();
        }
        
        if (checkHitPlanet() && !follow){
            setLocation (planet.getPlanetX() + planet.getRadius(), planet.getPlanetY());
            animate (dig);
            turnTowards(planet);
            turn(-90);
        }
        if (!checkHitPlanet() && !follow){
            passCount = 0;
            //rotateDetection = false;
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
        }
        
        if(checkCollisionLP()){
            follow = true;
            if(littlePrince != null){
                if(!littlePrince.checkHitPlanet() && follow){
                    passCount = 0;
                    targetClosestLP();
                    setRotation(littlePrince.getRotation());
                    if (getRotation() < 270 && getRotation() > 90){
                        targetClosestLP();
                        animate(flyInverted);
                    }
                    else {
                        targetClosestLP();
                        animate(fly);
                    }
                }
            }
        }
        if(checkHitPlanet() && follow){
            rotate();
            animate(walk);
        }
        if(!checkHitPlanet() && follow){
            //setLocation(littlePrince.getX(), littlePrince.getY());
            targetClosestLP();
        }
        super.act();
    }

    private void targetClosestPlanet(){
        double closestTargetDistance = 0;
        double distanceToActor;
        planets = (ArrayList<Planet>)getObjectsInRange(60, Planet.class);

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

    
    /**
     * This method allows the Little Fox to target closest LP and move towards it, which works 
     * well for the following mechanism.
     */
    private void targetClosestLP(){
        double closestTargetDistance = 0;
        double distanceToActor;
        littlePrinces = (ArrayList<LittlePrince>)getObjectsInRange(70, LittlePrince.class);

        if (littlePrinces.size() == 0){
            littlePrinces = (ArrayList<LittlePrince>)getObjectsInRange(170, LittlePrince.class);
        }

        if (littlePrinces.size() > 0){
            targetLP = littlePrinces.get(0);
            closestTargetDistance = getDistance (this, targetLP);

            for (LittlePrince o : littlePrinces){
                distanceToActor = getDistance(this, o);
                if (distanceToActor < closestTargetDistance){
                    targetLP = o;
                    closestTargetDistance = distanceToActor;
                }
            }
            setLocation(targetLP.getX(), targetLP.getY());
            //setRotation(targetLP.getDegree());
            if(targetLP.getDegree() > 90 && targetLP.getDegree() < 360){
                animate(flyInverted);
                turn(targetLP.getDegree());
            }
            if(targetLP.getDegree() > 0 && targetLP.getDegree() < 90){
                animate(flyInverted);
                turn(targetLP.getDegree());
            }
        }
    }
    
    private void targetFlyLP(){
        double closestTargetDistance = 0;
        double distanceToActor;
        littlePrinces = (ArrayList<LittlePrince>)getObjectsInRange(300, LittlePrince.class);

        if (littlePrinces.size() == 0){
            littlePrinces = (ArrayList<LittlePrince>)getObjectsInRange(300, LittlePrince.class);
        }

        if (littlePrinces.size() > 0){
            targetLP = littlePrinces.get(0);
            closestTargetDistance = getDistance (this, targetLP);

            for (LittlePrince o : littlePrinces){
                distanceToActor = getDistance(this, o);
                if (distanceToActor < closestTargetDistance){
                    targetLP = o;
                    closestTargetDistance = distanceToActor;
                }
            }
            setLocation(targetLP.getX(), targetLP.getY());
            //setRotation(targetLP.getDegree());
            if(targetLP.getDegree() > 90 && targetLP.getDegree() < 360){
                animate(flyInverted);
                turn(targetLP.getDegree());
            }
            if(targetLP.getDegree() > 0 && targetLP.getDegree() < 90){
                animate(flyInverted);
                turn(targetLP.getDegree());
            }
        }
    }

    private void moveTowardPlanet(){
        move(mySpeed);
        //System.out.println("Move towards P!");
    }

    /**
     * Checks if the Fox hits a planet.
     * 
     * @return True if the Fox hits a planet, otherwise false.
     */
    public boolean checkHitPlanet () {
        if (planet != null){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Checks if the Fox hits a tree.
     * 
     * @return True if the Fox hits a tree, otherwise false.
     */
    public boolean checkHitTree(){
        if (box != null && box.getBaobabTree().getPlanet().equals(randomPlanet)){
            return true;
        }
        return false;
    }

    public void rotate(){
        if(checkHitPlanet()){
            speed = planet.getSpeed();
            turnTowards (planet);
            turn(-90);
            if (!checkHitTree()){
                int radius = planet.getRadius();
                double radians = Math.toRadians(angle);
                double x = planet.getX() + (double) ((radius+30) * Math.cos(radians));
                double y = planet.getY() + (double) ((radius+30) * Math.sin(radians));
                angle -= 1.5;
                //targetClosestLP();
                //setLocation(x+speed, y);
                targetFlyLP();
                animate(walk);
            }else if(follow){
                
                setLocation(getX() + speed, getY());
                animate(dig);
                if (isTouching(LittlePrince.class)) box.getBaobabTree().removeBaobabTree(50);
                else box.getBaobabTree().removeBaobabTree(100);
                // if(canFly(planet)){
                    // //setLocation(getX()-200, getY() - 10);
                    // targetClosestLP();
                // }
                targetFlyLP();
            }
        }
    }

    /**
     * Checks if the Fox can fly over the planet.
     * 
     * @param planet The planet to check.
     * @return True if the Fox can fly over the planet, otherwise false.
     */
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
            angle = 0;
            return true;
            
        }
        return false;
    }

    public boolean getRotationDetection(){
        return rotateDetection;
    }

    /**
     * Constructor for the Fox class.
     * 
     * @param walk Array of walking animation frames.
     * @param fly Array of flying animation frames.
     * @param dig Array of digging animation frames.
     * @param flyInverted Array of inverted flying animation frames.
     */
    public Fox(GreenfootImage[] walk, GreenfootImage[] fly, GreenfootImage[] dig, GreenfootImage[] flyInverted){
        super(walk, fly, dig, flyInverted);
        this.walk = walk; 
        this.fly = fly;
        this.dig = dig;
        this.flyInverted = flyInverted;
    }

    /**
     * Checks if the Fox collides with the Little Prince.
     * 
     * @return True if the Fox collides with the Little Prince, otherwise false.
     */
    public boolean checkCollisionLP(){
        LittlePrince lp = (LittlePrince) getOneIntersectingObject(LittlePrince.class);
        if (lp != null){
            return true;
        }
        else{
            return false;
        }
    }
}