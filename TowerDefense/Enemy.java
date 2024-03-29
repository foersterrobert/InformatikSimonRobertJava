import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.*;
import javax.swing.*;

public class Enemy extends JPanel{
    public static final int UNIT_SIZE = 50;
    int[][] cellspathCords;
    int positionIndex = 1;
    int speed = 2;
    int x;
    int y;
    String javaIconPath = "assets/java_logo.png";
    Toolkit tk;
    Image javaIcon;
    
    public Enemy() throws IOException{
        tk = Toolkit.getDefaultToolkit();
        javaIcon = tk.getImage(javaIconPath);
        cellspathCords = Game.cellspathCords;
        x = cellspathCords[0][0] * UNIT_SIZE;
        y = cellspathCords[0][1] * UNIT_SIZE + UNIT_SIZE/2;
    }

    public void move() {
        if (x <= cellspathCords[positionIndex][0] * UNIT_SIZE) {
            x+=speed;
        }
        
        else {
            x-=speed;
        }
        
        if (y <= cellspathCords[positionIndex][1] * UNIT_SIZE + UNIT_SIZE/2) {
            y+=speed;
        }
        
        else {
            y-=speed;
        }
        
        if (
            Math.abs(cellspathCords[positionIndex][0] * UNIT_SIZE - x) <= speed*2
            && 
            Math.abs(cellspathCords[positionIndex][1] * UNIT_SIZE + UNIT_SIZE/2 - y) <= speed*2
        ) {
                positionIndex++;
                positionIndex = positionIndex % cellspathCords.length;
        }
    }

    public void draw(Graphics g){
        g.drawImage(
            javaIcon, 
            x, 
            y, 
            (ImageObserver) this);
        g.dispose();
        tk.sync();
    }
}
 