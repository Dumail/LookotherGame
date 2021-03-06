/**
 * Line.java at 2018年1月18日
 */
package com.model;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import com.fream.GamePanel;

/**
 * 路径线
 * 
 * @author PCF
 */
public class Line
{
    Point[] points;// 多个抽象点
    int step = 8;
    int index = 0;
    boolean flag = false;// 是否绘制成功
    Picture b1, b2;// 待连接的两个图片
    static Color c1 = new Color(27, 190, 186);// 设置路线的颜色
    Color c2 = Color.white;// 渐变颜色
    GamePanel gp;
    
    /**
     * 初始化路径线，确定其位置
     * 
     * @param p 游戏面板
     * @param points 路径点数组
     * @param b1 图片1
     * @param b2 图片2
     */
    public Line(GamePanel p, Point[] points, Picture b1, Picture b2)
    {
        this.gp = p;
        this.points = points;
        this.b1 = b1;
        this.b2 = b2;
    }

    /**
     * 判断是否绘制成功
     * 
     * @return 是否成果
     */
    public boolean isFlag()
    {
        return flag;
    }

    /**
     * 设置绘制成功标志
     * 
     * @param flag 是否成功
     */
    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }

    /**
     * 绘制路径线
     * 
     * @param g 画笔
     */
    public void draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2f));// 设置画笔粗细
        g2d.setColor(c1);
        GradientPaint gp;// 线性颜色渐变
        int length = step * index;
        int curL = 0;

        b1.setIselim(true);
        this.gp.repaint(b1.getX(), b1.getY(), this.gp.width + this.gp.BORDER,
                this.gp.height + this.gp.BORDER);// 绘制消失效果

        for (int i = 0; i < points.length - 1; i++)
        {
            int l = length - curL;
            if (points[i + 1].x == points[i].x)
            {// 两点同一行
                curL += Math.abs(points[i + 1].y - points[i].y);// 计算纵坐标之差
                if (curL < length)
                {
                    g2d.drawLine(points[i].x, points[i].y, points[i + 1].x, points[i + 1].y);
                    if (i == points.length - 2)
                    {
                        flag = true;// 绘制成功
                    }
                } else
                {
                    if (points[i + 1].y - points[i].y < 0)
                    {// 判断纵坐标谁高
                        l = -l;
                    }
                    gp = new GradientPaint(points[i].x, points[i].y, c1, points[i].x,
                            points[i].y + l, c2);
                    g2d.setPaint(gp);// 设置渐变
                    g2d.drawLine(points[i].x, points[i].y, points[i].x, points[i].y + l);// 绘制渐变线
                    break;
                }
            } else
            {// 两点同一列
                curL += Math.abs(points[i + 1].x - points[i].x);
                if (curL < length)
                {
                    g2d.drawLine(points[i].x, points[i].y, points[i + 1].x, points[i + 1].y);
                    if (i == points.length - 2)
                    {
                        flag = true;
                    }
                } else
                {
                    if (points[i + 1].x - points[i].x < 0)
                    {
                        l = -l;
                    }
                    gp = new GradientPaint(points[i].x, points[i].y, c1, points[i].x + l,
                            points[i].y, c2);
                    g2d.setPaint(gp);
                    g2d.drawLine(points[i].x, points[i].y, points[i].x + l, points[i].y);
                    break;
                }

            }

        }
        if (flag)// 绘制成功后消除两个游戏图片
        {
            this.gp.disMusics[(int) (Math.random() * 5)].play();// 随机播放音效
            b1.setVisible(false);
            b2.setVisible(false);
        }
        index++;

    }

}
