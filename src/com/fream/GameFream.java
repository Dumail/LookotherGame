/**
 * MainFream.java at 2018年1月18日
 */
package com.fream;

import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.border.StandardBorderPainter;
import org.jvnet.substance.button.StandardButtonShaper;
import org.jvnet.substance.painter.StandardGradientPainter;
import org.jvnet.substance.skin.SubstanceCremeLookAndFeel;
import org.jvnet.substance.theme.SubstanceBarbyPinkTheme;
import org.jvnet.substance.title.FlatTitlePainter;
import org.jvnet.substance.watermark.SubstanceStripeWatermark;
import com.model.GamePlayer;

/**
 * 游戏主窗口
 * 
 * @author PCF
 */
public class GameFream extends JFrame
{
    private static final long serialVersionUID = -8237910730472236929L;
    private GameInfo gameInfo;// 信息界面
    private GamePanel gamePanel;// 游戏界面
    static GameFream gamefream;// 指向此窗体

    /**
     * 创建主窗体
     * 
     * @param player 玩家
     * @param diff 0普通；1困难；2艰难
     * @param kind 主题风格 1可爱；2漫画
     */
    public GameFream(GamePlayer player, int diff, int kind)
    {
        int col = 10, cow = 8;
        switch (diff)
        {
            case 0:
                cow = 8;
                col = 10;
                setSize(1100, 730);
                setLocation(500, 200);
                break;
            case 1:
                cow = 10;
                col = 12;
                setSize(1270, 73 * 12);
                setLocation(300, 100);
                break;
            case 2:
                cow = 12;
                col = 14;
                setSize(1430, 73 * 14);
                setLocation(250, 30);
                break;
            default:
                break;
        }
        gamefream = this;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);// 设置不可自由改变窗口大小
        getContentPane().setLayout(null);
        gamePanel = new GamePanel(player, cow, col, 16, kind);// 初始化游戏界面，一开始图片种类数为16
        gameInfo = new GameInfo(gamePanel);
        gamePanel.setInfo(gameInfo);// 连接游戏界面和信息界面
        gamePanel.setBounds(0, 0, gamePanel.getPanelWidth(), gamePanel.getPanelHeight());
        gameInfo.setBounds(gamePanel.getPanelWidth(), 0, 300, gamePanel.getPanelHeight());
        getContentPane().add(gamePanel);
        getContentPane().add(gameInfo);
    }

    /**
     * 初始化字体和风格
     */
    static void setStyle()
    {
        try
        {
            // 设置全局字体
            Font font = new Font("浪漫雅圆", Font.PLAIN, 12);
            UIManager.put("Label.font", font);
            UIManager.put("Button.font", font);
            try
            {
                // 使用substance美化包设置外观风格
                UIManager.setLookAndFeel(new SubstanceCremeLookAndFeel());
                JFrame.setDefaultLookAndFeelDecorated(true);
                SubstanceLookAndFeel.setCurrentTheme(new SubstanceBarbyPinkTheme());// 设置主题为粉色
                // SubstanceLookAndFeel.setSkin(new FieldOfWheatSkin());// 设置皮肤
                SubstanceLookAndFeel.setCurrentButtonShaper(new StandardButtonShaper());// 设置按钮
                SubstanceLookAndFeel.setCurrentWatermark(new SubstanceStripeWatermark());// 设置水印
                SubstanceLookAndFeel.setCurrentBorderPainter(new StandardBorderPainter());// 设置边框
                SubstanceLookAndFeel.setCurrentGradientPainter(new StandardGradientPainter());// 设置渐变渲染
                SubstanceLookAndFeel.setCurrentTitlePainter(new FlatTitlePainter());// 设置标题
            } catch (UnsupportedLookAndFeelException e)
            {
                // TODO catch 块
                e.printStackTrace();
            }
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
