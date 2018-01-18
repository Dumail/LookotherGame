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
import org.jvnet.substance.theme.SubstanceSunGlareTheme;

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
     * 初始化字体和风格
     */
    static void uiInit()
    {
        try
        {
            Font font = new Font("浪漫雅圆", Font.PLAIN, 12);
            UIManager.put("Label.font", font);// 设置全局字体
            UIManager.put("Button.font", font);
            try
            {
                UIManager.setLookAndFeel(new SubstanceLookAndFeel());
                JFrame.setDefaultLookAndFeelDecorated(true);
                SubstanceLookAndFeel.setCurrentTheme(new SubstanceSunGlareTheme());
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

    /**
     * 创建主窗体
     */
    public MainFream()
    {
        // uiInit();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(500, 200);
        setSize(800, 760);
        setResizable(false);// 设置不可自由改变窗口大小
        getContentPane().setLayout(new BorderLayout());
        GamePanel aGamePanel = new GamePanel();
        getContentPane().add(aGamePanel, BorderLayout.CENTER);
        StatusPanel aStatusPanel = new StatusPanel(aGamePanel);
        getContentPane().add(aStatusPanel, BorderLayout.NORTH);
    }

}
