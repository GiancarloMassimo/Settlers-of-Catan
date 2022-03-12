import javax.swing.*;

public class Frame extends JFrame {
    public static final int WIDTH = 1600;
    public static final int HEIGHT = 900;

    public Frame(String name) {
        super(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        add(new GamePanel());
        setVisible(true);
    }
}
