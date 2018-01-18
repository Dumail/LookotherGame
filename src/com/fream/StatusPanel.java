/**
 * StatusPanel.java at 2018年1月18日
 */
package com.fream;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;

/**
 * 游戏信息界面
 * 
 * @author PCF
 */
public class StatusPanel extends JPanel
{
    private long time = 600 * 1000; // 限定时间十分钟
    private GamePanel gpGamePanel; //游戏主界面
    private boolean stoped = false;
    private int times_tishi = 3;// 提示次数
    private int times_resort = 3;// 重排次数
    // 信息界面组件：
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JLabel label5;
    private JSlider slider1;
    private JPanel vSpacer1;
    private JPanel panel2;
    private JProgressBar times;// 计时器进度条
    private JButton stop;// 暂停按钮
    private JButton tishi;// 提示按钮
    private JButton resort;// 重排按钮


    public StatusPanel(GamePanel path)
    {
        initComponents();
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (true)
                {
                    if (stoped)
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
                    int t = times.getValue();
                    t--;
                    times.setValue(t);
                    if (t <= 0)
                    {
                        break;
                    }
                    try
                    {
                        Thread.sleep(time / 100);
                    } catch (Exception ex)
                    {
                        break;
                    }
                }
            }
        }).start();
        this.gpGamePanel = path;
    }

    private void label3ActionPerformed(ActionEvent e)
    {

        this.tishi.setText("提示x" + (--times_tishi));
        gpGamePanel.Ghint();
        if (times_tishi <= 0)
        {
            this.tishi.setEnabled(false);
        }
    }

    private void label2ActionPerformed(ActionEvent e)
    {
        stoped = !stoped;
        gpGamePanel.pause(stoped);
    }

    private void label4ActionPerformed(ActionEvent e)
    {
        this.resort.setText("重排x" + (--times_resort));
        gpGamePanel.reSort();
        if (times_resort <= 0)
        {
            this.resort.setEnabled(false);
        }
    }

    /**
     * 初始化信息界面
     */
    private void initComponents()
    {
        // 各个组件实例化
        panel1 = new JPanel();
        button1 = new JButton();
        button2 = new JButton();
        label5 = new JLabel();
        slider1 = new JSlider();
        vSpacer1 = new JPanel(null);
        panel2 = new JPanel();
        times = new JProgressBar();
        stop = new JButton();// 暂停按钮
        tishi = new JButton();// 提示按钮
        resort = new JButton();// 重排按钮

        setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                        "", javax.swing.border.TitledBorder.CENTER,
                        javax.swing.border.TitledBorder.BOTTOM,
                        new java.awt.Font("微软雅黑", java.awt.Font.BOLD, 12), java.awt.Color.red),
                getBorder()));
        // 注册事件监听器
        addPropertyChangeListener(new java.beans.PropertyChangeListener()
        {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent e)
            {
                if ("border".equals(e.getPropertyName()))
                    throw new RuntimeException();
            }
        });

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));// 放在顶部

        {
            panel1.setLayout(null);

            button1.setText("选择关卡");
            panel1.add(button1);
            button1.setBounds(new Rectangle(new Point(10, 10), button1.getPreferredSize()));

            button2.setText("开始游戏");
            panel1.add(button2);
            button2.setBounds(new Rectangle(new Point(90, 10), button2.getPreferredSize()));

            label5.setText("音乐");
            panel1.add(label5);
            label5.setBounds(new Rectangle(new Point(580, 10), label5.getPreferredSize()));
            panel1.add(slider1);
            slider1.setBounds(610, 10, 240, slider1.getPreferredSize().height);
            panel1.add(vSpacer1);
            vSpacer1.setBounds(855, 0, vSpacer1.getPreferredSize().width, 40);

            {
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < panel1.getComponentCount(); i++)
                {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }
        {
            panel2.setLayout(null);

            // 时间
            times.setValue(100);
            panel2.add(times);
            times.setBounds(170, 10, 250, 25);

            // 暂停
            stop.setText("暂停");
            stop.setIcon(null);
            stop.setFont(new Font("浪漫雅圆", Font.BOLD, 12));
            stop.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    label2ActionPerformed(e);
                }
            });
            panel2.add(stop);
            stop.setBounds(430, 10, 110, 25);

            // 提示
            tishi.setText("提示×3");
            tishi.setFont(new Font("浪漫雅圆", Font.BOLD, 12));
            tishi.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    label3ActionPerformed(e);
                }
            });
            panel2.add(tishi);
            tishi.setBounds(550, 10, 110, 25);

            // resort
            resort.setText("重排×3");
            resort.setFont(new Font("浪漫雅圆", Font.BOLD, 12));
            resort.setIcon(
                    new ImageIcon("/res/icons/png-0616.png"));
            resort.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    label4ActionPerformed(e);
                }
            });
            panel2.add(resort);
            resort.setBounds(670, 10, 110, 25);
            {
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < panel2.getComponentCount(); i++)
                {
                    Rectangle bounds = panel2.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel2.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel2.setMinimumSize(preferredSize);
                panel2.setPreferredSize(preferredSize);
            }
        }
        add(panel2);
    }
}


