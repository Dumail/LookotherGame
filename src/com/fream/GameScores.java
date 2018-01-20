/*
 * defen.java
 *
 * Created on __DATE__, __TIME__
 */

package com.fream;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 分数窗口
 *
 * @author __USER__
 */
public class GameScores extends javax.swing.JFrame {
	String[] thead = new String[] { "排名", "姓名", "得分" };

	public GameScores() {
		initComponents();
		setSize(700, 600);//设置窗口的大小 （宽，高）
		setLocation(450, 200);//窗口在桌面位置（左右，上下）
        ImageIcon background = new ImageIcon("res/icons/bg4.jpg");// 吧背景图片显示在一个标签里面
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

		scrollPane1 = new java.awt.ScrollPane();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
        jTextLabel = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jTable1.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {},thead));
		jScrollPane1.setViewportView(jTable1);

		scrollPane1.add(jScrollPane1);

		jTextLabel.setBackground(new java.awt.Color(204, 255, 255));
		jTextLabel.setFont(new java.awt.Font("微软雅黑", 1, 24));
        jTextLabel.setText("      \u5f97\u5206\u699c");// 得分榜文字

		jButton1.setFont(new java.awt.Font("微软雅黑", 1, 14));
        jButton1.setText("\u8fd4\u56de");// 返回
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
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addContainerGap()
																.addComponent(
																		scrollPane1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		669,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(318,
																		318,
																		318)
																.addComponent(
																		jButton1))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(271,
																		271,
																		271)
																.addComponent(
																		jTextLabel,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(119, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addGap(54, 54, 54)
						.addComponent(jTextLabel,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(65, 65, 65)
						.addComponent(scrollPane1,
								javax.swing.GroupLayout.PREFERRED_SIZE, 106,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(39, 39, 39).addComponent(jButton1)
						.addContainerGap(107, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		dispose();
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable1;
    private javax.swing.JLabel jTextLabel;
	private java.awt.ScrollPane scrollPane1;
	// End of variables declaration//GEN-END:variables

}