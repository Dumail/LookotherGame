/**
 * MainFream.java at 2018年1月18日
 */
package com.fream;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.SubstanceCremeLookAndFeel;
import org.jvnet.substance.theme.SubstanceBarbyPinkTheme;

/**
 * 调用各级界面
 * 
 * @author PCF
 */
public class MainFream extends JFrame
{
    /**
     * 运行程序
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    MainFream frame = new MainFream();
                    frame.setVisible(true);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 创建主窗体
     */
    public MainFream()
    {
        setStyle();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(500, 200);
        setSize(800, 760);
        setResizable(false);// 设置不可自由改变窗口大小
        getContentPane().setLayout(new BorderLayout());
        // GamePanel aGamePanel = new GamePanel(8, 10, 21, 1);
        // getContentPane().add(aGamePanel, BorderLayout.CENTER);
        // StatusPanel aStatusPanel = new StatusPanel(aGamePanel);
        // getContentPane().add(aStatusPanel, BorderLayout.NORTH);

    }

    /**
     * 初始化字体和风格
     */
    static void setStyle()
    {
        try
        {
            // 设置全局字体
            Font font = new Font("浪漫雅圆", Font.PLAIN, 12);
            UIManager.put("Label.font", font);
            UIManager.put("Button.font", font);
            try
            {
                // 使用substance美化包设置外观风格
                UIManager.setLookAndFeel(new SubstanceCremeLookAndFeel());// 设置皮肤为奶酪
                JFrame.setDefaultLookAndFeelDecorated(true);
                SubstanceLookAndFeel.setCurrentTheme(new SubstanceBarbyPinkTheme());// 设置主题为粉色
            } catch (UnsupportedLookAndFeelException e)
            {
                // TODO catch 块
                e.printStackTrace();
            }
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
