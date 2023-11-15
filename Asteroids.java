import greenfoot.*; 
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Asteroids extends SuperSmoothMover {
    private int xSpeed;
    private int ySpeed;
    private int count;
    private GreenfootImage asteroid = new GreenfootImage("asteroid.png");
    public Asteroids() {
        xSpeed = Greenfoot.getRandomNumber(4) + 1; // x 方向的速度范围在 1 到 3 之间
        ySpeed = Greenfoot.getRandomNumber(3) - 1; // y 方向的速度范围在 -1 到 1 之间
        count = 0; // 计数器初始化为 0
        asteroid.scale(50+Greenfoot.getRandomNumber(20), 40+Greenfoot.getRandomNumber(20));
        setImage(asteroid);
    }

    public void act() {
        // 根据 x 和 y 方向的速度移动星球
        setLocation(getX() + xSpeed, getY() + ySpeed);

        // 如果星球移动到屏幕外边缘，将其放置在屏幕的另一侧
        reLocation();
        // 每隔一段时间改变一次速度
        randomSpeed();
    }
    private void setRandomRotation() {
        double rotation = Greenfoot.getRandomNumber(360); // 生成 0 到 359 之间的随机角度
        setRotation(rotation);
    }
    
    private void reLocation(){
        if (getX() >= getWorld().getWidth()) {
            setLocation(0, Greenfoot.getRandomNumber(getWorld().getHeight()));
        }else if (getX() < 0){
            setLocation(getWorld().getWidth(), Greenfoot.getRandomNumber(getWorld().getHeight()));
        }else if (getY() >= getWorld().getHeight()) {
            setLocation(Greenfoot.getRandomNumber(getWorld().getWidth()), 0);
        }else if (getY() < 0) {
            setLocation(Greenfoot.getRandomNumber(getWorld().getWidth()), getWorld().getHeight());
        }
    }
    
    private void randomSpeed(){
        if (count == 100) {
            xSpeed = Greenfoot.getRandomNumber(4) + 1; // x 方向的速度范围在 1 到 3 之间
            ySpeed = Greenfoot.getRandomNumber(3) - 1; // y 方向的速度范围在 -1 到 1 之间
            setRandomRotation();
            count = 0;
        }
        if (count < 100) {
            count++;
        }
    }
}