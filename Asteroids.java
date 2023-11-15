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
        xSpeed = Greenfoot.getRandomNumber(4) + 1; // x ������ٶȷ�Χ�� 1 �� 3 ֮��
        ySpeed = Greenfoot.getRandomNumber(3) - 1; // y ������ٶȷ�Χ�� -1 �� 1 ֮��
        count = 0; // ��������ʼ��Ϊ 0
        asteroid.scale(50+Greenfoot.getRandomNumber(20), 40+Greenfoot.getRandomNumber(20));
        setImage(asteroid);
    }

    public void act() {
        // ���� x �� y ������ٶ��ƶ�����
        setLocation(getX() + xSpeed, getY() + ySpeed);

        // ��������ƶ�����Ļ���Ե�������������Ļ����һ��
        reLocation();
        // ÿ��һ��ʱ��ı�һ���ٶ�
        randomSpeed();
    }
    private void setRandomRotation() {
        double rotation = Greenfoot.getRandomNumber(360); // ���� 0 �� 359 ֮�������Ƕ�
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
            xSpeed = Greenfoot.getRandomNumber(4) + 1; // x ������ٶȷ�Χ�� 1 �� 3 ֮��
            ySpeed = Greenfoot.getRandomNumber(3) - 1; // y ������ٶȷ�Χ�� -1 �� 1 ֮��
            setRandomRotation();
            count = 0;
        }
        if (count < 100) {
            count++;
        }
    }
}