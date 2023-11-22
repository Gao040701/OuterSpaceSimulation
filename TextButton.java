import greenfoot.*;

public class TextButton extends Actor {
    private GreenfootImage myImage;
    private GreenfootImage myAltImage;
    private String buttonText;
    private int textSize;
    private TextButton plusButton;
    private TextButton minusButton;
    private String buttonType;
    /**
     * Construct a TextButton given only a String
     * 
     * @param String String to be displayed
     */
    public TextButton(String text, int textSize, String buttonType) {
        this(text, 20);
        if ("plus".equals(buttonType)) {
            plusButton = new TextButton("+", textSize);
            getWorld().addObject(plusButton, getX() + getImage().getWidth() + 20, getY());
        } else if ("minus".equals(buttonType)) {
            minusButton = new TextButton("-", textSize);
            getWorld().addObject(minusButton, getX() - minusButton.getImage().getWidth() - 20, getY());
        }
    }

    /**
     * Construct a TextButton given a String and a text size
     * 
     * @param String String to be displayed
     */
    public TextButton(String text, int textSize) {
        // Assign value to my internal String
        buttonText = text;
        this.textSize = textSize;

        // Draw a button with centered text:
        updateMe(text);
    }
    
    public void act() {
        if (Greenfoot.mousePressed(this)) {
            // 处理 TextButton 的点击事件
            setImage(myAltImage);
        } else if ("plus".equals(buttonType) && Greenfoot.mousePressed(plusButton)) {
            // 处理加号按钮的点击事件
            // 执行相应的操作
        } else if ("minus".equals(buttonType) && Greenfoot.mousePressed(minusButton)) {
            // 处理减号按钮的点击事件
            // 执行相应的操作
        } else {
            setImage(myImage);
        }
    }
    /**
     * Update current TextButton text
     */
    public void updateMe(String text) {
        buttonText = text;
        GreenfootImage tempTextImage = new GreenfootImage(text, textSize, Color.GREEN, Color.WHITE);
        myImage = new GreenfootImage(tempTextImage.getWidth() + 8, tempTextImage.getHeight() + 8);
        myImage.setColor(Color.WHITE);
        myImage.fill();
        myImage.drawImage(tempTextImage, 4, 4);

        myImage.setColor(Color.BLACK);
        myImage.drawRect(0, 0, tempTextImage.getWidth() + 7, tempTextImage.getHeight() + 7);
        setImage(myImage);

        tempTextImage = new GreenfootImage(text, textSize, Color.WHITE, Color.RED);
        myAltImage = new GreenfootImage(tempTextImage.getWidth() + 8, tempTextImage.getHeight() + 8);
        myAltImage.setColor(Color.WHITE);
        myAltImage.fill();
        myAltImage.drawImage(tempTextImage, 4, 4);

        myAltImage.setColor(Color.BLACK);
        myAltImage.drawRect(0, 0, tempTextImage.getWidth() + 7, tempTextImage.getHeight() + 7);
    }
}

