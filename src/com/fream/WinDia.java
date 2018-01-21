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
 * @author PCF
 */
public class WinDia extends JDialog
{

    private final JPanel contentPanel = new JPanel();

    /**
     * Create the dialog.
     */
    public WinDia(int kind)
    {
        setBounds(600, 300, 497, 300);
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
                if (kind == 1)
                    lblNewLabel.setText("恭喜你，完成此关卡！");
                else
                    lblNewLabel.setText("恭喜你，通关了！");
                lblNewLabel.setFont(new Font("汉仪小麦体简", Font.PLAIN, 18));
                buttonPane.add(lblNewLabel);
            }
            {
                JButton cancelButton = new JButton("进入下一关");
                if (kind != 1)
                    cancelButton.setText("返回主界面");
                cancelButton.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        if (kind != 1)
                        {
                            GameLogin.gamelogin.setVisible(true);
                        }
                        dispose();
                    }
                });
                cancelButton.setFont(new Font("浪漫雅圆", Font.PLAIN, 18));
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }
}
