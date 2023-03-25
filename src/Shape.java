
import java.awt.*;
import java.io.Serializable;

public abstract class Shape implements Serializable {
    int x1, x2, y1, y2;
    Color color;
    float width;
    boolean fill=false;

    public Shape(int x1, int x2, int y1, int y2, Color color, float width) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.color = color;
        this.width = width;
    }

    public void setFill(boolean b){
        this.fill=b;
    }

    public void setColor(Color color){
        this.color=color;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public void adjust(int x1, int x2, int y1, int y2) { // from left top to right bottom
        int xmin = Math.min(x1, x2);
        int xmax = Math.max(x1, x2);
        int ymin = Math.min(y1, y2);
        int ymax = Math.max(y1, y2);
        this.x1 = xmin;
        this.y1 = ymin;
        this.x2 = xmax;
        this.y2 = ymax;
    }

    public void move(int dx, int dy) {
        x1 += dx;
        x2 += dx;
        y1 += dy;
        y2 += dy;
    }

    public void changeSize(int flag) {// enlarge: flag=1 shrink:flag=0
        // int size_diff = 2 * flag;
        double size_diff;
        if (x1 < x2) {
            size_diff = (x2 - x1) * 0.1 * flag;
            x1 -= size_diff;
            x2 += size_diff;
        } else {
            size_diff = (x1 - x2) * 0.1 * flag;
            x2 -= size_diff;
            x1 += size_diff;
        }
        if (y1 < y2) {
            size_diff = (y2 - y1) * 0.1 * flag;
            y1 -= size_diff;
            y2 += size_diff;
        } else {
            size_diff = (y1 - y2) * 0.1 * flag;
            y2 -= size_diff;
            y1 += size_diff;
        }
    }

    public abstract void draw(Graphics g);

    public abstract boolean isSelected(int x, int y);

}
