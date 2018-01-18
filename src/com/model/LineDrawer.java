/**
 * LineDrawer.java at 2018年1月18日
 */
package com.model;

import java.awt.Point;
import com.fream.GamePanel;

/**
 * 用于动态绘制路径线的内部类，通过线程启动绘制
 * 
 * @author PCF
 */
public class LineDrawer implements Runnable
{
    private PathPoint[] points;// 路径点数组
    private Picture b1, b2;// 待连接的图片
    private GamePanel gp;// 待绘制的面板

    public LineDrawer(GamePanel gp, PathPoint[] points, Picture b1, Picture b2)
    {
        this.points = points;
        this.b1 = b1;
        this.b2 = b2;
        this.gp = gp;
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

        Line l = new Line(gp, ps, b1, b2);// 用路径点创建路径线
        synchronized (GamePanel.class)
        {
            gp.getLines().add(l);// 将路径线加入路径线列表
        }
        gp.startTimer();
    }

    void transPoint(Point p1, Point p2, Point target, int flag)
    {
        // 两点同一列
        if (p1.getX() == p2.getX())
        {

            if (p1.getY() < p2.getY())
            {
                target.y += flag * gp.height / 2;
            } else
            {
                target.y -= flag * gp.height / 2;
            }
        }
        // 两点同一行
        else
        {
            if (p1.getX() < p2.getX())
            {
                target.x += flag * gp.width / 2;
            } else
            {
                target.x -= flag * gp.width / 2;
            }
        }
    }

}
