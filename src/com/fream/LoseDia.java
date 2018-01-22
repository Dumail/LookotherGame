/**
 * LoseDia.java at 2018年1月21日
 */
package com.fream;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * 时间结束的弹出窗口
 * 
 * @author PCF
 */
public class LoseDia extends JDialog
{

    private static final long serialVersionUID = 6119893419075930333L;
    private final JPanel contentPanel = new JPanel() {
        private static final long serialVersionUID = 6553876856573615314L;
        /* （非 Javadoc）
         * @see java.awt.Window#paint(java.awt.Graphics)
         */
        @Override
        public void paint(Graphics g)
        {
            // TODO 自动生成的方法存根
            super.paint(g);
        }
    };
    Image img = new ImageIcon("res/icons/bg1.jpg").getImage();

    /**
     * Create the dialog.
     */
    public LoseDia(int score)
    {
        setBounds(650, 300, 371, 303);// 设置坐标和边框
        setResizable(false);// 设置不可自由改变窗口大小
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);// 设置默认关闭方式
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                // 返回按钮
                JButton okButton = new JButton("返回主界面");
                okButton.setFont(new Font("浪漫雅圆", Font.PLAIN, 14));
                okButton.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        GameLogin.gamelogin.setVisible(true);// 显示登录界面
                        dispose();// 销毁
                    }
                });
                {
                    JLabel lblNewLabel = new JLabel("时间结束，你的分数是");
                    lblNewLabel.setFont(new Font("汉仪小麦体简", Font.PLAIN, 18));
                    buttonPane.add(lblNewLabel);
                }
                {
                    JLabel lblNewLabel_1 = new JLabel(score + "分");// 显示得分
                    lblNewLabel_1.setForeground(Color.CYAN);
                    lblNewLabel_1.setFont(new Font("汉仪小麦体简", Font.PLAIN, 18));
                    buttonPane.add(lblNewLabel_1);
                }
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
        }
    }
}
