/**
 * GamePanel.java at 2018年1月18日
 */
package com.fream;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import com.algorithm.PathAlgo;
import com.icons.IconManager;
import com.model.Line;
import com.model.LineDrawer;
import com.model.PathPoint;
import com.model.Picture;

/**
 * 游戏主界面
 * 
 * @author PCF
 */
public class GamePanel extends JPanel implements ActionListener
{
    private static final long serialVersionUID = 5556509107728753988L;

    java.util.List<Line> lines = new ArrayList<Line>();// 路径线列表
    public int width = 70;// 游戏图片的宽度
    public int height = 70;// 游戏图片的高度
    public int rows, columns;// 图片行数和列数
    private int panelWidth, panelHeight;// 游戏主面板宽度和高度
    public int BORDER = 1;// 游戏图片间的间距
    private int level = 1;// 游戏关数
    public int left, top;// 游戏图片的开始排列的位置
    private int lenght;// 游戏图片的种类数
    private int kind;// 游戏图片的种类
    // 游戏背景图片
    Image image = new ImageIcon("res/icons/bg11.jpg").getImage();
    // 游戏暂停背景图片
    Image img_stop = new ImageIcon("res/icons/stop.jpg").getImage();


    Timer timer;// 计时器对象
    private int delay = 12;
    private boolean stoped;
    private boolean started;// 开始标志
    private Picture[][] pictures;// 游戏图片数组
    java.util.List<Picture> ibs;// 游戏图片列表
    private Picture front_click;// 上一次点击的游戏图片

    Map<String, List<Picture>> maps = new HashMap<String, List<Picture>>();// 图片的字符串和相同游戏图片列表的隐射
    private Image offImage;// 双缓冲图像
    boolean pause;// 暂停标志

    /**
     * 初始化游戏主界面
     * 
     * @param rows 游戏图片的行数
     * @param columns 游戏图片的列数
     * @param lenght 游戏图片的种类数
     * @param kind 游戏图片的种类
     */
    public GamePanel(int rows, int columns, int lenght, int kind)
    {
        this.rows = rows;
        this.columns = columns;
        this.panelWidth = columns * 80;
        this.panelHeight = rows * 90 - 20;
        this.lenght = lenght;
        this.kind = kind;

        left = (panelWidth - BORDER - columns * (width + 1)) / 2;// 计算游戏图片开始排列的左边坐标
        top = (panelHeight - BORDER - (height + 1) * rows) / 2;// 计算游戏图片开始排列的顶部坐标

        ibs = IconManager.getRandomIcons(this, lenght, kind, rows * columns, maps);// 获取随机游戏图片列表
        this.pictures = new Picture[rows][columns];
        initPictures();// 排列游戏图片组并初始化

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isOut(e))
                {// 点击未超出范围
                    if (front_click != null)
                    {// 之前有点击则不将它选中，并清除其储存的信息
                        front_click.setSelected(false);
                        front_click = null;
                        repaint();// 重绘，去除其选中特效
                    }
                    return;
                }
                Picture nowa = getPicture(e);// 获取点击的游戏图片
                if (nowa != null && nowa.isVisible())
                {// 能获取游戏图片并且它未被消除则点击它
                    doClick(nowa);
                    repaint();// 重绘
                }
            }
        });
    }

    /**
     * 将已获得的游戏图片列表放入游戏图片数组，
     */
    private void initPictures()
    {
        List<Picture> list = new ArrayList<Picture>(ibs);// 创建一个游戏图片列表的副本，便于重新排序
        // 随机将列表中的游戏图片加入到游戏图片数组，并设置其行和列数
        for (int row = 0; row < pictures.length; row++)
        {
            for (int col = 0; col < pictures[row].length; col++)
            {
                pictures[row][col] = list.remove((int) (Math.random() * list.size()));// 通过remove动态的确定随机范围
                pictures[row][col].setRow(row);
                pictures[row][col].setCol(col);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        synchronized (GamePanel.class)
        {
            Iterator<Line> it = lines.iterator();

            while (it.hasNext())
            {
                Line l = it.next();
                if (l.isFlag())
                {
                    it.remove();
                }
            }
        }
        if (lines.size() > 0 || !stoped)
        {
            this.repaint();
        }
        if (lines.size() == 0 && started)
        {
            timer.stop();
            started = false;
            isFinished();
        }
    }


    /*
     * （非 Javadoc）
     * 
     * @see javax.swing.JComponent#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics g)
    {
        if (offImage == null)
        {
            offImage = createImage(getWidth(), getHeight());// 创建当前界面的双缓冲存储区
        }
        Graphics offg = offImage.getGraphics();// 获取图形缓冲区的图形上下文

        if (pause)
        {
            offg.drawImage(img_stop, 0, 0, this.getWidth(), this.getHeight(), this);// 绘制暂停界面在图形缓冲区
            g.drawImage(offImage, 0, 0, this);// 在界面上绘制图形缓冲区
            return;
        }

        super.paint(offg);
        // 在图形缓冲区绘制各个图片
        for (Picture[] button : pictures)
        {
            for (Picture p : button)
            {
                p.paint(offg);
            }
        }
        // 图形缓冲区绘制路径线
        if (lines.size() > 0)
        {
            synchronized (GamePanel.class)
            {
                for (Line l : lines)
                {
                    l.draw(offg);
                }
            }
            stoped = false;
        } else
        {
            stoped = true;
        }
        g.drawImage(offImage, 0, 0, this);// 绘制缓冲区
    }

    /*
     * 非 Javadoc）
     * 
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);// 绘制背景图片
    }

    /**
     * 点击游戏图片响应事件
     *
     * @param ib
     */
    public void doClick(Picture ib)
    {
        ib.setSelected(true);// 设置游戏图片被选中
        // 如果之前有选中的，并且这次选中的与之前被选中的不是同一个，则清除被选中效果
        if (front_click != null && !ib.getId().equals(front_click.getId()))
        {
            front_click.setSelected(false);
        }
        // 如果之前有选中的，两次选中的不是同一个对象，并且两个是同一种图片
        if (front_click != null && front_click != ib
                && front_click.getIconId().equals(ib.getIconId()))
        {
            PathPoint[] cp = PathAlgo.getPoints(front_click, ib, this);// 判断是否能连接，并获取路径点
            // 能连接则绘制路径线
            if (cp != null)
            {
                drawPath(cp, front_click, ib);// 绘制路径线
                List<Picture> list = maps.get(front_click.getIconId());// 获取和选中游戏图片相同的游戏图片列表
                // 移除其中选中的两个游戏图片
                list.remove(front_click);
                list.remove(ib);
                // 如果不存在其他的相同游戏图片则在映射关系中移除此映射
                if (list.size() == 0)
                {
                    maps.remove(front_click.getIconId());
                }
                front_click = null;// 之前选中的置空
            } else
            {
                front_click = ib;
            }
        } else
        {// 没有选对，这次选中的变成之前选中的
            front_click = ib;
        }
    }

    /**
     * 获取点击到的图片对象
     *
     * @param e
     * @return
     */
    private Picture getPicture(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();
        if (isOut(e))
        {
            return null;
        }
        int row = (y - top) / (BORDER + height);// 将坐标转换为行数
        int col = (x - left) / (BORDER + width);// 将坐标转换为列数
        return pictures[row][col];
    }

    /**
     * 判断鼠标点击坐标是否在游戏图片的有效区域
     * 
     * @param e
     * @return
     */
    boolean isOut(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();
        int right = left + columns * (BORDER + width);
        int bottom = top + rows * (BORDER + height);

        return x <= left || x - right >= 0 || y <= top || y - bottom >= 0;
    }


    /**
     * 判断是否完成次游戏关卡
     */
    public void isFinished()
    {
        if (maps.size() > 0)
        {
            return;
        }
        JOptionPane.showMessageDialog(this, "恭喜你，进入第" + level + "关");
        level++;// 关卡加一
        ibs.clear();// 清除游戏图片列表
        ibs = IconManager.getRandomIcons(this, lenght, kind, rows * columns, maps);// 重新获取随机游戏图片列表
        initPictures();
    }

    /**
     * 获取游戏当前关卡
     * 
     * @return
     */
    public int getLevel()
    {
        return level;
    }

    /**
     * 返回游戏图片组
     * 
     * @return
     */
    public Picture[][] getPictures()
    {
        return pictures;
    }

    /**
     * @param pictures 要设置的图片组
     */
    public void setPictures(Picture[][] pictures)
    {
        this.pictures = pictures;
    }

    /**
     * 获取提示信息，并对可获取的提示绘制特效
     */
    public void Ghint()
    {
        Picture[] maped = getMappedButtons();// 获取可以连接的两个游戏图片
        if (maped == null)
        {
            JOptionPane.showMessageDialog(this, "没有可用的提示，请尝试重新排序");
            return;
        }
        if (front_click != null)
        {
            front_click.setSelected(false);// 清除之前的选择
            repaint(front_click.getX(), front_click.getY(), width + BORDER, height + BORDER);// 去除选择特效
            front_click = null;
        }
        // 选中提示的两张图片，并绘制其相关区域的特效
        maped[0].setSelected(true);
        maped[1].setSelected(true);
        repaint(maped[0].getX(), maped[0].getY(), width + BORDER, height + BORDER);
        repaint(maped[1].getX(), maped[1].getY(), width + BORDER, height + BORDER);
    }

    /**
     * 重排图片
     */
    public void reSort()
    {
        initPictures();// 游戏图片列表随机化
        repaint();
    }

    /**
     * 获取两个可以连接的图片
     * 
     * @return
     */
    Picture[] getMappedButtons()
    {
        for (List<Picture> l : maps.values())// 在相同图片列表中遍历
        {
            for (int i = 0; i < l.size(); i++)
            {
                for (int j = i + 1; j < l.size(); j++)
                {
                    if (PathAlgo.getPoints(l.get(i), l.get(j), this) != null)// 可以连接
                    {
                        return new Picture[] {l.get(i), l.get(j)};
                    }
                }
            }
        }
        return null;// 不可以连接则返回空
    }

    /**
     * 暂停游戏
     * 
     * @param b
     */
    public void pause(boolean b)
    {
        pause = b;
        repaint();
    }

    /**
     * 获取两个数的中间值
     * 
     * @param i1
     * @param i2
     * @return
     */
    int getCenter(int i1, int i2)
    {
        int min = i1 < i2 ? i1 : i2;
        int max = i1 < i2 ? i2 : i1;
        return min + (max - min) / 2;
    }

    /**
     * 为游戏面板添加计时器
     */
    public void startTimer()
    {
        if (timer == null)
        {
            timer = new Timer(delay, this);
        }
        if (!started)
        {
            timer.start();// 定时器开始计时
            started = true;
        }
    }

    /**
     * 绘制连接两个图片的路径线
     * 
     * @param points
     * @param b1
     * @param b2
     */
    public void drawPath(PathPoint[] points, Picture b1, Picture b2)
    {
        new Thread(new LineDrawer(this, points, b1, b2)).start();
    }

    /**
     * 获取路径线列表
     * 
     * @return lines
     */
    public java.util.List<Line> getLines()
    {
        return lines;
    }
}
