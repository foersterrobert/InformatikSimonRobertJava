import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class MainFrame extends JFrame{

    JPanel mainPanel = new JPanel();

    String currentView = "YearView";

    YearView yv;

    public MainFrame(){
        yv = new YearView();
        mainPanel.add(yv);

        add(mainPanel);

        setVisible(true);
        setTitle("Calendar V2.0");
        setSize(1920, 1080);
    }
    
    /*public static void main(String[]args){
        SwingUtilities.invokeLater(() -> new MainFrame());
    }*/

    public static Window getMainFrame() {
        return null;
    }
}
