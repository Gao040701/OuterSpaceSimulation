import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
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
    //variables for set the image 
    private SuperStatBar randomHpBar;
    private int ylocation;
    private boolean firstGenerated = true;
    private ArrayList<BaobabTree> trees = new ArrayList<BaobabTree>();
    
    private static boolean hasFox = false;
    private GreenfootImage[] foxRun = new GreenfootImage[8];
    private GreenfootImage[] foxFly = new GreenfootImage[5];
    private GreenfootImage[] foxDig = new GreenfootImage[11];
    
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
                    removeRanPlanet();
                    return;
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
        Asteroids a =(Asteroids) getOneObjectAtOffset((int)speed + -getImage().getWidth()/2,0, Asteroids.class);//left
        if(a==null){
            a=(Asteroids) getOneObjectAtOffset((int)speed + getImage().getWidth()/2,0, Asteroids.class);//right
        }
        if(a==null){
            a=(Asteroids) getOneObjectAtOffset((int)speed, -getImage().getWidth() / 2, Asteroids.class);//north
        }
        if(a==null){
            a=(Asteroids) getOneObjectAtOffset((int)speed,  getImage().getWidth() / 2, Asteroids.class);//south
        }
        if(a==null){
         a=(Asteroids) getOneObjectAtOffset((int)(speed-getImage().getWidth()*Math.sqrt(2) / 2.0), (int)(-getImage().getHeight()*Math.sqrt(2) / 2), Asteroids.class);//westnorth
        }
        if(a==null){
         a=(Asteroids) getOneObjectAtOffset((int)(speed+getImage().getWidth()*Math.sqrt(2) / 2),(int)(getImage().getHeight()*Math.sqrt(2) / 2), Asteroids.class);//westsouth
        }
        if(a==null){
         a=(Asteroids) getOneObjectAtOffset((int)(speed-getImage().getWidth()*Math.sqrt(2) / 2),(int)(getImage().getHeight()*Math.sqrt(2) / 2), Asteroids.class);//westsouth
        }
        if(a==null){
         a=(Asteroids) getOneObjectAtOffset((int)(speed+getImage().getWidth()*Math.sqrt(2) / 2),(int)(-getImage().getHeight()*Math.sqrt(2) / 2), Asteroids.class);//westsouth
        }
        if(a!=null){
            totalHP -= decreaseHP;
            randomHpBar.update(totalHP);
            getWorld().removeObject(a);
            int currentAsteroids = asteroidsList.size();
            int asteroidsToAdd = Galaxy.getNumOfAsteroids() - currentAsteroids;
            
            for (int i = 0; i < asteroidsToAdd+1; i++) {
                int x = Greenfoot.getRandomNumber(getWorld().getWidth());
                int y = Greenfoot.getRandomNumber(getWorld().getHeight());
                getWorld().addObject(new Asteroids(), x, y);
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
        if (getWorld() != null && totalHP <= 0) {
            if(getX() <= 600){
                num++;
                canSpawnNext = false;
                RandomPlanet newPlanet = new RandomPlanet();
                ylocation=Greenfoot.getRandomNumber(276) + 150;
                getWorld().addObject(newPlanet, 0, ylocation);
                getWorld().addObject(newPlanet.getHpBar(), 0, Greenfoot.getRandomNumber(276) + 15);
            }
            removeRanPlanet();
            appear=false;
            return;
        }
    }
    
    public void generateTrees() {
        for (int i = 0; i < 4; i++) {
            if (i == 0 && Greenfoot.getRandomNumber(1) == 0 && !hasFox){
                System.out.println (hasFox);
                Fox fox = new Fox(foxRun, foxFly, foxDig);
                fox.prepareAnimation(foxRun, "foxRun/run", fox.getImage().getWidth()*4, fox.getImage().getHeight()*4);
                fox.prepareAnimation(foxFly, "foxFly/fly", fox.getImage().getWidth()*4, fox.getImage().getHeight()*4);
                fox.prepareAnimation(foxDig, "foxDig/dig", fox.getImage().getWidth()*4, fox.getImage().getHeight()*4);
                getWorld().addObject(fox, getX(), getY() - radius);
                hasFox = true; 
            }else if (Greenfoot.getRandomNumber(2) == 0){
                BaobabTree tree = new BaobabTree(this, i+1);
                trees.add(tree);
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
    
    public void removeRanPlanet(){
        getWorld().removeObject(randomHpBar);
        for (int i = 0; i < trees.size(); i++){
            if (trees.get(i) != null){ //may need to change the condition 
                getWorld().removeObject(trees.get(i));
            }
        }
        getWorld().removeObject(this); 
        return;
    }
}
