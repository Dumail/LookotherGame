/**
 * BombDrawer.java at 2018年1月19日
 */
package com.icons;

import java.awt.Graphics;
import com.fream.GamePanel;
import com.model.Picture;

/**
 * 绘制透明动画,绘制图片消失时的爆炸特效
 * 
 * @author PCF
 */
public class BombDrawer extends GifDrawer
{
    GamePanel gp;
    
    /**
     * 指定绘制特效的文件夹来初始化gif绘制
     * 
     * @param p 特效所属的游戏图片
     * @param g 绘制用的画笔
     * @param kind gif图片文件夹的编号
     * @param num 图片总数
     */
    public BombDrawer(Picture p, Graphics g, int kind, int num)
    {
        super("res/eff", g, p.getX(), p.getX(), kind, num);
        this.gp = p.getGp();
        this.x = p.getX();
        this.y = p.getX();
    }

    /*
     * （非 Javadoc）
     * 
     * @see com.icons.GifDrawer#run()
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
            this.paint(g2d);
            try
            {
                Thread.sleep(times);
            } catch (InterruptedException e)
            {
                // TODO catch 块
                e.printStackTrace();
            }
            // gp.repaint();
        }
    }
}
