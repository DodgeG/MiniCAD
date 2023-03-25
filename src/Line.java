
import java.awt.*;

public class Line extends Shape {

    public Line(int x1, int x2, int y1, int y2, Color color, float width) {
        super(x1, x2, y1, y2, color, width);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(width));
        g2.setColor(color);
        g2.drawLine(x1, y1, x2, y2);
    }

    @Override
    public boolean isSelected(int x, int y) {
        // TODO Auto-generated method stub
        int xmin = Math.min(x1, x2);
        int xmax = Math.max(x1, x2);
        int ymin = Math.min(y1, y2);
        int ymax = Math.max(y1, y2);
        if (x >= xmin && x <= xmax && y >= ymin && y <= ymax && dist(x, y) < 10.0) {
            return true;
        }
        return false;
    }

    public double dist(int x, int y) {
        double k = (y2 - y1) * 1.0 / (x2 - x1);
        double b = y2 - k * x2;
        double d = Math.abs((k * x - y + b) / (Math.sqrt(k * k + 1)));
        return d;
    }

}
