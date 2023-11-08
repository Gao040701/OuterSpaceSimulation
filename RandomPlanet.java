import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RandomPlanet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RandomPlanet extends Planet {
    private boolean canSpawnNext;
    
    //variables for set the image;
    private GreenfootImage[] planets = new GreenfootImage[7];
    private int planetImgIndex, length;
    private GreenfootImage img;
    public RandomPlanet() {
        // 在构造函数中设置RandomPlanet的初始位置和速度
        setLocation(0, Greenfoot.getRandomNumber(276) + 150); // 设置y坐标在150到425之间随机
        speed=Greenfoot.getRandomNumber(3) + 1; // 设置x方向速度
        canSpawnNext = false;
        
        //set the greenfootImage array for random setImage
        for (int i = 0; i < 7; i++){
            planets[i] = new GreenfootImage("planets/planet" + i + ".png");
        }
        //set random img in random size 
        randomImage();
    }

    public void checkCollision(){
        Asteroids a = (Asteroids) getOneObjectAtOffset((int) speed + getImage().getWidth() / 2, 0, Asteroids.class);

    }
    
    public void act() {
        // RandomPlanet的行为逻辑，例如移动和其他交互等

        // 当HomePlanet移出世界后，允许生成下一个RandomPlanet
        if (!canSpawnNext && getOneIntersectingObject(HomePlanet.class) == null) {
            canSpawnNext = true;
        }

        // 当x坐标超过600且可以生成下一个RandomPlanet时，生成下一个RandomPlanet
        if (getX() > 600 && canSpawnNext) {
            getWorld().addObject(new RandomPlanet(), 0, Greenfoot.getRandomNumber(276) + 150);
            canSpawnNext = false; // 防止连续生成多个RandomPlanet
        }
    }
    
    private void randomImage(){
        planetImgIndex = Greenfoot.getRandomNumber(7);
        img = planets[planetImgIndex];
        //scale from 200*200 to 240*240
        length = 200+Greenfoot.getRandomNumber(5);
        img.scale(length, length);
        setImage(img);
    }
    
}
