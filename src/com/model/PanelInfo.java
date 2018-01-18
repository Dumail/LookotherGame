/**
 * PanelInfo.java at 2018年1月18日
 */
package com.model;

/**
 * @author PCF
 */
public class PanelInfo
{
    private int width ;// 游戏图片的宽度
    private int height;// 游戏图片的高度
    private int rows, columns;// 图片行数和列数
    private int panel_width, panel_height;// 游戏主面板高度和宽度
    private int BORDER;// 游戏图片间的间距
    private int level;// 游戏关数
    // 游戏图片的开始排列的位置
    private int left;
    private int top;
    
    /**
     * @param width
     * @param height
     * @param rows
     * @param columns
     * @param panel_width
     * @param panel_height
     * @param bORDER
     * @param level
     * @param left
     * @param top
     */
    public PanelInfo(int width, int height, int rows, int columns, int panel_width,
            int panel_height, int bORDER, int level)
    {
        super();
        this.width = width;
        this.height = height;
        this.rows = rows;
        this.columns = columns;
        this.panel_width = panel_width;
        this.panel_height = panel_height;
        BORDER = bORDER;
        this.level = level;
        left = (width - BORDER - columns * (width + 1)) / 2 + 350;// 计算游戏图片开始排列的左边坐标
        top = (height - BORDER - (height + 1) * rows) / 2 + 290;// 计算游戏图片开始排列的顶部坐标
    }

    /**
     * @return left
     */
    public int getLeft()
    {
        return left;
    }

    /**
     * @param left 要设置的 left
     */
    public void setLeft(int left)
    {
        this.left = left;
    }

    /**
     * @return top
     */
    public int getTop()
    {
        return top;
    }

    /**
     * @param top 要设置的 top
     */
    public void setTop(int top)
    {
        this.top = top;
    }

    
    

    /**
     * @return width
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * @param width 要设置的 width
     */
    public void setWidth(int width)
    {
        this.width = width;
    }

    /**
     * @return height
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * @param height 要设置的 height
     */
    public void setHeight(int height)
    {
        this.height = height;
    }

    /**
     * @return rows
     */
    public int getRows()
    {
        return rows;
    }

    /**
     * @param rows 要设置的 rows
     */
    public void setRows(int rows)
    {
        this.rows = rows;
    }

    /**
     * @return columns
     */
    public int getColumns()
    {
        return columns;
    }

    /**
     * @param columns 要设置的 columns
     */
    public void setColumns(int columns)
    {
        this.columns = columns;
    }

    /**
     * @return panel_width
     */
    public int getPanel_width()
    {
        return panel_width;
    }

    /**
     * @param panel_width 要设置的 panel_width
     */
    public void setPanel_width(int panel_width)
    {
        this.panel_width = panel_width;
    }

    /**
     * @return panel_height
     */
    public int getPanel_height()
    {
        return panel_height;
    }

    /**
     * @param panel_height 要设置的 panel_height
     */
    public void setPanel_height(int panel_height)
    {
        this.panel_height = panel_height;
    }

    /**
     * @return bORDER
     */
    public int getBORDER()
    {
        return BORDER;
    }

    /**
     * @param bORDER 要设置的 bORDER
     */
    public void setBORDER(int bORDER)
    {
        BORDER = bORDER;
    }

    /**
     * @return level
     */
    public int getLevel()
    {
        return level;
    }

    /**
     * @param level 要设置的 level
     */
    public void setLevel(int level)
    {
        this.level = level;
    }
    
    
}
