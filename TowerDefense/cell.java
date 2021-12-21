import java.awt.*;

public class cell {
    int x;
    int y;
    int width;
    int height;
    Color color = Color.BLACK;

    public cell(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void hover() {
        System.out.println("Hovering over cell" + x + "," + y);
    }
    
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillRect(x * width, y * height + 25, width, height);
        g.setColor(Color.WHITE);
        g.drawRect(x*width, y*height + 25, width, height);
    }
}
