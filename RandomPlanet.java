import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class RandomPlanet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RandomPlanet extends Planet {
    private static boolean canSpawnNext;
    private GreenfootImage[] planets = new GreenfootImage[7];
    private int planetImgIndex, length;
    private GreenfootImage img;
    private int num=0;
    //variables for set the image;
    private SuperStatBar randomHpBar;
    private Hitbox hitbox;
    
    public RandomPlanet() {
        //setLocation(0, Greenfoot.getRandomNumber(276) + 150);
        speed = Greenfoot.getRandomNumber(1) + 1;
        canSpawnNext = false;
        planets = new GreenfootImage[7];
        totalHP=Galaxy.Rhp;
        decreaseHP=Galaxy.Rdecrease;
        for (int i = 0; i < 7; i++){
            planets[i] = new GreenfootImage("planets/planet" + i + ".png");
        }
        randomImage();
        randomHpBar = new SuperStatBar(totalHP, totalHP, this, 50, 10, -20, Color.GREEN, Color.RED, false, Color.BLACK, 1);
        hitbox = new Hitbox(10, 10);
        appear=true;
    }

    public void checkCollision() {
        List<Asteroids> asteroidsList = getWorld().getObjects(Asteroids.class);
        Actor actor = getOneIntersectingObject(Asteroids.class);
    if (actor instanceof Asteroids) {
    Asteroids a = (Asteroids) actor;
    totalHP -= decreaseHP;
    randomHpBar.update(totalHP);
    getWorld().removeObject(a);
    
    // 在这里添加新的 Asteroids，以保持总数为三个
    int currentAsteroids = asteroidsList.size();
    int asteroidsToAdd = 3 - currentAsteroids;
    
    for (int i = 0; i < asteroidsToAdd+1; i++) {
        int x = Greenfoot.getRandomNumber(getWorld().getWidth());
        int y = Greenfoot.getRandomNumber(getWorld().getHeight());
        getWorld().addObject(new Asteroids(), x, y);
    }
    }
    }

    public void act() {
        if (!appear) {
            return; // 如果对象不应该出现，直接返回
        }
        if(appear){
                if(appear){
                super.act();
            }// 调用基类的 act() 方法，实现星球的基本移动逻辑
            if (getImage() == null) {
                // 如果图像为 null，说明已经被移除，不执行后续逻辑
                appear =false;
                return;
            }

        if(appear){
            if(getX() > getWorld().getWidth()){
                getWorld().removeObject(this); // 移除当前星球对象
            }else if (!canSpawnNext  ) {
                canSpawnNext = true;
                if(num==2){
                    num=0;
                    int treeCount = Greenfoot.getRandomNumber(3) + 1; // Randomly generate 1 to 3 trees
                    generateTrees(treeCount);
                }
            } 
            else if(getX() > 600 && canSpawnNext && num==0) {
                num++;
                canSpawnNext = false;
                RandomPlanet newPlanet = new RandomPlanet();
                getWorld().addObject(newPlanet, 0, Greenfoot.getRandomNumber(276) + 150);
                getWorld().addObject(newPlanet.getHpBar(), 0, Greenfoot.getRandomNumber(276) + 150);
            }
            randomHpBar.moveMe();
            hitbox.move((int)speed);
        }
    }
        checkAndRemove();
    }

    public void randomImage(){
        planetImgIndex = Greenfoot.getRandomNumber(7);
        img = planets[planetImgIndex];
        //scale from 200*200 to 240*240
        length = 200 + Greenfoot.getRandomNumber(41); // 200 + 0 to 40
        img.scale(length, length);
        setImage(img);
    }

    public SuperStatBar getHpBar() {
        return randomHpBar;
    }

    public void addedToWorld (World w){
        w.addObject(randomHpBar, getX() / 2, getY() / 2);
        w.addObject(hitbox, getX(), getY() - getRadius());
        System.out.println("Random X coord: " + getX()+ "Random Y coord: "+ (getY() - getRadius()));
    }
    
    public void checkAndRemove ()
    {
        if (getWorld() != null && totalHP <= 0 && appear) {
        getWorld().removeObject(randomHpBar);
        getWorld().removeObject(hitbox);
        getWorld().removeObject(this); // 从世界中移除我
        appear=false;
        System.out.println(appear);
        }
    }
    private void generateTrees(int count) {
        for (int i = 0; i < count; i++) {
            BaobabTree tree = new BaobabTree(); // Assuming you have a Tree class that accepts a planet as a parameter
            double angle = Math.toRadians(Greenfoot.getRandomNumber(360)); // Random angle
            int distance = Greenfoot.getRandomNumber(radius * 2); // Random distance within the planet's radius
            int treeX = (int) (getX() + distance * Math.cos(angle));
            int treeY = (int) (getY() + distance * Math.sin(angle));
            getWorld().addObject(tree, treeX, treeY);
            tree.setRotation(getRotation()); // Set tree's angle to match the planet's rotation
        }
    }
}
