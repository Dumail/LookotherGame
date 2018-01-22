/*
 * tishi2.java
 *
 * Created on __DATE__, __TIME__
 */

package com.fream;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 用户名设置成功时的信息窗口
 *
 * @author __USER__
 */
public class NameTrue extends javax.swing.JFrame {
    private static final long serialVersionUID = -2320409908720572263L;

    /** Creates new form tishi2 */
	public NameTrue() {
		initComponents();
		setSize(400, 300);//设置窗口的大小 （宽，高）
		setLocation(680, 300);//窗口在桌面位置（左右，上下）
        setResizable(false);// 设置不可自由改变窗口大小
        ImageIcon background = new ImageIcon("res/icons/ds.jpeg");// 吧背景图片显示在一个标签里面
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, this.getWidth(), this.getHeight());// 把标签的大小位置设置为图片刚好填充整个面板 
		JPanel imagePanel = (JPanel) this.getContentPane();// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));// 把背景图片添加到分层窗格的最底层作为背景 
		setVisible(true);//设置可见
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setBackground(new java.awt.Color(204, 255, 255));
		jLabel1.setFont(new java.awt.Font("微软雅黑", 1, 24));
		jLabel1.setText("\u4fee\u6539\u540d\u79f0\u6210\u529f\uff01");

		jButton1.setText("\u8fd4\u56de");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			@Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap(125, Short.MAX_VALUE)
								.addComponent(jLabel1).addGap(107, 107, 107))
				.addGroup(
						layout.createSequentialGroup().addGap(164, 164, 164)
								.addComponent(jButton1)
								.addContainerGap(179, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addGap(81, 81, 81)
						.addComponent(jLabel1).addGap(72, 72, 72)
						.addComponent(jButton1)
						.addContainerGap(89, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		dispose();
	}


	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	// End of variables declaration//GEN-END:variables

}