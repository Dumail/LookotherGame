/**
 * PathPoint.java at 2018年1月18日
 */
package com.model;


import java.awt.Point;
import com.fream.GamePanel;

/**
 * 连接游戏图片的路径点，路径也有与游戏图片一样行和列
 * 
 * @author PCF
 */
public class PathPoint
{
    private int row;// 行数
    private int col;// 列数
    private GamePanel gp;
    
    /**
     * 以行和列初始化路径点
     * 
     * @param row 初始行
     * @param col 初始列
     * @param gp 游戏面板
     */
    public PathPoint(int row, int col, GamePanel gp)
    {
        this.row = row;
        this.col = col;
        this.gp = gp;
    }
    
    /**
     * 通过图片的行数和列数初始化路径点
     * 
     * @param ib 游戏图片
     * @param gp 游戏界面
     */
    public PathPoint(Picture ib, GamePanel gp)
    {
        this(ib.getRow(), ib.getCol(), gp);
        this.gp = gp;
    }
    
    /**
     * 返回一个路径点对象在游戏窗体中的坐标点
     * 
     * @return 路径点
     */
    public Point getPointOnMain()
    {
        return new Point(gp.left + col * (gp.width + gp.BORDER) + gp.width / 2,
                gp.top + row * (gp.height + gp.BORDER) + gp.height / 2);
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
