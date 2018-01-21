/**
 * GifDrawer.java at 2018年1月19日
 */
package com.icons;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

/**
 * gif动图绘制，将gif各帧放入gif文件夹，此类会每隔一段时间绘制一帧,且只能绘制不透明动画 注意，开启此线程需要在组件可以显示后，否则会引起空异常
 * 
 * @author PCF
 */
public class GifDrawer implements Runnable
{
    private Image[] icons;// 图片数组
    private String gifFolder;// gif文件夹
    protected int i = 0;
    protected int num;// 图片总数
    protected int times = 100;// 每帧图片显示时间
    protected Graphics2D g2d;// 画笔
    protected boolean isPaly = true;// 是否播放
    protected boolean isloop = false;
    protected int x;// 播放的坐标
    protected int y;
    
    /**
     * 初始化gif动画
     * 
     * @param x 坐标x
     * @param y 坐标y
     * @param g 绘制用的画笔
     * @param kind gif图片文件夹的编号
     * @param num 图片总数
     */
    public GifDrawer(Graphics g, int x, int y, int kind, int num)
    {
        this("res/gif", g, x, y, kind, num);
    }

    /**
     * 初始化动画图片组
     * 
     * @param folder 指定gif动画的文件夹
     * @param g 画笔
     * @param x x坐标
     * @param y y坐标
     * @param kind 主题
     * @param num 图片数
     */
    public GifDrawer(String folder, Graphics g, int x, int y, int kind, int num)
    {
        this.gifFolder = folder;// 设置gif文件夹
        g2d = (Graphics2D) g;
        this.num = num;
        this.x = x;
        this.y = y;
        IconManager.setFolder(gifFolder);// 设置图片管理器文件夹为gif
        icons = IconManager.getIcons(num, kind);// 获取gif(kind)文件夹中的各帧图片
        IconManager.setFolder("res/pics");// 重新设置图片管理器的文件夹
    }

    public void paint(Graphics g)
    {
        // TODO 画出一帧图片
        g2d.drawImage(icons[i], x, y, null);
    }
    /* （非 Javadoc）
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run()
    {
        while (isPaly)// 控制是否继续播放
        {
            if (i <= num - 1)
                i++;
            if (i > num - 1)
            {
                i = 0;
                if (!isloop)// 循环一次后停止播放
                    isPaly = false;
            }
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
     * 开始播放
     */
    public void play()
    {
        new Thread(this).start();
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
     * @param isPaly 播放状态
     */
    public void setPaly(boolean isPaly)
    {
        this.isPaly = isPaly;
    }

    /**
     * 获取播放间隔时间
     * 
     * @return times 播放间隔时间
     */
    public int getTimes()
    {
        return times;
    }

    /**
     * 设置播放间隔
     * 
     * @param times 播放间隔
     */
    public void setTimes(int times)
    {
        this.times = times;
    }

    /**
     * 循环播放动画
     */
    public void loop()
    {
        isloop = true;
        play();
    }

    /**
     * 停止播放动画
     */
    public void stop()
    {
        setPaly(false);
    }
}
