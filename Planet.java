import greenfoot.*; 

public class Planet extends Actor {
    private int xSpeed;
    private int ySpeed;
    
    public Planet() {
        // 为 x 和 y 方向的速度赋予随机值
        xSpeed = Greenfoot.getRandomNumber(5) + 1; // x 方向的速度范围在 1 到 5 之间
        ySpeed = Greenfoot.getRandomNumber(3) - 1; // y 方向的速度范围在 -1 到 1 之间
    }
    
    public void act() {
        // 根据 x 和 y 方向的速度移动星球
        setLocation(getX() + xSpeed, getY() + ySpeed);
        
        // 如果星球移动到屏幕外边缘，将其放置在屏幕的另一侧，并且保持 3 到 5 个星球在屏幕上
        if (getX() >= getWorld().getWidth()) {
            setLocation(0, Greenfoot.getRandomNumber(getWorld().getHeight()));
        }
        if (getX() < 0) {
            setLocation(getWorld().getWidth(), Greenfoot.getRandomNumber(getWorld().getHeight()));
        }
        if (getY() >= getWorld().getHeight()) {
            setLocation(Greenfoot.getRandomNumber(getWorld().getWidth()), 0);
        }
        if (getY() < 0) {
            setLocation(Greenfoot.getRandomNumber(getWorld().getWidth()), getWorld().getHeight());
        }
    }
}
