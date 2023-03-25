
import java.awt.*;

public class Text extends Shape {
    String s;

    public Text(int x1, int x2, int y1, int y2, Color color, float width, String str) {
        super(x1, x2, y1, y2, color, width);
        s = str;
    }

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(width));// 画笔大小
        g2.setColor(color);// 设置画笔颜色
        int xmin = Math.min(x1, x2);
        int xmax = Math.max(x1, x2);
        int ymin = Math.min(y1, y2);
        int ymax = Math.max(y1, y2);
        Font font = new Font("宋体", Font.PLAIN, ymax - ymin);
        
        g2.setFont(font);
        g2.drawString(s, xmin, ymax);
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
