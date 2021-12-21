import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class board extends JFrame implements MouseListener {
    JPanel panel = new JPanel();
    cell[][] cells = new cell[10][10];

    public static int[] cellspathColumn = {2, 2, 2, 3, 4, 4, 4, 3, 2, 2, 2, 2, 3, 4, 5, 6, 7, 7, 7};
    public static int[] cellspathRow =    {0, 1, 2, 2, 2, 3, 4, 4, 4, 5, 6, 7, 7, 7, 7, 7, 7, 8, 9};

    public board() {
        // System.out.println(cellspathColumn.length);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = new cell(i, j, 50, 50);
            }
        }
        for (int k = 0; k < cellspathColumn.length; k++){
            cells[cellspathColumn[k]][cellspathRow[k]].color = Color.CYAN;
        }
    
        setSize(500, 525);
        add(panel);
        setVisible(true);
        addMouseListener(this);
    }


    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        // System.out.println("Clicked at " + x + "," + y);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (x > i * 50 && x < (i + 1) * 50 && y > j * 50 + 25 && y < (j + 1) * 50 + 25) {
                    if (cells[i][j].color == Color.BLACK) {
                        cells[i][j].color = Color.RED;
                        // System.out.println("1st: "+i+" 2nd: "+j);
                        cells[i][j].draw(panel.getGraphics());
                    }
                }
            }
        }
        repaint();
    }

    public void paint(Graphics g) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j].draw(g);
            }
        }
    }
    


    @Override
    public void mouseEntered(MouseEvent arg0) {
    }


    @Override
    public void mouseExited(MouseEvent arg0) {
    }


    @Override
    public void mouseClicked(MouseEvent arg0) {
    }


    @Override
    public void mouseReleased(MouseEvent arg0) {
    }
}
