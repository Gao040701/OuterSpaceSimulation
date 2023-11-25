import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Fox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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

    private GreenfootImage[] walk;
    private GreenfootImage[] fly;
    private GreenfootImage[] dig;
    private GreenfootImage[] flyInverted;
    private boolean follow = false;
    public void act()
    {
        // if (getWorld().getObjects(LittlePrince.class) != null){
        // //turnTowards(littlePrince);
        // }
        littlePrince = (LittlePrince) getOneIntersectingObject(LittlePrince.class);
        randomPlanet = (RandomPlanet) getOneIntersectingObject(RandomPlanet.class);
        planet = (Planet) getOneIntersectingObject(Planet.class);
        box = (HitBox) getOneIntersectingObject(HitBox.class);

        if (checkHitPlanet() && follow == false){
            //System.out.println("ON PLANET");
            setLocation (planet.getPlanetX() + planet.getRadius(), planet.getPlanetY());
            animate (dig);
            turnTowards(planet);
            turn(-90);
        }
        if (!checkHitPlanet() && follow == false){
            //System.out.println("FLOAT ALONE");
            passCount = 0;
            //rotateDetection = false;
            isStaying = false;
            rotateImage(90);
            moveRandomly();
            if (getRotation() < 270 && getRotation() > 90){
                animate(flyInverted);
            }else animate(fly);
        }
        if(checkCollisionLP()){
            follow = true;
            //System.out.println("Follow");

            if(!littlePrince.checkHitPlanet() && follow == true){
                passCount = 0;
                //setLocation(littlePrince.getPrinceX(), littlePrince.getPrinceY());
                targetClosestLP();
                setRotation(littlePrince.getRotation());
                if (getRotation() < 270 && getRotation() > 90){
                    targetClosestLP();
                    //setLocation(littlePrince.getPrinceX(), littlePrince.getPrinceY());
                    //turnTowards(littlePrince.getPrinceX(), littlePrince.getPrinceY());
                    //animate(flyInverted);
                }
                else {
                    targetClosestLP();
                    //setLocation(littlePrince.getPrinceX(), littlePrince.getPrinceY());
                    //turnTowards(littlePrince.getPrinceX(), littlePrince.getPrinceY());
                    animate(fly);
                }
                //System.out.println("FOLLOW LP FLOAT");
                //setLocation(littlePrince.getPrinceX(), littlePrince.getPrinceY());
                //turnTowards(littlePrince);
                //setRotation(degree);
                animate(fly);
            }
        }
        if(checkHitPlanet() && follow == true){
            //targetClosestLP();
            rotate();
            animate(walk);
        }
        if(!checkHitPlanet() && follow == true){
            targetClosestLP();
        }
        super.act();
    }

    public boolean checkHitPlanet () {
        if (planet != null){
            return true;
        }
        else{
            return false;
        }
    }

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
                setLocation(x+speed, y);
                if(canFly(planet)){
                    setLocation(getX()-200, getY() - 10);
                }
                animate(walk);
            }else if(follow == true){
                setLocation(getX() + speed, getY());
                animate(dig);
                box.getBaobabTree().removeBaobabTree();
                if(canFly(planet)){
                    setLocation(getX()-200, getY() - 10);
                }
            }
        }
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
            //rotateDetection = false;
            //setLocation(getX()-10, getY() - 10);
            //System.out.println("LP FLY!");
            return true;
        }
        return false;
    }

    public boolean getRotationDetection(){
        return rotateDetection;
    }

    public Fox(GreenfootImage[] walk, GreenfootImage[] fly, GreenfootImage[] dig, GreenfootImage[] flyInverted){
        super(walk, fly, dig, flyInverted);
        this.walk = walk; 
        this.fly = fly;
        this.dig = dig;
        this.flyInverted = flyInverted;
    }

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
