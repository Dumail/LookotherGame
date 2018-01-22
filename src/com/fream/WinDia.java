/**
 * WinDia.java at 2018年1月21日
 */
package com.fream;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * 胜利和过关画面弹窗
 * 
 * @author PCF
 */
public class WinDia extends JDialog
{
    private static final long serialVersionUID = 7800129376469384490L;
    private final JPanel contentPanel = new JPanel();

    /**
     * @param kind 种类 1过关;2通关
     */
    public WinDia(int kind)
    {
        setBounds(650, 300, 504, 300);// 设置位置和长宽
        setResizable(false);// 设置不可自由改变窗口大小
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JLabel lblNewLabel = new JLabel("00");
                // 设置提示信息
                if (kind == 1)
                    lblNewLabel.setText("恭喜你，完成此关卡！");
                else
                    lblNewLabel.setText("恭喜你，通关了！");
                lblNewLabel.setFont(new Font("汉仪小麦体简", Font.PLAIN, 18));
                buttonPane.add(lblNewLabel);
            }
            {
                JButton cancelButton = new JButton("进入下一关");
                // 设置按钮信息
                if (kind != 1)
                    cancelButton.setText("返回主界面");
                cancelButton.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        if (kind != 1)// 通关则返回主界面
                        {
                            GameLogin.gamelogin.setVisible(true);
                        }
                        dispose();// 销毁
                    }
                });
                cancelButton.setFont(new Font("浪漫雅圆", Font.PLAIN, 18));
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }
}
