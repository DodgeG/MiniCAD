
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class View extends JPanel {
    public ArrayList<Shape> shapes = new ArrayList<>();

    Listener ls = new Listener(this);

    public View() {
        setBackground(Color.WHITE);

        // need to fill in
        addMouseListener(ls);
        addMouseMotionListener(ls);
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (Shape shape : shapes) {
            shape.draw(g);
        }
    }

    public void clear() {
        removeAll();
        shapes.clear();
        repaint();
    }
}
