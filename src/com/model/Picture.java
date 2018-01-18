/**
 * Picture.java at 2018年1月18日
 */
package com.model;


import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;


/**
 * 游戏主体图片
 * 
 * @author PCF
 */
public class Picture
{
    private int x;// 坐标
    private int y;
    private Image img;// 显示的图片

    private int row;// 行
    private int col;// 列
    private boolean visible = true;// 是否显示
    private boolean selected;// 是否被选中
    private Color bgColor;// 背景颜色
    private String id;// 编号，随机生成图片时递增生成
    private PanelInfo pi;

    /**
     * 以显示图片和图片编号初始化对象
     * 
     * @param img
     * @param id
     */
    public Picture(Image img, String id, PanelInfo pi)
    {
        this.img = img;
        this.id = id;
        this.pi = pi;
    }

    /**
     * 以行数，列数以及显示的图片初始化对象
     * 
     * @param row
     * @param col
     * @param img
     */
    public Picture(int row, int col, Image img, PanelInfo pi)
    {
        this.x = row;
        this.y = col;
        this.img = img;
        this.pi = pi;
    }
    /**
     * 获取图片行数
     * 
     * @return
     */
    public int getRow()
    {
        return row;
    }

    /**
     * 设置图片行数，并修改图片的坐标
     * 
     * @param row
     */
    public void setRow(int row)
    {
        this.row = row;
        this.y = pi.getTop() + row * (pi.getHeight() + 1);
    }

    /**
     * 获取图片列数
     * 
     * @return
     */
    public int getCol()
    {
        return col;
    }

    /**
     * 设置图片行数，并修改图片的坐标
     * 
     * @param col
     */
    public void setCol(int col)
    {
        this.col = col;
        this.x = pi.getLeft() + col * (pi.getWidth() + 1);
    }

    /**
     * 获取编号
     * 
     * @return
     */
    public String getId()
    {
        return id;
    }

    /**
     * 设置编号
     * 
     * @param id
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * 获取显示的图片字符串信息
     * 
     * @return
     */
    public String getIconId()
    {
        return img.toString();
    }

    /**
     * 获取图片x坐标
     * 
     * @return
     */
    public int getX()
    {
        return x;
    }


    /**
     * 获取图片纵坐标
     * 
     * @return
     */
    public int getY()
    {
        return y;
    }

    /**
     * 判断是否显示
     * 
     * @return
     */
    public boolean isVisible()
    {
        return visible;
    }

    /**
     * 判断是否被选中
     * 
     * @return
     */
    public boolean isSelected()
    {
        return selected;
    }


    /**
     * 返回显示的图片
     * 
     * @return
     */
    public Image getImg()
    {
        return img;
    }

    /**
     * 更改显示的图片
     * 
     * @param img
     */
    public void setImg(Image img)
    {
        this.img = img;
    }

    /**
     * 获取背景颜色
     * 
     * @return
     */
    public Color getBgColor()
    {
        return bgColor;
    }

    /**
     * 设置背景颜色
     * 
     * @param bgColor
     */
    public void setBgColor(Color bgColor)
    {
        this.bgColor = bgColor;
    }

    /**
     * 绘制此游戏图片
     * 
     * @param g
     */
    public void paint(Graphics g)
    {
        if (!visible)
        {
            return;// 只有游戏图片允许被显示才能被显示
        }
        Graphics2D g2d = (Graphics2D) g;
        if (bgColor != null)
        {// 有背景颜色时设置画笔的背景颜色
            g2d.setColor(bgColor);
            g2d.fillRect(x, y, pi.getWidth(), pi.getHeight());// 绘制游戏图片的背景
        }
        g2d.setStroke(new BasicStroke(pi.getBORDER()));// 设置画笔的粗细以绘制边框
        g2d.setColor(new Color(182, 171, 180));// 设置画笔颜色
        // if (x == pi.left) {
        g2d.drawLine(x, y, x, y + pi.getHeight());// 绘制游戏图片的四边边框
        // }
        // if (y == pi.top) {
        g2d.drawLine(x, y, x + pi.getWidth(), y);
        // }
        g2d.drawLine(x + pi.getBORDER(), y + pi.getHeight() + pi.getBORDER(),
                x + pi.getWidth() + pi.getBORDER(), y + pi.getHeight() + pi.getBORDER());
        g2d.drawLine(x + pi.getWidth() + pi.getBORDER(), y + pi.getBORDER(),
                x + pi.getWidth() + pi.getBORDER(), y + pi.getHeight() + pi.getBORDER());

        g2d.drawImage(img, x, y, null);
        if (selected)
        {// 如果被选中要绘制特殊效果
            Color color = Color.GREEN;// 以红色的半透明矩形覆盖
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g2d.setColor(color);
            g2d.fillRect(x + pi.getBORDER(), y + pi.getBORDER(), pi.getWidth(), pi.getHeight());
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));// 取消半透明效果
        }

    }

    /**
     * 设置是否显示
     * 
     * @param b
     */
    public void setVisible(boolean b)
    {
        this.visible = b;
    }

    /**
     * 设置是否被选中
     * 
     * @param b
     */
    public void setSelected(boolean b)
    {
        this.selected = b;
    }
}

