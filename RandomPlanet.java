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
    public int planetImgIndex, length;
    private GreenfootImage img;
    public int distance;
    private int num=0;
    //variables for set the image;
    private SuperStatBar randomHpBar;
    private Hitbox hitbox;
    private int ylocation;
    private boolean firstGenerated = true;
    
    public RandomPlanet() {
        //setLocation(0, Greenfoot.getRandomNumber(276) + 150);
        speed = Greenfoot.getRandomNumber(1) + 1;
        canSpawnNext = false;
        totalHP = Galaxy.Rhp;
        decreaseHP=Galaxy.Rdecrease;
        for (int i = 0; i < 7; i++){
            planets[i] = new GreenfootImage("planets/planet" + i + ".png");
        }
        randomImage();
        randomHpBar = new SuperStatBar(totalHP, totalHP, this, 50, 10, -20, Color.GREEN, Color.RED, false, Color.BLACK, 1);
        hitbox = new Hitbox(10, 10);
        appear=true;
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
                    }
                }else if(getX() > 600 && canSpawnNext && num==0) {
                    num++;
                    canSpawnNext = false;
                    RandomPlanet newPlanet = new RandomPlanet();
                    ylocation=Greenfoot.getRandomNumber(276) + 150;
                    getWorld().addObject(newPlanet, 0, ylocation);
                    getWorld().addObject(newPlanet.getHpBar(), 0, Greenfoot.getRandomNumber(276) + 150);
                }
                randomHpBar.moveMe();
                hitbox.move((int)speed);
            }
        }
        checkAndRemove();
        
        if (firstGenerated){
            generateTrees();
            firstGenerated = false;
        }
    }
    
    public void checkCollision() {
    List<Asteroids> asteroidsList = getWorld().getObjects(Asteroids.class);
    Actor actor = getOneIntersectingObject(Asteroids.class);
    if (actor instanceof Asteroids) {
        Asteroids a = (Asteroids) actor;
        totalHP -= decreaseHP;
        randomHpBar.update(totalHP);
        getWorld().removeObject(a);

        // 在这里添加新的 Asteroids，以保持总数为 SetValuePage.numOfAsteroids
        int currentAsteroids = asteroidsList.size();
        if (currentAsteroids < SetValuePage.numOfAsteroids) {
            int asteroidsToAdd = SetValuePage.numOfAsteroids - currentAsteroids;

            for (int i = 0; i < asteroidsToAdd; i++) {
                int x = Greenfoot.getRandomNumber(getWorld().getWidth());
                int y = Greenfoot.getRandomNumber(getWorld().getHeight());
                getWorld().addObject(new Asteroids(), x, y);
            }
        }
    }
}


    public void randomImage(){
        planetImgIndex = Greenfoot.getRandomNumber(7);
        img = planets[planetImgIndex];
        //scale from 200*200 to 240*240
        length = 200 + Greenfoot.getRandomNumber(41); // 200 + 0 to 40
        img.scale(length, length);
        setImage(img);
        radius = length/2;
    }

    public SuperStatBar getHpBar() {
        return randomHpBar;
    }

    public void addedToWorld (World w){
        w.addObject(randomHpBar, getX() / 2, getY() / 2);
    }
    
    public void checkAndRemove ()
    {
        if (getWorld() != null && totalHP <= 0 && appear) {
        getWorld().removeObject(randomHpBar);
        getWorld().removeObject(hitbox);
        getWorld().removeObject(this); // 从世界中移除我
        appear=false;
        if(getX() <= 600){
            num++;
            canSpawnNext = false;
            RandomPlanet newPlanet = new RandomPlanet();
            ylocation=Greenfoot.getRandomNumber(276) + 150;
            getWorld().addObject(newPlanet, 0, ylocation);
            getWorld().addObject(newPlanet.getHpBar(), 0, Greenfoot.getRandomNumber(276) + 15);
        }
        }
    }
    
    public void generateTrees() {
        for (int i = 0; i < 4; i++) {
            if (Greenfoot.getRandomNumber(2) == 0){
                BaobabTree tree = new BaobabTree(this, i+1);
                switch (i+1){
                    case 1:
                        getWorld().addObject (tree, getX(), getY() - radius - tree.getYOffset());
                        break;
                    case 2:
                        getWorld().addObject (tree, getX() + radius + tree.getXOffset(), getY());
                        break;
                    case 3:
                        getWorld().addObject (tree, getX(), getY() + radius + tree.getYOffset());
                        break;
                    case 4:
                        getWorld().addObject (tree, getX() - radius - 3*tree.getXOffset(), getY());
                        break;
                }
            }
            
        }
    }
}
