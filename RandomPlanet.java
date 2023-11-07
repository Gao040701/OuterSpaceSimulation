import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RandomPlanet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RandomPlanet extends Planet {
    private boolean canSpawnNext;

    public RandomPlanet() {
        // �ڹ��캯��������RandomPlanet�ĳ�ʼλ�ú��ٶ�
        setLocation(0, Greenfoot.getRandomNumber(276) + 150); // ����y������150��425֮�����
        speed=Greenfoot.getRandomNumber(3) + 1; // ����x�����ٶ�
        canSpawnNext = false;
    }

    public void checkCollision(){
        Asteroids a = (Asteroids) getOneObjectAtOffset((int) speed + getImage().getWidth() / 2, 0, Asteroids.class);

    }
    
    public void act() {
        // RandomPlanet����Ϊ�߼��������ƶ�������������

        // ��HomePlanet�Ƴ����������������һ��RandomPlanet
        if (!canSpawnNext && getOneIntersectingObject(HomePlanet.class) == null) {
            canSpawnNext = true;
        }

        // ��x���곬��600�ҿ���������һ��RandomPlanetʱ��������һ��RandomPlanet
        if (getX() > 600 && canSpawnNext) {
            getWorld().addObject(new RandomPlanet(), 0, Greenfoot.getRandomNumber(276) + 150);
            canSpawnNext = false; // ��ֹ�������ɶ��RandomPlanet
        }
    }
}
