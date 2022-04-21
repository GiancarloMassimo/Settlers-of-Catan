import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameLog extends JPanel implements KeyEventHandler {
    public static GameLog instance;
    private ArrayList<String> log;
    private JFrame frame = null;
    private JTextArea textArea;

    public GameLog() {
        if (instance == null) {
            instance = this;
        } else {
            return;
        }
        log = new ArrayList<>();

        InputHandler.addKeyEvent(this);

        createScrollPane();
    }

    private void createScrollPane() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(525, 200));

        textArea = new JTextArea(5, 40);

        String text = getTextAreaText();

        textArea.setText(text);
        textArea.setFont(new Font("Arial", Font.TRUETYPE_FONT, 18));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);

        this.add(scrollPane, BorderLayout.CENTER);
        textArea.setEditable(false);

        setOpaque(true);

        frame = new JFrame("Game Log");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setContentPane(this);
        frame.pack();
        frame.setVisible(true);
    }

    private String getTextAreaText() {
        StringBuilder stringBuilder = new StringBuilder();

        int i = 1;
        for (String s : log) {
            stringBuilder.append(i + ". " + s + "\n");
            i++;
        }

        return stringBuilder.toString();
    }

    public void logEvent(String event) {
        log.add(event);
        textArea.setText(getTextAreaText());
    }

    @Override
    public void OnKeyDown(KeyEvent e) {
        if (e.getKeyChar() == 'l') {
            frame.setVisible(!frame.isVisible());
        }
    }
}
