import greenfoot.*; 

public class Planet extends Actor {
    private int xSpeed;
    private int ySpeed;
    
    public Planet() {
        // Ϊ x �� y ������ٶȸ������ֵ
        xSpeed = Greenfoot.getRandomNumber(5) + 1; // x ������ٶȷ�Χ�� 1 �� 5 ֮��
        ySpeed = Greenfoot.getRandomNumber(3) - 1; // y ������ٶȷ�Χ�� -1 �� 1 ֮��
    }
    
    public void act() {
        // ���� x �� y ������ٶ��ƶ�����
        setLocation(getX() + xSpeed, getY() + ySpeed);
        
        // ��������ƶ�����Ļ���Ե�������������Ļ����һ�࣬���ұ��� 3 �� 5 ����������Ļ��
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
