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

        // 创建显示数字的 TextButton
        TextButton valueButton = new TextButton(String.valueOf(numOfAsteroids), 30);
        addObject(valueButton, getWidth() / 2, getHeight() / 2);

        // 创建加号按钮
        plusButton = new TextButton("+", 30);
        addObject(plusButton, getWidth() / 2 + 50, getHeight() / 2);

        // 创建减号按钮
        minusButton = new TextButton("-", 30);
        addObject(minusButton, getWidth() / 2 - 50, getHeight() / 2);

        // 创建开始按钮
        startButton = new TextButton("Start", 30);
        addObject(startButton, getWidth() / 2, getHeight() / 2 + 50);
    }

    public void act() {
        if (Greenfoot.mousePressed(plusButton)) {
            // 处理加号按钮点击事件
            if (numOfAsteroids < 6) {
                numOfAsteroids++;
                updateValueButton();
            }
        } else if (Greenfoot.mousePressed(minusButton)) {
            // 处理减号按钮点击事件
            if (numOfAsteroids > 0) {
                numOfAsteroids--;
                updateValueButton();
            }
        } else if (Greenfoot.mousePressed(startButton)) {
            // 处理开始按钮点击事件
            // 进入 Galaxy 场景，并将 numOfAsteroids 传递给 Galaxy
            Galaxy galaxy = new Galaxy(numOfAsteroids);
            Greenfoot.setWorld(galaxy);
        }
    }

    private void updateValueButton() {
        // 更新显示数字的按钮文本
        TextButton valueButton = getObjects(TextButton.class).get(0);
        valueButton.updateMe(String.valueOf(numOfAsteroids));
    }
}
