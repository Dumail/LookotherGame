/*
 * defen.java
 *
 * Created on __DATE__, __TIME__
 */

package com.fream;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import com.model.PlayerServer;

/**
 * 分数窗口
 *
 * @author __USER__
 */
public class GameScores extends javax.swing.JFrame {
    private static final long serialVersionUID = -934686323308121689L;
    String[] thead = new String[] {"排名", "姓名", "得分"};// 表格列信息
    String[][] scores = PlayerServer.getScoreTable();// 分数表格数组

	public GameScores() {
		initComponents();
		setSize(700, 600);//设置窗口的大小 （宽，高）
        setLocation(650, 200);// 窗口在桌面位置（左右，上下）
        setResizable(false);// 设置不可自由改变窗口大小
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
    private void initComponents()
    {

		scrollPane1 = new java.awt.ScrollPane();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
        jTextLabel = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();

		//设置表格内容
        DefaultTableModel tableModel = new DefaultTableModel(scores, thead){
            private static final long serialVersionUID = 1L;

            @Override // 设置表格不可编辑
            public boolean isCellEditable(int row,int column) {
                return false;
              }};
        jTable1.setModel(tableModel);
		jScrollPane1.setViewportView(jTable1);

		scrollPane1.add(jScrollPane1);

        jTextLabel.setBackground(new java.awt.Color(204, 255, 255));
        jTextLabel.setFont(new java.awt.Font("汉仪小麦简", 1, 28));
        jTextLabel.setText("     英雄榜");// 得分榜文字

		jButton1.setFont(new java.awt.Font("汉仪小麦简", 1, 14));
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
                .addGroup(layout.createSequentialGroup().addGap(108, 108, 108).addGroup(layout
                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup().addGap(165, 165, 165).addComponent(
                                jTextLabel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup().addGap(209, 209, 209)
                                .addComponent(jButton1))
                        .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        474, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(216, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(43, 43, 43)
                        .addComponent(jTextLabel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77).addComponent(jButton1)
                        .addContainerGap(83, Short.MAX_VALUE)));

        pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		dispose();
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;// 滚动条
    private javax.swing.JTable jTable1;// 分数窗口
    private javax.swing.JLabel jTextLabel;
	private java.awt.ScrollPane scrollPane1;
	// End of variables declaration//GEN-END:variables

}