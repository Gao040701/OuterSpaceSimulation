import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RandomPlanet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RandomPlanet extends Planet {
    private static boolean canSpawnNext;
    private GreenfootImage[] planets = new GreenfootImage[7];
    private int planetImgIndex, length;
    private GreenfootImage img;
    private int num=0;
    //variables for set the image;
    public static SuperStatBar RandomHpBar;
     public RandomPlanet() {
        //setLocation(0, Greenfoot.getRandomNumber(276) + 150);
        speed = Greenfoot.getRandomNumber(1) + 1;
        canSpawnNext = false;
        planets = new GreenfootImage[7];
        for (int i = 0; i < 7; i++){
            planets[i] = new GreenfootImage("planets/planet" + i + ".png");
        }
        randomImage();
        RandomHpBar = new SuperStatBar(100, 100, this, 50, 10, -20, Color.GREEN, Color.RED, false, Color.BLACK, 1);
    }

    public void checkCollision() {
        Asteroids a = (Asteroids) getOneObjectAtOffset((int) speed + getImage().getWidth() / 2, 0, Asteroids.class);
        // ������ײ�߼�
    }

    public void act() {
        super.act(); // ���û���� act() ������ʵ������Ļ����ƶ��߼�

        if(getX() > getWorld().getWidth()){
            getWorld().removeObject(this); // �Ƴ���ǰ�������
        }else if (!canSpawnNext  ) {
            canSpawnNext = true;
            if(num==2){
                num=0;
            }
        } 
        else if(getX() > 600 && canSpawnNext && num==0) {
            num++;
            canSpawnNext = false;
            RandomPlanet newPlanet = new RandomPlanet();
            getWorld().addObject(newPlanet, 0, Greenfoot.getRandomNumber(276) + 150);
            getWorld().addObject(newPlanet.getHpBar(), 0, Greenfoot.getRandomNumber(276) + 150);

        }
        RandomHpBar.moveMe();
    }

    
    public void randomImage(){
        planetImgIndex = Greenfoot.getRandomNumber(7);
        img = planets[planetImgIndex];
        //scale from 200*200 to 240*240
        length = 200 + Greenfoot.getRandomNumber(41); // 200 + 0 to 40
        img.scale(length, length);
        setImage(img);
        radius = length / 2;
    }
    
    public SuperStatBar getHpBar() {
        return RandomHpBar;
    }
}
