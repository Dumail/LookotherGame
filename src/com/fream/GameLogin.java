/*
 * jiemian.java
 *
 * Created on __DATE__, __TIME__
 */

package com.fream;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.model.GamePlayer;
import com.model.PlayerServer;

/**
 *登录窗口
 *
 * @author  __USER__
 */
public class GameLogin extends javax.swing.JFrame {

    public static GameFream gamefream=null;
    public static GamePlayer gameplayer=null;
    public static GameLogin gamelogin = null;
    
	/** Creates new form login */
	public GameLogin() {
        gamelogin = this;
        GameFream.setStyle();
		initComponents();
        setTitle("连连看");
		setSize(650, 600);//设置窗口的大小 （宽，高）
		setLocation(450, 200);//窗口在桌面位置（左右，上下）
        ImageIcon background = new ImageIcon("res/icons/bg3.jpg");// 把背景图片显示在一个标签里面
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

		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jButton4 = new javax.swing.JButton();
		jLabel2 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setFont(new java.awt.Font("汉仪小麦简", 0, 18));
		jButton1.setText("\u5f00\u59cb");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			@Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

        jButton2.setFont(new java.awt.Font("汉仪小麦简", 0, 18));
		jButton2.setText("\u79bb\u5f00");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			@Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

        jButton3.setFont(new java.awt.Font("汉仪小麦简", 0, 18));
		jButton3.setText("\u5f97\u5206\u699c");
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			@Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		jLabel1.setBackground(new java.awt.Color(204, 255, 255));
        jLabel1.setFont(new java.awt.Font("汉仪小麦简", 1, 18));
		jLabel1.setText("\u7528\u6237\u540d\uff1a");

        jTextField1.setFont(new java.awt.Font("汉仪小麦简", 0, 18));
        jTextField1.setText("user");
		jTextField1.addActionListener(new java.awt.event.ActionListener() {
			@Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField1ActionPerformed(evt);
			}
		});

        jButton4.setFont(new java.awt.Font("汉仪小麦简", 0, 18));
		jButton4.setText("\u6539\u540d");
		jButton4.addActionListener(new java.awt.event.ActionListener() {
			@Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton4ActionPerformed(evt);
			}
		});

        jLabel2.setFont(new java.awt.Font("汉仪小麦简", 1, 48));
        jLabel2.setText("Welcome");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(386, Short.MAX_VALUE)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jButton2)
												.addComponent(jButton1)
												.addComponent(jButton3))
								.addGap(288, 288, 288))
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addGroup(
														layout.createSequentialGroup()
																.addContainerGap()
																.addComponent(
																		jLabel2))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(137,
																		137,
																		137)
																.addComponent(
																		jLabel1)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		170,
																		Short.MAX_VALUE)
																.addComponent(
																		jTextField1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		130,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addGap(83, 83, 83).addComponent(jButton4)
								.addGap(100, 100, 100)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(83, 83, 83)
								.addComponent(jLabel2)
								.addGap(78, 78, 78)
								.addComponent(jButton1)
								.addGap(33, 33, 33)
								.addComponent(jButton2)
								.addGap(39, 39, 39)
								.addComponent(jButton3)
								.addGap(58, 58, 58)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jButton4)
												.addComponent(jLabel1)
												.addComponent(
														jTextField1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(164, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
        if (gameplayer == null)
        {
            gameplayer = new GamePlayer("user");
            PlayerServer.insertPlayer(gameplayer);// 如果没有增加数据
            jTextField1.setText(gameplayer.getUsername());
        }
		new GameChoice().setVisible(true);
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		dispose();
	}

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		new GameScores().setVisible(true);
	}

	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {

		String text = jTextField1.getText();
		String s = "";
		if (s.equals(text)) {
            new NameFalse().setVisible(true);// 显示修改失败界面
        } else
        {
            gameplayer = new GamePlayer(text);
            PlayerServer.insertPlayer(gameplayer);// 如果没有增加数据
            new NameTrue().setVisible(true);// 显示修改成功界面
        }
	}

	private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
		jTextField1.setText("");
		jTextField1.getText();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
            public void run() {
                new GameLogin().setVisible(true);// 显示一个登录界面
			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
    private javax.swing.JButton jButton1;// 开始游戏按钮
    private javax.swing.JButton jButton2;// 离开游戏按钮
    private javax.swing.JButton jButton3;// 分数榜按钮
    private javax.swing.JButton jButton4;// 更改用户名按钮
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;// 用户名输入框
	// End of variables declaration//GEN-END:variables

}