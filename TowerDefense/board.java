import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class board extends JFrame implements MouseListener {
    JPanel panel = new JPanel();
    cell[][] cells = new cell[10][10];

    int[] cellspath1 = {2, 1};
    int[] cellspath2 = {5, 3};
    public board() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = new cell(i, j, 50, 50);

                /*if (j % 2 == 0) {
                    cells[i][j].color = Color.CYAN;
                }*/
            }
        }
        for (int k = 0; k < cellspath1.length; k++){
            for (int l = 0; l < cellspath1.length; l++){
            cells[cellspath1[k]][cellspath2[l]].color = Color.CYAN;
            }
        }
    
        setSize(500, 525);
        add(panel);
        setVisible(true);
        addMouseListener(this);
    }

    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println("Clicked at " + x + "," + y);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (x > i * 50 && x < (i + 1) * 50 && y > j * 50 + 25 && y < (j + 1) * 50 + 25) {
                    if (cells[i][j].color == Color.BLACK) {
                        cells[i][j].color = Color.RED;
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
        // TODO Auto-generated method stub
        
    }


    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }
}
