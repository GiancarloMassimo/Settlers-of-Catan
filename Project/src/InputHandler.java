import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashSet;

public class InputHandler {
    private static HashSet<KeyEventHandler> keyEvents = new HashSet<>();
    private static HashSet<MouseEventHandler> mouseEvents = new HashSet<>();

    public static void addKeyEvent(KeyEventHandler keyEvent) {
        keyEvents.add(keyEvent);
    }

    public static void addMouseEvent(MouseEventHandler mouseEvent) {
        mouseEvents.add(mouseEvent);
    }

    public static void removeKeyEvent(KeyEventHandler keyEvent) {
        keyEvents.remove(keyEvent);
    }

    public static void removeMouseEvent(MouseEventHandler mouseEvent) {
        mouseEvents.remove(mouseEvent);
    }

    public static void InvokeMouseEvents(MouseEvent e) {
        for (MouseEventHandler mouseEvent : mouseEvents) {
            mouseEvent.OnMouseClick(e);
        }
    }

    public static void InvokeKeyEvents(KeyEvent e) {
        for (KeyEventHandler keyEvent : keyEvents) {
            keyEvent.onKeyDown(e);
        }
    }

}
