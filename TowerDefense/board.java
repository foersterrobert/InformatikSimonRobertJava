import javax.swing.*;

public class board extends JFrame {
    JPanel panel = new JPanel();
    // add image to jpanel
    JLabel label = new JLabel(new ImageIcon("assets/bg.jpeg"));

    public board() {
        setSize(500, 500);
        // resize label
        panel.add(label);
        add(panel);
        setVisible(true);
    }
}
