import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * A Generic Button to display text that is clickable. 
 * 
 * This should be added to, and controlled by, a world.
 * 
 * @author Jordan Cohen
 * @version v0.1.5
 */
public class TextButton extends Actor
{

    // Declare variables
    private GreenfootImage myImage;
    private GreenfootImage myAltImage;
    private String buttonText;
    private int textSize;
    private TextButton plusButton;
    private TextButton minusButton;
    /**
     * Construct a TextButton given only a String
     * @param String    String to be displayed
     */
    public TextButton (String text)
    {
        this(text, 20);
        plusButton = new TextButton("+", textSize);
        getWorld().addObject(plusButton, getX() + getWidth() + 20, getY());
        
        // �������Ű�ť
        minusButton = new TextButton("-", textSize);
        getWorld().addObject(minusButton, getX() - minusButton.getWidth() - 20, getY());
    
    }
    
    /**
     * Construct a TextButton given a String and a text size
     * @param String    String to be displayed
     */
    public TextButton (String text, int textSize)
    {
        // Assign value to my internal String
        buttonText = text;
        this.textSize = textSize;
        // Draw a button with centered text:
        
        updateMe (text);
    }

    public void act ()
    {
        if (Greenfoot.mousePressed(this)) {
            // ���� TextButton �ĵ���¼�
            setImage(myAltImage);
        } else if (Greenfoot.mousePressed(plusButton)) {
            // ����ӺŰ�ť�ĵ���¼�
            // ִ����Ӧ�Ĳ���
        } else if (Greenfoot.mousePressed(minusButton)) {
            // ������Ű�ť�ĵ���¼�
            // ִ����Ӧ�Ĳ���
        } else {
            setImage(myImage);
        }
    }
    
    /**
     * Update current TextButton text
     */
    public void updateMe (String text)
    {
        buttonText = text;
        GreenfootImage tempTextImage = new GreenfootImage (text, textSize, Color.RED, Color.WHITE);
        myImage = new GreenfootImage (tempTextImage.getWidth() + 8, tempTextImage.getHeight() + 8);
        myImage.setColor (Color.WHITE);
        myImage.fill();
        myImage.drawImage (tempTextImage, 4, 4);

        myImage.setColor (Color.BLACK);
        myImage.drawRect (0,0,tempTextImage.getWidth() + 7, tempTextImage.getHeight() + 7);
        setImage(myImage);
        
        tempTextImage = new GreenfootImage (text, textSize, Color.WHITE, Color.RED);
        myAltImage = new GreenfootImage(tempTextImage.getWidth() + 8, tempTextImage.getHeight() + 8);
        myAltImage.setColor (Color.WHITE);
        myAltImage.fill();
        myAltImage.drawImage (tempTextImage, 4, 4);

        myAltImage.setColor (Color.BLACK);
        myAltImage.drawRect (0,0,tempTextImage.getWidth() + 7, tempTextImage.getHeight() + 7);
    }
    public int getWidth() {
        return getImage().getWidth();
    }
}
