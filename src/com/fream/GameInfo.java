/**
 * GameInfo.java at 2018年1月19日
 */
package com.fream;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.icons.IconManager;
import com.model.Music;
import com.model.PlayerServer;

/**
 * 游戏信息界面，必须先连接游戏界面
 * 
 * @author PCF
 */
public class GameInfo extends JPanel
{
    private static final long serialVersionUID = 5288370214477309269L;
    private long time = 600 * 600; // 限定时间六分钟
    private GamePanel gpGamePanel; // 游戏主界面
    private boolean stoped = false;
    private int times_hint = 4;// 提示次数
    private int times_resort = 3;// 重排次数
    Timer timer = new Timer();// 计时器
    JLabel lblNewLabel = null;// 分数牌
    private Music reMusic = new Music("resort");
    private Music hiMusic = new Music("hint");
    JButton btnNewButton_1 = new JButton("提示x4");
    JButton btnx = new JButton("洗牌x3");
    JLabel label;
    /**
     * Create the panel.
     */
    public GameInfo(GamePanel gpGamePanel)
    {
        this.gpGamePanel = gpGamePanel;
        setBackground(SystemColor.control);
        setLayout(null);

        switch (gpGamePanel.getLevel())// 更加关数减少时间
        {
            case 1:
                timer.setTime(time - 600 * 100);
            case 2:
                timer.setTime(time - 600 * 200);
            case 3:
                timer.setTime(time - 600 * 300);
        }
        timer.setValue(0);
        timer.setBounds(54, 118, 191, 38);
        add(timer);
        Thread t = new Thread(timer);// 启动定时器
        t.start();
        // 暂停按钮
        JButton btnNewButton = new JButton("暂停");
        btnNewButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                stoped = !stoped;
                if (stoped)
                {
                    btnNewButton.setText("继续");
                    btnNewButton_1.setVisible(false);
                    btnx.setVisible(false);
                }
                else
                {
                    btnNewButton.setText("暂停");
                    btnNewButton_1.setVisible(true);
                    btnx.setVisible(true);
                }
                timer.setStoped(stoped);
                gpGamePanel.pause(stoped);
            }
        });
        btnNewButton.setFont(new Font("汉仪小麦体简", Font.BOLD, 26));
        btnNewButton.setBounds(103, 388, 97, 60);
        add(btnNewButton);
        // 提示按钮
        btnNewButton_1.addMouseMotionListener(new MouseMotionAdapter()
        {
            @Override
            public void mouseMoved(MouseEvent e)
            {
                btnNewButton_1.setToolTipText("提示一次减40分！");
            }
        });
        btnNewButton_1.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (gpGamePanel.getPlayer().getScore() >= 50)
                {
                    btnNewButton_1.setText("提示x" + (--times_hint));
                    hiMusic.play();
                    gpGamePanel.Ghint();// 提示
                    gpGamePanel.getPlayer().dclScore(50);// 减少分数
                    updateScore();
                    if (times_hint <= 0)// 如果次数不够则关闭按钮
                    {
                        btnNewButton_1.setToolTipText("没有提示次数了！");
                        btnNewButton_1.setEnabled(false);
                    }
                } else
                {
                    JOptionPane.showMessageDialog(gpGamePanel, "分数不足");
                }
            }
        });
        btnNewButton_1.setFont(new Font("汉仪小麦体简", Font.PLAIN, 18));
        btnNewButton_1.setBounds(45, 511, 90, 38);
        add(btnNewButton_1);
        // 重牌按钮
        lblNewLabel = new JLabel("00000");
        lblNewLabel.setForeground(Color.PINK);
        lblNewLabel.setBackground(Color.YELLOW);
        lblNewLabel.setFont(new Font("华文彩云", Font.PLAIN, 35));
        lblNewLabel.setBounds(90, 250, 145, 63);
        add(lblNewLabel);

        btnx.addMouseMotionListener(new MouseMotionAdapter()
        {
            @Override
            public void mouseMoved(MouseEvent e)
            {
                btnx.setToolTipText("洗牌一次减100分！");
            }
        });
        btnx.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (gpGamePanel.getPlayer().getScore() >= 100)
                {
                    btnx.setText("洗牌x" + (--times_resort));
                    reMusic.play();
                    gpGamePanel.reSort();
                    gpGamePanel.getPlayer().dclScore(100);// 减少分数
                    updateScore();// 更新分数
                    if (times_resort <= 0)
                    {
                        btnNewButton_1.setToolTipText("没有提示次数了！");
                        btnx.setEnabled(false);
                    }
                } else
                    JOptionPane.showMessageDialog(gpGamePanel, "分数不足");
            }
        });
        btnx.setFont(new Font("汉仪小麦体简", Font.PLAIN, 18));
        btnx.setBounds(166, 511, 90, 38);
        add(btnx);
        // 返回主界面按钮
        JButton button = new JButton("返回");
        button.addMouseListener(new MouseAdapter()
        {// 返回主界面
            @Override
            public void mouseClicked(MouseEvent e)
            {
                GameLogin.gameplayer.setHighscore();
                PlayerServer.updataPlayerScore(GameLogin.gameplayer);
                GameLogin.gameplayer.ClearScore(); // 分数清零
                GameLogin.gamelogin.setVisible(true);// 显示登录界面
                gpGamePanel.bgMusic.stop();
                IconManager.setIcons(null);
                GameFream.gamefream.dispose();// 关闭游戏窗口
            }
        });
        button.addMouseMotionListener(new MouseMotionAdapter()
        {// 鼠标停留事件
            @Override
            public void mouseMoved(MouseEvent e)
            {
                button.setToolTipText("返回主界面");// 鼠标停留文字
            }
        });
        button.setFont(new Font("汉仪小麦体简", Font.PLAIN, 15));
        button.setBounds(214, 623, 65, 23);
        add(button);

        JLabel lblNewLabel_1 = new JLabel("你的历史最高分是");
        lblNewLabel_1.setForeground(new Color(205, 50, 222));
        lblNewLabel_1.setFont(new Font("汉仪小麦体简", Font.PLAIN, 18));
        lblNewLabel_1.setBounds(60, 29, 145, 36);
        add(lblNewLabel_1);
        // 最高分标签
        label = new JLabel();
        label.setText(PlayerServer.getPlayerScore(GameLogin.gameplayer) + "");
        label.setForeground(Color.CYAN);
        label.setFont(new Font("汉仪小麦体简", Font.PLAIN, 18));
        label.setBounds(203, 29, 67, 36);
        add(label);
    }

    /**
     * 更新分数显示
     */
    public void updateScore()
    {
        String score = gpGamePanel.getPlayer().getScore() + "";
        int i = 0;
        if ((i = 5 - score.length()) > 0)
        {
            while (i-- != 0)// 填充为五位数
            {
                score = "0" + score;
            }
        }
        lblNewLabel.setText(score);
    }

    /*
     * （非 Javadoc）
     * 
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        // 绘制底纹
        Image img = new ImageIcon("res/icons/bg5.jpg").getImage();
        g.drawImage(img, 0, 0, null);
    }
}
