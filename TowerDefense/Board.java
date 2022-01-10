import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Board extends JFrame implements MouseListener {
    JPanel panel = new JPanel();
    cell[][] cells = new cell[10][10];
    javaEnemy jEnemy;
    int hp = 10;
    public static final int UNIT_SIZE = 50;
    public static int[][] cellspathCords = {{2, 0}, {2, 1}, {2, 2}, {3, 2}, {4, 2}, {4, 3}, {4, 4}, {3, 4}, {2, 4}, {2, 5}, {2, 6}, {2, 7}, {3, 7}, {4, 7}, {5, 7}, {6, 7}, {7, 7}, {7, 8}, {7, 9}};

    public Board() throws IOException, InterruptedException {

        boardGrid();
        cellspath();
        
        jEnemy = new javaEnemy();
        this.add(jEnemy);
        setSize(500, 525);
        add(panel);
        setVisible(true);
        setResizable(false);
        addMouseListener(this);
        setLocationRelativeTo(null);

        while (true){
            repaint();
            move();
            Thread.sleep(10);
        }
    }

    public void move(){
        jEnemy.move();
    }

    public void boardGrid(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = new cell(i, j, UNIT_SIZE, UNIT_SIZE);
            }
        }
    }

    public void cellspath(){
        for (int k = 0; k < cellspathCords.length; k++){
            cells[cellspathCords[k][0]][cellspathCords[k][1]].color = Color.LIGHT_GRAY;
        }
    }

    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (x > i * UNIT_SIZE && x < (i + 1) * UNIT_SIZE && y > j * UNIT_SIZE + (int)(UNIT_SIZE/2)+1 && y < (j + 1) * UNIT_SIZE + (int)(UNIT_SIZE/2)+1) {
                    if (cells[i][j].color == Color.BLACK) {
                        cells[i][j].color = Color.RED;
                    }
                }
            }
        }
    }

    public void paint(Graphics g) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j].draw(g);
            }
        }
        jEnemy.draw(g);
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
