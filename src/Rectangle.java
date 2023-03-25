
import java.awt.*;

public class Rectangle extends Shape {

    public Rectangle(int x1, int x2, int y1, int y2, Color color, float width) {
        super(x1, x2, y1, y2, color, width);
        // TODO Auto-generated constructor stub

    }

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(width));
        g2.setColor(color);
        int xmin = Math.min(x1, x2);
        int xmax = Math.max(x1, x2);
        int ymin = Math.min(y1, y2);
        int ymax = Math.max(y1, y2);
        if (this.fill==false)
        g2.drawRect(xmin, ymin, xmax - xmin, ymax - ymin);
        else
        g2.fillRect(xmin, ymin, xmax - xmin, ymax - ymin);
    }

    @Override
    public boolean isSelected(int x, int y) {
        // TODO Auto-generated method stub
        adjust(x1, x2, y1, y2);
        if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
            return true;
        }
        return false;
    }

}
