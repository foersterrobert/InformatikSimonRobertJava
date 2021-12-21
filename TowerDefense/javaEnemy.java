import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.*;
import javax.swing.*;

public class javaEnemy{
    int[] cellspathColumn;
    int[] cellspathRow;

    String javaIconPath = "assets/java_logo.png";

    JPanel panel = new JPanel();

    public javaEnemy() throws IOException{
        cellspathColumn = board.cellspathColumn;
        cellspathRow = board.cellspathRow;
        
    }

    public void paint(Graphics g){
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image javaIcon = tk.getImage(javaIconPath);
        g.drawImage(javaIcon, 200, 200, (ImageObserver) this);
    }
}
