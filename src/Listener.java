
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.*;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Listener implements ActionListener, MouseListener, MouseMotionListener {
    private Shape selected;
    String state;
    View view;
    Color color;
    int x1, x2, y1, y2;
    Graphics graphic;
    String text;
    // ArrayList<Shape> localshape = new ArrayList<>();

    public Listener(View v) {
        view = v;
        color = Color.BLACK;
    }

    public void setG(Graphics g) {

        this.graphic = g;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        x1 = e.getX();
        y1 = e.getY();
        selected = null;
        switch (state) {
        case "select": {
            for (Shape s : view.shapes) {
                if (s.isSelected(x1, y1)) {
                    selected = s;
                    break;
                }
            }
            break;
        }
        case "line": {
            selected = new Line(x1, x1, y1, y1, color, 3);
            view.shapes.add(selected);
            break;
        }
        case "rect": {
            selected = new Rectangle(x1, x1, y1, y1, color, 3);
            view.shapes.add(selected);
            break;
        }
        case "circle": {
            selected = new Circle(x1, x1, y1, y1, color, 3);
            view.shapes.add(selected);
            break;
        }
        case "text": {
            selected = new Text(x1, x1, y1, y1, color, 3, text);
            view.shapes.add(selected);
            break;
        }
        default: {
            // selected = null;
            break;
        }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        x2 = e.getX();
        y2 = e.getY();
        switch (state) {
        case "line":
        case "rect":
        case "circle":
        case "text": {
            selected.setX2(x2);
            selected.setY2(y2);
            view.repaint();
            break;
        }
        
        case "select": {
            if (selected != null) {
                selected.move(x2 - x1, y2 - y1);
                view.repaint();
            }
            x1 = x2;
            y1 = y2;
        }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String cmd = e.getActionCommand();
        switch (cmd) {
        case "打开": {
            try {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                chooser.setDialogTitle("请打开cad文件");
                File file = chooser.getSelectedFile();
                if (file == null) {
                    JOptionPane.showMessageDialog(null, "文件路径错误或未选择文件！");
                } else {
                    ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                    view.shapes = (ArrayList<Shape>) (in.readObject());
                    view.repaint();
                    in.close();
                }
                break;
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "文件打开失败！");
            }
        }
        case "保存": {
            try {
                JFileChooser chooser = new JFileChooser();
                chooser.showSaveDialog(null);
                chooser.setDialogTitle("保存文件");
                File file = chooser.getSelectedFile();
                if (file == null) {
                    JOptionPane.showMessageDialog(null, "文件路径错误！");
                } else {
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                    out.writeObject(view.shapes);
                    out.close();
                    JOptionPane.showMessageDialog(null, "保存成功！");
                }
                break;
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "保存失败！");
            }
        }
        case "选定": {
            state = "select";
            selected = null;
            break;
        }
        case "放大": {
            if (selected != null)
                selected.changeSize(1);
            view.repaint();
            break;
        }
        case "缩小": {
            if (selected != null)
                selected.changeSize(-1);
            view.repaint();
            break;
        }
        case "加粗": {
            if (selected != null)
                selected.width++;
            view.repaint();
            break;
        }
        case "变细": {
            if (selected != null)
                if (selected.width > 1)
                    selected.width--;
            view.repaint();
            break;
        }
        case "填充":{
            if (selected!= null){
                selected.setFill(!selected.fill);                
            }
            view.repaint();
            break;
        }
        case "删除":{
            if (selected != null){
                view.shapes.remove(selected);
                state = "select";
                selected=null;
            }
            view.repaint();
            break;
        }
        case "清屏": {
            view.clear();
            break;
        }
        case "Line": {
            state = "line";
            break;
        }
        case "Rect": {
            state = "rect";
            break;
        }
        case "Circle": {
            state = "circle";
            break;
        }
        case "Text": {
            state = "text";
            text = JOptionPane.showInputDialog("请输入:");
            break;
        }
        
        case "black":
        case "blue":
        case "orange":
        case "green":
        case "magenta":
        case "red":
        case "pink":
        case "purple": {
            if (selected != null) {
                for (int i = 0; i < CAD.colorName.length; i++) {
                    if (cmd.equals(CAD.colorName[i])) {
                        color = CAD.colorArray[i];
                        selected.setColor(color);
                        
                        view.repaint();
                        state = "select";
                        selected=null;
                        break;
                    }
                }
            }
        }
        default:
            state = "none";
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        x2 = e.getX();
        y2 = e.getY();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}
