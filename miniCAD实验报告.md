## miniCAD实验报告

姓名：巩德志	学号：3200105088

### 一、实验目的和要求

用Java的awt和swing做一个简单的绘图工具，以CAD的方式操作，能放置直线、矩形、圆和文字，能选中图形，修改参数，如颜色等，能拖动图形和调整大小，可以保存和恢复。

### 二、实验内容

#### 1 整体架构

本程序可以分为四个部分

第一部分包括Shape及其一系列的子类Circle、Line、Rectangle、Text，实现了各种图形的封装

第二部分是View，继承了JPanel类，实现了各种图形的可视化

第三部分是Listener，实现了ActionListener, MouseListener, MouseMotionListener接口，完成了程序的监听

第四部分是CAD，继承了JFrame类，创建了程序窗口，完成了组件的布局

#### 2 UI设计

<img src="https://images-tc.oss-cn-beijing.aliyuncs.com/20221127143053769.png" alt="image" style="zoom:50%;" />

- 上方菜单栏提供了文件的打开和保存
- 上方工具栏实现了各种功能
- 左侧工具栏实现了选择图形类型和颜色
- 空白区域为画布，在此区域作图

#### 3 实现思路

##### 第一部分：Shape及其一系列的子类Circle、Line、Rectangle、Text

父类Shape实现了序列化接口Serializable，用于文件保存和恢复

父类Shape的成员变量如下

```java
	int x1, x2, y1, y2;//坐标
    Color color;//图形颜色
    float width;//边框宽度
    boolean fill=false;//是否被填充
```

实现了以下方法

```java
public void setFill(boolean b)//设置fill
public void setColor(Color color)//设置颜色
public void setX2(int x2)//设置横坐标
public void setY2(int y2)//设置纵坐标
public void adjust(int x1, int x2, int y1, int y2)//重设坐标，保证point1和point2坐标从左上到右下
public void move(int dx, int dy)//移动指定距离
public void changeSize(int flag)//改变尺寸
```

定义了两个抽象方法

```java
public abstract void draw(Graphics g);
public abstract boolean isSelected(int x, int y);
```

draw传入了一个Graphics参数，通过调用Graphics2D的draw和fill实现画未填充的图形和画填充的图形

isSelected传入一个坐标，通过判断当前图形坐标的关系决定图形是否能被选中



##### 第二部分：View

View继承了JPanel作为画布，成员变量包括一个shapes的Arraylist， 一个Listener

View通过调用repaint()来实现刷新画布，另外每次repaint()调用的是paint()，所以在View中重写了paint() 来实现图形的显示

```java
public void paint(Graphics g) {
        super.paint(g);
        for (Shape shape : shapes) {
            shape.draw(g);
        }
    }
```



##### 第三部分：Listener

Listener实现了ActionListener, MouseListener, MouseMotionListener接口，监听事件，对View做出控制

Listener成员变量如下

```java
	private Shape selected;//当前选中的图形
    String state;//当前操作的名称（状态）
    View view;
    Color color;
    int x1, x2, y1, y2;
    Graphics graphic;
    String text;//保存输入的文本
```

Listener重写了以下四个方法

```
public void mousePressed(MouseEvent e)
public void mouseDragged(MouseEvent e)
public void actionPerformed(ActionEvent e)
public void mouseReleased(MouseEvent e)
```

当鼠标按下时，根据当前的state判断是选中还是创建新图形。

当拖动鼠标时，如果state是作图（line，rect，circle，text），那么更新选中图形的坐标，repaint；如果state是选中select，那么传入坐标差，调用shape的move()移动图形，最后repaint()

当鼠标释放时，更新坐标

当点击按钮时，会触发Listener的监听，根据不同的按钮进行不同的操作，操作结束后repaint()刷新画布



##### 第四部分：CAD

CAD继承了JFrame，使用BorderLayout布局，分为四个部分，分别是menubar，north的JPanel，west的JPanel和center的View画布

对每个按钮设置setActionCommand，用于监听

### 三、实验操作与实验结果

1. 点击左侧功能栏的按钮，拖动鼠标绘制四种图形
   <img src="https://images-tc.oss-cn-beijing.aliyuncs.com/20221127175434301.png" alt="image" style="zoom:50%;" />

2. 先点击选中，然后在画布上选中图形，再点击上方功能栏进行修改粗细，放大缩小，改变颜色，填充

   <img src="https://images-tc.oss-cn-beijing.aliyuncs.com/20221127175825771.png" alt="image" style="zoom:50%;" />

3. 先选中图形再点击删除，可以删除该图形。点击清屏可以删除所有图形
   <img src="https://images-tc.oss-cn-beijing.aliyuncs.com/20221127175915190.png" alt="image" style="zoom:50%;" />
   <img src="https://images-tc.oss-cn-beijing.aliyuncs.com/20221127175925963.png" alt="image" style="zoom:50%;" />

4. 保存文件
   <img src="https://images-tc.oss-cn-beijing.aliyuncs.com/20221127180049363.png" alt="image" style="zoom:50%;" />
   <img src="https://images-tc.oss-cn-beijing.aliyuncs.com/20221127180141272.png" alt="image" style="zoom:50%;" />
   删除一个图形
   <img src="https://images-tc.oss-cn-beijing.aliyuncs.com/20221127180250926.png" alt="image" style="zoom:50%;" />
   打开刚刚保存的文件
   <img src="https://images-tc.oss-cn-beijing.aliyuncs.com/20221127180327187.png" alt="image" style="zoom:50%;" />
   恢复原来的图形
   <img src="https://images-tc.oss-cn-beijing.aliyuncs.com/20221127180341397.png" alt="image" style="zoom:50%;" />

