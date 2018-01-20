/**
 * Timer.java at 2018年1月19日
 */
package com.fream;

import javax.swing.BoundedRangeModel;
import javax.swing.JProgressBar;
import com.model.Music;

/**
 * 计时器，随时间自动减少
 * 
 * @author PCF
 */
public class Timer extends JProgressBar implements Runnable
{
    private boolean stoped = false;// 计时器暂停标志
    private long time; // 限定时间十分钟
    private Music timeMusic = new Music("timeover");

    /**
     * 
     */
    public Timer()
    {
        super();
    }

    /**
     * @param orient
     */
    public Timer(int orient)
    {
        super(orient);
    }

    /**
     * @param newModel
     */
    public Timer(BoundedRangeModel newModel)
    {
        super(newModel);
    }

    /**
     * @param min
     * @param max
     */
    public Timer(int min, int max)
    {
        super(min, max);
    }

    /**
     * @param orient
     * @param min
     * @param max
     */
    public Timer(int orient, int min, int max)
    {
        super(orient, min, max);
    }

    /*
     * 计时器自动减少 （非 Javadoc）
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run()
    {
        while (true)
        {
            if (stoped)// 游戏暂停时停止一会进入下一个循环
            {
                try
                {
                    Thread.sleep(500);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                continue;
            }
            int t = getValue();
            if (t >= 80 && t % 5 == 0)
                timeMusic.play();
            t++;
            setValue(t);// 更改计时器的进度
            if (t >= 100)
            {
                break;
            }
            try
            {
                Thread.sleep(time / 100);// 每各百分之总时间减少一格计时器
            } catch (Exception ex)
            {
                break;
            }
        }
    }

    /**
     * 是否暂停
     * 
     * @return stoped
     */
    public boolean isStoped()
    {
        return stoped;
    }

    /**
     * 设置暂停状态
     * 
     * @param stoped 要设置的 stoped
     */
    public void setStoped(boolean stoped)
    {
        this.stoped = stoped;
    }

    /**
     * 设置计时器时间
     * 
     * @param time 要设置的 time
     */
    public void setTime(long time)
    {
        this.time = time;
    }

    /**
     * 重载定时器
     * 
     * @return 上次定时器的值
     */
    public int reset()
    {
        this.stoped = false;
        int t = getValue();
        setValue(0);
        return t;
    }
}
