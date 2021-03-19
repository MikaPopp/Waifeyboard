import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class application {
    private JPanel mainPanel;
    private JButton qButton;
    private JButton aButton;
    private JButton wButton;
    private JButton eButton;
    private JButton rButton;
    private JButton sButton;
    private JButton dButton;
    private JButton fButton;

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
        Component[] componentArr = application.getContentPane().getComponents();
        for(Component component : componentArr) {
            if(component instanceof JButton) {
                component.setFocusable(false);
                component.setEnabled(false);
                component.setBackground(Color.WHITE);
            }
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