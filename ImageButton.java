import greenfoot.*;

/**
 * The ImageButton represents a clickable button with different images for normal and clicked states.
 * It provides basic button functionality such as handling mouse clicks and changing the button's appearance accordingly.
 * This class can be extended to create various types of buttons with custom logic.
 * 
 * Usage:
 * <li>Create an instance by providing the file paths for normal and clicked images, button type, width, and height.</li>
 * <li>Optionally, add specific logic for different button types by checking the buttonType parameter.</li>
 * 
 * @author Jiayi Li
 * @version November 2023
 */
public class ImageButton extends Actor {
    private GreenfootImage normalImage;
    private GreenfootImage clickedImage;

    /**
     * Constructs an {@code ImageButton} with specified images and dimensions.
     * 
     * @param normalImageFile The file path for the normal state image.
     * @param clickedImageFile The file path for the clicked state image.
     * @param buttonType The type of the button (e.g., "plus", "minus", "start").
     * @param buttonWidth The width of the button.
     * @param buttonHeight The height of the button.
     */
    public ImageButton(String normalImageFile, String clickedImageFile, String buttonType, int buttonWidth, int buttonHeight) {
        normalImage = new GreenfootImage(normalImageFile);
        clickedImage = new GreenfootImage(clickedImageFile);
        normalImage.scale(buttonWidth, buttonHeight);
        clickedImage.scale(buttonWidth, buttonHeight);
        setImage(normalImage);

        // Handle different button types
        handleButtonLogic(buttonType);
    }

    
    /**
     * Handles button-specific logic based on the button type.
     * 
     * @param buttonType The type of the button (e.g., "plus", "minus", "start").
     */
    private void handleButtonLogic(String buttonType) {
        if ("plus".equals(buttonType)) {
            // Add logic for the plus button (example: print a message)
            handlePlusButtonLogic();
        } else if ("minus".equals(buttonType)) {
            // Add logic for the minus button (example: print a message)
            handleMinusButtonLogic();
        } else if ("start".equals(buttonType)) {
            // Add logic for the start button
            handleStartButtonLogic();
        }
        // Add logic for other button types...
    }

    /**
     * Handles logic specific to the plus button.
     */
    private void handlePlusButtonLogic() {
        // Example: Print a message when the plus button is clicked
        if (Greenfoot.mousePressed(this)) {
            System.out.println("Plus button clicked!");
        }
    }

    /**
     * Handles logic specific to the minus button.
     */
    private void handleMinusButtonLogic() {
        // Example: Print a message when the minus button is clicked
        if (Greenfoot.mousePressed(this)) {
            System.out.println("Minus button clicked!");
        }
    }

    /**
     * Handles logic specific to the start button.
     */
    private void handleStartButtonLogic() {
        // Example: Change the button image when clicked, and execute additional logic on click
        if (Greenfoot.mousePressed(this)) {
            setImage(clickedImage);
        } else if (Greenfoot.mouseClicked(this)) {
            // Example: Print a message when the start button is clicked
            System.out.println("Start button clicked!");
        } else {
            setImage(normalImage);
        }
    }
    
}



