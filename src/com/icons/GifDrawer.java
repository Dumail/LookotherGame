/**
 * GifDrawer.java at 2018年1月19日
 */
package com.icons;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

/**
 * gif动图绘制，将gif各帧放入gif文件夹，此类会每隔一段时间绘制一帧 注意，开启此线程需要在组件可以显示后，否则会引起空异常
 * 
 * @author PCF
 */
public class GifDrawer implements Runnable
{
    private Image[] icons;// 图片数组
    private String folder = "res/gif";// gif文件夹
    private int i = 0;
    private int num;// 图片总数
    private int times = 100;// 每帧图片显示时间
    private Graphics2D g2d;// 画笔
    private boolean isPaly = true;// 是否播放

    /**
     * 
     * 
     * @param g 绘制用的画笔
     * @param kind gif图片文件夹的编号
     * @param num 图片总数
     */
    public GifDrawer(Graphics g, int kind, int num)
    {
        g2d = (Graphics2D) g;
        this.num = num;
        IconManager.setFolder(folder);// 设置图片管理器文件夹为gif
        icons = IconManager.getIcons(num, kind);// 获取gif(kind)文件夹中的各帧图片
        IconManager.setFolder("res/pics");// 重新设置图片管理器的文件夹
    }

    public void paint(Graphics g)
    {
        // TODO 画出一帧图片
        g2d.drawImage(icons[i], 0, 0, null);
    }
    /* （非 Javadoc）
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run()
    {
        // TODO 自动生成的方法存根
        while (isPaly)
        {
            if (i <= num - 1)
                i++;
            if (i > num - 1)
                i = 0;
            try
            {
                Thread.sleep(times);
            } catch (InterruptedException e)
            {
                // TODO catch 块
                e.printStackTrace();
            }
            this.paint(g2d);
        }
    }

    /**
     * @return 播放状态
     */
    public boolean isPaly()
    {
        return isPaly;
    }

    /**
     * 设置播放状态
     * 
     * @param isPaly
     */
    public void setPaly(boolean isPaly)
    {
        this.isPaly = isPaly;
    }

    /**
     * @return times
     */
    public int getTimes()
    {
        return times;
    }

    /**
     * 设置播放间隔
     * 
     * @param times
     */
    public void setTimes(int times)
    {
        this.times = times;
    }

}
