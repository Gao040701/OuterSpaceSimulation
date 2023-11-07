import greenfoot.*; 

public class Asteroids extends Actor {
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
        if (count == 200) {
            xSpeed = Greenfoot.getRandomNumber(4) + 1; // x ������ٶȷ�Χ�� 1 �� 3 ֮��
            ySpeed = Greenfoot.getRandomNumber(3) - 1; // y ������ٶȷ�Χ�� -1 �� 1 ֮��
            count = 0;
        }
        if (count < 200) {
            count++;
        }
    }
}


