import greenfoot.*;

public class ImageButton extends Actor {
    private GreenfootImage normalImage;
    private GreenfootImage clickedImage;

    public ImageButton(String normalImageFile, String clickedImageFile, String buttonType, int buttonWidth, int buttonHeight) {
        normalImage = new GreenfootImage(normalImageFile);
        clickedImage = new GreenfootImage(clickedImageFile);
        normalImage.scale(buttonWidth, buttonHeight);
        clickedImage.scale(buttonWidth, buttonHeight);
        setImage(normalImage);

        // 如果是加号按钮
        if ("plus".equals(buttonType)) {
            // 添加相应的按钮逻辑
            // 示例：添加一个加法按钮的点击事件监听
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if (Greenfoot.mousePressed(this)) {
                // 执行加法按钮的点击事件
                // 示例：在控制台打印消息
                System.out.println("Plus button clicked!");
            }
        }
        // 如果是减号按钮
        else if ("minus".equals(buttonType)) {
            // 添加相应的按钮逻辑
            // 示例：添加一个减法按钮的点击事件监听
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if (Greenfoot.mousePressed(this)) {
                // 执行减法按钮的点击事件
                // 示例：在控制台打印消息
                System.out.println("Minus button clicked!");
            }
        }
        // 添加其他按钮类型的逻辑...
        if ("start".equals(buttonType)) {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if (Greenfoot.mousePressed(this)) {
                setImage(clickedImage);
            } else if (Greenfoot.mouseClicked(this)) {
                // Execute start button clicked event
                // Example: Print a message to the console
                System.out.println("Start button clicked!");
                // You can add more logic for the start button click event here
            } else {
                setImage(normalImage);
            }
        }
    }
}



