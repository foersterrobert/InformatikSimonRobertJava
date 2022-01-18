import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame{
    JPanel jp = new JPanel();
    JTextField jtf = new JTextField(30);
    JButton jsubmit = new JButton("Submit");

    public GUI(){
        jp.add(jtf);
        jp.add(jsubmit);
        add(jp);
        setVisible(true);
        setSize(400, 400);
    }
}
