/*
 * tishi3.java
 *
 * Created on __DATE__, __TIME__
 */

package com.fream;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 用户名为空时的错误信息窗口
 *
 * @author __USER__
 */
public class NameFalse extends javax.swing.JFrame {
    private static final long serialVersionUID = -3850997244669942748L;

    /** Creates new form tishi3 */
	public NameFalse() {
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
		jLabel1.setText("\u7528\u6237\u540d\u4e0d\u53ef\u4e3a\u7a7a\uff01");

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
								.addGap(113, 113, 113)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel1)
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		57,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(
																		jButton1)
																.addGap(78, 78,
																		78)))
								.addContainerGap(147, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addGap(74, 74, 74)
						.addComponent(jLabel1).addGap(79, 79, 79)
						.addComponent(jButton1)
						.addContainerGap(131, Short.MAX_VALUE)));

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