/**
 * GamePanel.java at 2018年1月18日
 */
package com.fream;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
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
import com.model.PanelInfo;
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
    public PanelInfo gameinfo;// 游戏窗体相关信息
    int level = gameinfo.getLevel();
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

    public GamePanel()
    {
        gameinfo = new PanelInfo(70, 70, 8, 10, 800, 700, 1, 1);
        ibs = IconManager.getRandomIcons(gameinfo, gameinfo.getRows() * gameinfo.getColumns(),
                maps);// 获取随机游戏图片列表
        this.setPictures(new Picture[gameinfo.getRows()][gameinfo.getColumns()]);


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
                        repaint();// 重绘
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
        for (Picture[] button : getPictures())
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
     * 绘制背景 （非 Javadoc）
     * 
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);// 绘制背景图片
        // g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
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
     * 将随机获得的游戏图片列表放入游戏图片数组
     */
    private void initPictures()
    {
        List<Picture> list = new ArrayList<Picture>(ibs);// 创建一个游戏图片列表的副本
        // 随机将列表中的游戏图片加入到游戏图片数组，并设置其行和列数
        for (int row = 0; row < getPictures().length; row++)
        {
            for (int col = 0; col < getPictures()[row].length; col++)
            {
                getPictures()[row][col] = list.remove((int) (Math.random() * list.size()));// 通过remove动态的确定随机范围
                getPictures()[row][col].setRow(row);
                getPictures()[row][col].setCol(col);
            }
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
        int row = (y - gameinfo.getTop()) / (gameinfo.getBORDER() + gameinfo.getHeight());// 将坐标转换为行数
        int col = (x - gameinfo.getLeft()) / (gameinfo.getBORDER() + gameinfo.getWidth());// 将坐标转换为列数
        return getPictures()[row][col];
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
        int right = gameinfo.getLeft()
                + gameinfo.getColumns() * (gameinfo.getBORDER() + gameinfo.getWidth());
        int bottom = gameinfo.getTop()
                + gameinfo.getRows() * (gameinfo.getBORDER() + gameinfo.getHeight());

        return x <= gameinfo.getLeft() || x - right >= 0 || y <= gameinfo.getTop() || y - bottom >= 0;
    }

    public void isFinished()
    {
        if (maps.size() > 0)
        {
            return;
        }
        JOptionPane.showMessageDialog(this, "进入" + level + "关");
        level++;
        ibs.clear();
        ibs = IconManager.getRandomIcons(gameinfo, gameinfo.getRows() * gameinfo.getColumns(),
                maps);
        initPictures();
    }

    /**
     * ����
     *
     * @param p
     * @param flag
     */
    public void down(Picture p, boolean flag)
    {
        int row = p.getRow();
        int col = p.getCol();
        while (true)
        {
            if (row <= 0)
            {
                break;
            }
            Picture up = getPictures()[row - 1][col];
            if (up.isVisible() || flag)
            {
                getPictures()[row - 1][col] = p;
                getPictures()[row][col] = up;
                up.setRow(row);
                row--;
            } else
            {
                break;
            }
        }
    }

    /**
     * ���к������
     */
    public void downAll()
    {
        for (int col = 0; col < gameinfo.getColumns(); col++)
        {
            for (int row = 0; row < gameinfo.getRows(); row++)
            {
                if (!getPictures()[row][col].isVisible())
                {
                    down(getPictures()[row][col], false);
                }
            }
        }
    }

    /**
     * ����
     *
     * @param p
     * @param flag
     */
    public void left(Picture p, boolean flag)
    {
        int row = p.getRow();
        int col = p.getCol();
        while (true)
        {
            if (col >= gameinfo.getColumns() - 1)
            {
                break;
            }
            Picture up = getPictures()[row][col + 1];
            if (up.isVisible() || flag)
            {
                getPictures()[row][col + 1] = p;
                getPictures()[row][col] = up;
                up.setCol(col);
                col++;
            } else
            {
                break;

            }
        }
    }

    public void leftAll()
    {
        for (int row = 0; row < gameinfo.getRows(); row++)
        {
            for (int col = gameinfo.getColumns() - 1; col >= 0; col--)
            {
                if (!getPictures()[row][col].isVisible())
                {
                    left(getPictures()[row][col], false);
                }
            }
        }
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

    // /**
    // * 获取可以连接两个图片的点
    // *
    // * @param b1
    // * @param b2
    // * @return 满足连接条件返回路径点数组，否则返回null
    // */
    // public PathPoint[] getPoints(Picture b1, Picture b2)
    // {
    // if (b1.getRow() == b2.getRow())
    // {// 同一行
    // return wayRow(b1, b2);
    // } else if (b1.getCol() == b2.getCol())
    // {// 同一列
    // return wayCol(b1, b2);
    // } else
    // {
    // PathPoint[] cp = wayRow(b1, b2);
    // return cp == null ? wayCol(b1, b2) : cp;
    // }
    // }
    //
    // /**
    // * 行路径算法
    // *
    // * @param b1
    // * @param b2
    // * @return
    // */
    // PathPoint[] wayRow(Picture b1, Picture b2)
    // {
    //
    // Picture left = b1.getCol() < b2.getCol() ? b1 : b2;// 左边的图片
    // Picture right = (left == b1) ? b2 : b1;// 右边的图片
    //
    // int center = getCenter(b1.getRow(), b2.getRow());// 行数
    // int flag = -1;
    //
    // row: for (int i = 0; i < gameinfo.getRows() + 2; i++)
    // {
    // if (center + flag * i < -1)
    // {
    // center += 1;
    // } else if (center + flag * i > gameinfo.getRows())
    // {
    // center -= 1;
    // } else
    // {
    // center += flag * i;
    // flag = -flag;
    // }
    //
    // if (center > -1 && center < gameinfo.getRows())
    // {
    // for (int x = left.getCol(); x <= right.getCol(); x++)
    // {
    // String id = getPictures()[center][x].getId();
    // if (id.equals(b1.getId()) || id.equals(b2.getId()))
    // {
    // continue;
    // }
    // if (getPictures()[center][x].isVisible())
    // {
    // continue row;
    // }
    // }
    // }
    // int r = left.getRow();
    //
    // int beg = r < center ? r : center;
    // int end = r < center ? center : r;
    // for (int step = beg + 1; step < end; step++)
    // {
    // if (getPictures()[step][left.getCol()].isVisible())
    // {
    // continue row;
    // }
    // }
    //
    // r = right.getRow();
    // beg = r < center ? r : center;
    // end = r < center ? center : r;
    // for (int step = beg + 1; step < end; step++)
    // {
    // if (getPictures()[step][right.getCol()].isVisible())
    // {
    // continue row;
    // }
    // }
    //
    // PathPoint[] points;
    // if (left.getRow() == center && right.getRow() == center)
    // {
    // points = new PathPoint[2];
    // points[0] = new PathPoint(left, gameinfo);
    // points[1] = new PathPoint(right, gameinfo);
    // } else if (left.getRow() == center)
    // {
    // points = new PathPoint[3];
    // points[0] = new PathPoint(left, gameinfo);
    // points[1] = new PathPoint(center, right.getCol(), gameinfo);
    // points[2] = new PathPoint(right, gameinfo);
    // } else if (right.getRow() == center)
    // {
    // points = new PathPoint[3];
    // points[0] = new PathPoint(left, gameinfo);
    // points[1] = new PathPoint(center, left.getCol(), gameinfo);
    // points[2] = new PathPoint(right, gameinfo);
    // } else
    // {
    // points = new PathPoint[4];
    // points[0] = new PathPoint(left, gameinfo);
    // points[1] = new PathPoint(center, left.getCol(), gameinfo);
    // points[2] = new PathPoint(center, right.getCol(), gameinfo);
    // points[3] = new PathPoint(right, gameinfo);
    // }
    //
    // if (b2.getId().equals(left.getId()))
    // {
    // int length = points.length;
    // PathPoint[] cp = new PathPoint[length];
    // for (int index = 0; index < length; index++)
    // {
    // cp[index] = points[length - index - 1];
    // }
    // return cp;
    // }
    // return points;
    // }
    // return null;
    // }
    //
    // /**
    // * 列路径算法
    // *
    // * @param b1
    // * @param b2
    // * @return
    // */
    // PathPoint[] wayCol(Picture b1, Picture b2)
    // {
    // Picture top = b1.getRow() < b2.getRow() ? b1 : b2;
    // Picture bottom = (top == b1) ? b2 : b1;
    //
    // int middle = getCenter(b1.getCol(), b2.getCol());
    // int flag = -1;
    //
    // row: for (int i = 0; i < gameinfo.getColumns() + 2; i++)
    // {
    // if (middle + flag * i < -1)
    // {
    // middle += 1;
    // } else if (middle + flag > gameinfo.getRows())
    // {
    // middle -= 1;
    // } else
    // {
    // middle += flag * i;
    // flag = -flag;
    // }
    //
    // if (middle > -1 && middle < gameinfo.getColumns())
    // {
    // for (int x = top.getRow(); x <= bottom.getRow(); x++)
    // {
    // String id = getPictures()[x][middle].getId();
    // if (id.equals(b1.getId()) || id.equals(b2.getId()))
    // {
    // continue;
    // }
    // if (getPictures()[x][middle].isVisible())
    // {
    // continue row;
    // }
    // }
    // }
    // int r = top.getCol();
    // int beg = r < middle ? r : middle;
    // int end = r < middle ? middle : r;
    // for (int step = beg + 1; step < end; step++)
    // {
    // if (getPictures()[top.getRow()][step].isVisible())
    // {
    // continue row;
    // }
    // }
    //
    // r = bottom.getCol();
    // beg = r < middle ? r : middle;
    // end = r < middle ? middle : r;
    // for (int step = beg + 1; step < end; step++)
    // {
    // if (getPictures()[bottom.getRow()][step].isVisible())
    // {
    // continue row;
    // }
    // }
    //
    // PathPoint[] points;
    // if (top.getCol() == middle && bottom.getCol() == middle)
    // {
    // points = new PathPoint[2];
    // points[0] = new PathPoint(top, gameinfo);
    // points[1] = new PathPoint(bottom, gameinfo);
    // } else
    // {
    // points = new PathPoint[4];
    // points[0] = new PathPoint(top, gameinfo);
    // points[1] = new PathPoint(top.getRow(), middle, gameinfo);
    // points[2] = new PathPoint(bottom.getRow(), middle, gameinfo);
    // points[3] = new PathPoint(bottom, gameinfo);
    // }
    //
    // if (b2.getId().equals(top.getId()))
    // {
    // int length = points.length;
    // PathPoint[] cp = new PathPoint[length];
    // for (int index = 0; index < length; index++)
    // {
    // cp[index] = points[length - index - 1];
    // }
    // return cp;
    // }
    // return points;
    //
    // }
    //
    // return null;
    // }

    /**
     * 提示
     */
    public void tishi()
    {
        Picture[] maped = getMappedButtons();
        if (maped == null)
        {
            JOptionPane.showMessageDialog(this, "�����������ˣ���");
            return;
        }
        if (front_click != null)
        {
            front_click.setSelected(false);
            repaint(front_click.getX(), front_click.getY(),
                    gameinfo.getWidth() + gameinfo.getBORDER(),
                    gameinfo.getHeight() + gameinfo.getBORDER());
            front_click = null;
        }
        maped[0].setSelected(true);
        maped[1].setSelected(true);
        repaint(maped[0].getX(), maped[0].getY(), gameinfo.getWidth() + gameinfo.getBORDER(),
                gameinfo.getHeight() + gameinfo.getBORDER());
        repaint(maped[1].getX(), maped[1].getY(), gameinfo.getWidth() + gameinfo.getBORDER(),
                gameinfo.getHeight() + gameinfo.getBORDER());
    }

    /**
     * 重排
     */
    public void reSort()
    {
        initPictures();
        switch (level)
        {
            case 2:
                downAll();
                break;
            case 3:
                leftAll();
                break;
        }
        repaint();
    }

    Picture[] getMappedButtons()
    {
        for (List<Picture> l : maps.values())
        {
            for (int i = 0; i < l.size(); i++)
            {
                for (int j = i + 1; j < l.size(); j++)
                {
                    if (PathAlgo.getPoints(l.get(i), l.get(j), this) != null)
                    {
                        return new Picture[] {l.get(i), l.get(j)};
                    }
                }
            }
        }
        return null;
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
        new Thread(new Drawer(this, points, b1, b2)).start();
    }

    /**
     * 返回图片
     * 
     * @return
     */
    public Picture[][] getPictures()
    {
        return pictures;
    }


    /**
     * @param pictures 要设置的 pictures
     */
    public void setPictures(Picture[][] pictures)
    {
        this.pictures = pictures;
    }

    /**
     * 用于动态绘制路径线的内部类，通过线程启动绘制
     * 
     * @author PCF
     */
    class Drawer implements Runnable
    {
        private PathPoint[] points;// 路径点数组
        private Picture b1, b2;// 待连接的图片
        private GamePanel panel;// 待绘制的面板

        Drawer(GamePanel panel, PathPoint[] points, Picture b1, Picture b2)
        {
            this.points = points;
            this.b1 = b1;
            this.b2 = b2;
            this.panel = panel;
        }

        @Override
        public void run()
        {
            Point[] ps = new Point[points.length];// 数组储存路径点在界面中对应的坐标点
            for (int i = 0; i < points.length; i++)
            {
                ps[i] = points[i].getPointOnMain();
            }
            int index = ps.length - 1;

            transPoint(ps[0], ps[1], ps[0], 1);
            transPoint(ps[index - 1], ps[index], ps[index], -1);

            Line l = new Line(panel, ps, b1, b2);// 用路径点创建路径线
            synchronized (GamePanel.class)
            {
                lines.add(l);// 将路径线加入路径线列表
            }
            startTimer();
        }

        void transPoint(Point p1, Point p2, Point target, int flag)
        {
            // 两点同一列
            if (p1.getX() == p2.getX())
            {

                if (p1.getY() < p2.getY())
                {
                    target.y += flag * gameinfo.getHeight() / 2;
                } else
                {
                    target.y -= flag * gameinfo.getHeight() / 2;
                }
            }
            // 两点同一行
            else
            {
                if (p1.getX() < p2.getX())
                {
                    target.x += flag * gameinfo.getWidth() / 2;
                } else
                {
                    target.x -= flag * gameinfo.getWidth() / 2;
                }
            }
        }
    }


}
