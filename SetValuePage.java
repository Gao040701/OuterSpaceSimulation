import greenfoot.*;

public class SetValuePage extends World {
    private int numOfAsteroids;
    private TextButton plusButton;
    private TextButton minusButton;
    private TextButton startButton;

    public SetValuePage() {
        super(1024, 576, 1);
        prepare();
    }

    private void prepare() {
        numOfAsteroids = 0;

        // ������ʾ���ֵ� TextButton
        TextButton valueButton = new TextButton(String.valueOf(numOfAsteroids), 30);
        addObject(valueButton, getWidth() / 2, getHeight() / 2);

        // �����ӺŰ�ť
        plusButton = new TextButton("+", 30);
        addObject(plusButton, getWidth() / 2 + 50, getHeight() / 2);

        // �������Ű�ť
        minusButton = new TextButton("-", 30);
        addObject(minusButton, getWidth() / 2 - 50, getHeight() / 2);

        // ������ʼ��ť
        startButton = new TextButton("Start", 30);
        addObject(startButton, getWidth() / 2, getHeight() / 2 + 50);
    }

    public void act() {
        if (Greenfoot.mousePressed(plusButton)) {
            // ����ӺŰ�ť����¼�
            if (numOfAsteroids < 6) {
                numOfAsteroids++;
                updateValueButton();
            }
        } else if (Greenfoot.mousePressed(minusButton)) {
            // ������Ű�ť����¼�
            if (numOfAsteroids > 0) {
                numOfAsteroids--;
                updateValueButton();
            }
        } else if (Greenfoot.mousePressed(startButton)) {
            // ����ʼ��ť����¼�
            // ���� Galaxy ���������� numOfAsteroids ���ݸ� Galaxy
            Galaxy galaxy = new Galaxy(numOfAsteroids);
            Greenfoot.setWorld(galaxy);
        }
    }

    private void updateValueButton() {
        // ������ʾ���ֵİ�ť�ı�
        TextButton valueButton = getObjects(TextButton.class).get(0);
        valueButton.updateMe(String.valueOf(numOfAsteroids));
    }
}
