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

        // ����ǼӺŰ�ť
        if ("plus".equals(buttonType)) {
            // �����Ӧ�İ�ť�߼�
            // ʾ�������һ���ӷ���ť�ĵ���¼�����
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if (Greenfoot.mousePressed(this)) {
                // ִ�мӷ���ť�ĵ���¼�
                // ʾ�����ڿ���̨��ӡ��Ϣ
                System.out.println("Plus button clicked!");
            }
        }
        // ����Ǽ��Ű�ť
        else if ("minus".equals(buttonType)) {
            // �����Ӧ�İ�ť�߼�
            // ʾ�������һ��������ť�ĵ���¼�����
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if (Greenfoot.mousePressed(this)) {
                // ִ�м�����ť�ĵ���¼�
                // ʾ�����ڿ���̨��ӡ��Ϣ
                System.out.println("Minus button clicked!");
            }
        }
        // ���������ť���͵��߼�...
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



