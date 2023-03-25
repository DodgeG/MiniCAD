
import java.awt.*;
import javax.swing.*;

public class CAD extends JFrame {
    public static Color[] colorArray = { Color.BLACK, new Color(30, 144, 255), new  Color(255, 140, 0), new Color(50, 205, 50), Color.magenta, new Color(238, 44, 44),
            Color.PINK, new Color(138, 43, 226) };
    public static String[] colorName = { "black", "blue", "orange", "green", "magenta", "red", "pink", "purple" };
    // static 便于其他类里使用
    public String[] tools = { "选定", "放大", "缩小", "加粗", "变细","填充","删除", "清屏" };
    public String[] SHAPE = {"线段","矩形","圆形","文本","橡皮"};
    Graphics g;

    public CAD() {
        setTitle("miniCAD");
        setLayout(new BorderLayout(5, 5));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        View view = new View();
        add(view, BorderLayout.CENTER);

        // menu: save and open
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("文件");
        JMenuItem item1 = new JMenuItem("打开");
        JMenuItem item2 = new JMenuItem("保存");
        // need to fill in
        item1.addActionListener(view.ls);
        item2.addActionListener(view.ls);
        menu1.add(item1);
        menu1.add(item2);
        menuBar.add(menu1);
        setJMenuBar(menuBar);

        // west: shape and color
        JPanel west = new JPanel();
        west.setPreferredSize(new Dimension(150, 500));
        west.setLayout(new GridLayout((4 + colorArray.length)-6, 2, 0, 0)); // 4 shape + 4*2 color
        for (int i = 0; i < 4 + colorArray.length; i++) {
            JButton button = new JButton();
            if (i <= 3) {// shape
                button.setPreferredSize(new Dimension(60, 60));
                button.setText(SHAPE[i]);
                button.setBackground(new Color(240, 255, 240));
                ImageIcon icon = new ImageIcon("image/line.png");
                ;
                switch (i) {
                    case 0:
                        icon = new ImageIcon("image/line.png");
                        button.setActionCommand("Line");
                        break;
                    case 1:
                        icon = new ImageIcon("image/rect.png");
                        button.setActionCommand("Rect");
                        break;
                    case 2:
                        icon = new ImageIcon("image/circle.png");
                        button.setActionCommand("Circle");
                        break;
                    case 3:
                        icon = new ImageIcon("image/text.png");
                        button.setActionCommand("Text");
                        break;
                    
                }
                
                // 自适应图标
                Image temp = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                icon = new ImageIcon(temp);
                button.setIcon(icon);
            } else {// color
                
                button.setPreferredSize(new Dimension(80, 60));
                button.setBackground(colorArray[i - 4]);
                button.setActionCommand(colorName[i - 4]);
                button.setText(colorName[i - 4]);
                button.setForeground(Color.white);
            }
            button.setBorder(BorderFactory.createRaisedBevelBorder());// 凸效果
            // need to fill in
            button.addActionListener(view.ls);
            button.setOpaque(true);
            west.add(button);
        }
        // west.add(new JButton());
        add(west, BorderLayout.WEST);

        // north: function
        JPanel north = new JPanel();
        north.setPreferredSize(new Dimension(100, 80));
        north.setLayout(new GridLayout(1, tools.length));
        for (int i = 0; i < tools.length; i++) {
            ImageIcon icon = new ImageIcon("image/thin.png");
            switch(i){
                case 0:{
                    icon=new ImageIcon("image/selected.png");
                    break;
                }
                case 1:{
                    icon=new ImageIcon("image/zoom-in.png");
                    break;
                }
                case 2:{
                    icon=new ImageIcon("image/zoom-out.png");
                    break;
                }
                case 3:{
                    icon=new ImageIcon("image/coarsen.png");
                    break;
                }
                case 4:{
                    icon=new ImageIcon("image/thin.png");
                    break;
                }
                case 5:{
                    icon=new ImageIcon("image/fill.png");
                    break;
                }
                case 6:{
                    icon=new ImageIcon("image/delete.png");
                    break;
                }
                case 7:{
                    icon=new ImageIcon("image/clear.png");
                    break;
                }
            }
            JButton button = new JButton(tools[i]);
            button.setActionCommand(tools[i]);
            Image temp = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            icon = new ImageIcon(temp);
            button.setIcon(icon);
            button.setPreferredSize(new Dimension(60, 60));
            button.setBackground(new Color(240, 255, 240));
            
            button.setBorder(BorderFactory.createRaisedBevelBorder());// 凸效果
            // need to fill in
            button.addActionListener(view.ls);
            button.setBounds(50,100,5,5);
            north.add(button);
            
        }
        add(north, BorderLayout.NORTH);

        // other
        setVisible(true);
        g = view.getGraphics();
        view.ls.setG(g);
    }

    public static void main(String[] args) {
        CAD frame = new CAD();
    }
}