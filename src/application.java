import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class application {
    private JButton wButton;
    private JButton sButton;
    private JButton eButton;
    private JButton dButton;
    private JPanel mainPanel;

    public static void main(String[] args) {
        JFrame application = new JFrame();
        application.setContentPane(new application().mainPanel);
        application.pack();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setVisible(true);
        application.setLocationRelativeTo(null);
        CustomKeyListener anyKeyListener = new CustomKeyListener(application);
        application.addKeyListener(anyKeyListener);
        application.setFocusable(true);
        application.requestFocus();
        int size = application.getContentPane().getComponentCount();
        for(int i = 0; i < size; i++) {
            application.getContentPane().getComponent(i).setFocusable(false);
            application.getContentPane().getComponent(i).setEnabled(false);
        }
    }
}

class CustomKeyListener extends KeyAdapter {
    private HashMap<String,Component> componentHashMap;

    public CustomKeyListener(JFrame frame) {
        this.componentHashMap = createComponentMap(frame);
    }

    private HashMap<String,Component> createComponentMap(JFrame frame) {
        HashMap<String,Component> componentMap = new HashMap<>();
        Component[] components = frame.getContentPane().getComponents();
        for (Component component : components) {
            componentMap.put(component.getName(), component);
        }
        return componentMap;
    }

    public Component getComponentByName(String name) {
        if (this.componentHashMap.containsKey(name)) {
            return componentHashMap.get(name);
        }
        else {
            return null;
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {
        String keyString = event.getKeyChar()+"Button";
        try {
            getComponentByName(keyString).setBackground(Color.BLACK);
        } catch (Exception ignored) {

        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        String keyString = event.getKeyChar() + "Button";
        try {
            getComponentByName(keyString).setBackground(Color.WHITE);
        } catch (Exception ignored) {

        }
    }
}