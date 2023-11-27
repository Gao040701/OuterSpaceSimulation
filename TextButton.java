import greenfoot.*;

/**
 * <p>The TextButton class represents a button with text in the Greenfoot environment.</p>
 * <p>It can be used to create buttons with different functionalities, such as plus and minus buttons.</p>
 * 
 * @author Jiayi Li
 * @version November 2023
 */
public class TextButton extends Actor {
    private GreenfootImage myImage;
    private GreenfootImage myAltImage;
    private String buttonText;
    private int textSize;
    private TextButton plusButton;
    private TextButton minusButton;
    private String buttonType;

    /**
     * Construct a TextButton given only a String.
     * 
     * @param text       String to be displayed on the button.
     * @param textSize   Size of the text.
     * @param buttonType Type of the button, e.g., "plus" or "minus".
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
     * Construct a TextButton given a String and a text size.
     * 
     * @param text     String to be displayed on the button.
     * @param textSize Size of the text.
     */
    public TextButton(String text, int textSize) {
        // Assign value to my internal String
        buttonText = text;
        this.textSize = textSize;

        // Draw a button with centered text:
        updateMe(text);
    }

    /**
     * Act - do whatever the TextButton wants to do.
     * This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if (Greenfoot.mousePressed(this)) {
            // Handle click events for TextButton
            setImage(myAltImage);
        } else if ("plus".equals(buttonType) && Greenfoot.mousePressed(plusButton)) {
            // Handle click events for the plus button
            // Execute corresponding operations
        } else if ("minus".equals(buttonType) && Greenfoot.mousePressed(minusButton)) {
            // Handle click events for the minus button
            // Execute corresponding operations
        } else {
            setImage(myImage);
        }
    }

    /**
     * Update current TextButton text.
     * 
     * @param text New text to be displayed on the button.
     */
    public void updateMe(String text) {
        buttonText = text;
        GreenfootImage tempTextImage = new GreenfootImage(text, textSize, new Color(87, 171, 131), Color.WHITE);
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

