/**
 * PathPoint.java at 2018年1月18日
 */
package com.model;


import java.awt.Point;

/**
 * 连接游戏图片的路径点，路径也有与游戏图片一样行和列
 * 
 * @author PCF
 */
public class PathPoint
{
    private int row;// 行数
    private int col;// 列数
    private PanelInfo pi;
    /**
     * 以行和列初始化路径点
     * 
     * @param row
     * @param col
     */
    public PathPoint(int row, int col, PanelInfo pi)
    {
        this.row = row;
        this.col = col;
        this.pi = pi;
    }
    /**
     * 通过图片的行数和列数初始化路径点
     * 
     * @param ib
     */
    public PathPoint(Picture ib, PanelInfo pi)
    {
        this(ib.getRow(), ib.getCol(), pi);
        this.pi = pi;
    }
    /**
     * 返回一个路径点对象在游戏窗体中的坐标点
     * 
     * @return
     */
    public Point getPointOnMain()
    {
        return new Point(pi.getLeft() + col * (pi.getWidth() + pi.getBORDER()) + pi.getWidth() / 2,
                pi.getTop() + row * (pi.getHeight() + pi.getBORDER()) + pi.getHeight() / 2);
    }
    /*
     * 打印路径点所在的行数和列数 （非 Javadoc）
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "CurvePoint{" + "row=" + row + ", col=" + col + '}';
    }
}
