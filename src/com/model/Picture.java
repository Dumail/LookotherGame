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
import com.fream.GamePanel;


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
    private GamePanel gp;
    private boolean iselim = false;
    
    /**
     * @param iselim 要设置的 iselim
     */
    public void setIselim(boolean iselim)
    {
        this.iselim = iselim;
    }

    /**
     * 获取图片行数
     * 
     * @return 行数
     */
    public int getRow()
    {
        return row;
    }

    /**
     * 设置图片行数，并修改图片的坐标
     * 
     * @param row 行数
     */
    public void setRow(int row)
    {
        this.row = row;
        this.y = gp.top + row * (gp.height + 1);
    }

    /**
     * 获取图片列数
     * 
     * @return 列数
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
        this.x = gp.left + col * (gp.width + 1);
    }

    /**
     * 获取编号
     * 
     * @return 编号
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
     * 以显示图片和图片编号初始化对象
     * 
     * @param img 图片
     * @param id 编号
     */
    public Picture(Image img, String id, GamePanel gp)
    {
        this.img = img;
        this.id = id;
        this.gp = gp;
    }

    /**
     * 获取显示的图片字符串信息
     * 
     * @return img.toString
     */
    public String getIconId()
    {
        return img.toString();
    }

    /**
     * 获取图片x坐标
     * 
     * @return x坐标
     */
    public int getX()
    {
        return x;
    }


    /**
     * 获取图片纵坐标
     * 
     * @return y坐标
     */
    public int getY()
    {
        return y;
    }

    /**
     * 判断是否显示
     * 
     * @return visible
     */
    public boolean isVisible()
    {
        return visible;
    }

    /**
     * 判断是否被选中
     * 
     * @return selected
     */
    public boolean isSelected()
    {
        return selected;
    }


    /**
     * 返回显示的图片
     * 
     * @return 图片
     */
    public Image getImg()
    {
        return img;
    }

    /**
     * 更改显示的图片
     * 
     * @param img 图片
     */
    public void setImg(Image img)
    {
        this.img = img;
    }

    /**
     * 以行数，列数以及显示的图片初始化对象
     * 
     * @param row 行
     * @param col 列
     * @param img 图片
     */
    public Picture(int row, int col, Image img, GamePanel gp)
    {
        this.x = row;
        this.y = col;
        this.img = img;
        this.gp = gp;
    }


    /**
     * 获取背景颜色
     * 
     * @return 背景颜色
     */
    public Color getBgColor()
    {
        return bgColor;
    }

    /**
     * 设置背景颜色
     * 
     * @param bgColor 背景颜色
     */
    public void setBgColor(Color bgColor)
    {
        this.bgColor = bgColor;
    }

    /**
     * 绘制此游戏图片
     * 
     * @param g 画笔
     */
    public void paint(Graphics g)
    {
        if (!visible)
        {
            return;// 只有游戏图片允许被显示才能被显示
        }
        Graphics2D g2d = (Graphics2D) g;
        if (bgColor != null)// 有背景颜色时设置画笔的背景颜色
        {
            g2d.setColor(bgColor);
            g2d.fillRect(x, y, gp.width, gp.height);// 绘制游戏图片的背景
        }
        g2d.setStroke(new BasicStroke(gp.BORDER));// 设置画笔的粗细以绘制边框
        g2d.setColor(new Color(182, 171, 180));// 设置画笔颜色
        g2d.drawLine(x, y, x, y + gp.height);// 绘制游戏图片的四边边框
        g2d.drawLine(x, y, x + gp.width, y);
        g2d.drawLine(x + gp.BORDER, y + gp.height + gp.BORDER, x + gp.width + gp.BORDER,
                y + gp.height + gp.BORDER);
        g2d.drawLine(x + gp.width + gp.BORDER, y + gp.BORDER, x + gp.width + gp.BORDER,
                y + gp.height + gp.BORDER);

        if (iselim)
        {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g2d.drawImage(img, x, y, null);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        } else
            g2d.drawImage(img, x, y, null);

        // int width = img.getHeight(null);
        // int height = img.getHeight(null);
        // Rectangle rect_des = CalcRotatedSize(new Rectangle(new Dimension(width, height)), 30);
        // BufferedImage res = null;
        // res = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        // Graphics2D g2 = res.createGraphics();
        // g2.drawImage(img, null, null);
        // g2d.drawImage(res, x, y, null);

        if (selected)
        {// 如果被选中要绘制特殊效果
            Color color = Color.GREEN;// 以红色的半透明矩形覆盖
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));// 不透明度50%
            g2d.setColor(color);
            g2d.fillRect(x + gp.BORDER, y + gp.BORDER, gp.width, gp.height);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));// 取消半透明效果
        }

    }

    /**
     * 设置是否显示
     * 
     * @param b visible
     */
    public void setVisible(boolean b)
    {
        this.visible = b;
    }

    /**
     * 设置是否被选中
     * 
     * @param b selected
     */
    public void setSelected(boolean b)
    {
        this.selected = b;
    }

    /**
     * 获取游戏图片的面板
     * 
     * @return gp 游戏面板
     */
    public GamePanel getGp()
    {
        return gp;
    }

}

