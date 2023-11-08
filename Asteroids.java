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

    public Asteroids() {
        xSpeed = Greenfoot.getRandomNumber(4) + 1; // x ������ٶȷ�Χ�� 1 �� 3 ֮��
        ySpeed = Greenfoot.getRandomNumber(3) - 1; // y ������ٶȷ�Χ�� -1 �� 1 ֮��
        count = 0; // ��������ʼ��Ϊ 0
    }

    public void act() {
        // ���� x �� y ������ٶ��ƶ�����
        setLocation(getX() + xSpeed, getY() + ySpeed);

        // ��������ƶ�����Ļ���Ե�������������Ļ����һ��
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
        // ÿ��һ��ʱ��ı�һ���ٶ�
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
    private void setRandomRotation() {
        double rotation = Greenfoot.getRandomNumber(360); // ���� 0 �� 359 ֮�������Ƕ�
        setRotation(rotation);
    }
}

