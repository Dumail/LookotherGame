package com.fream;
/**
 * GameMain.java at 2018年1月20日
 */

import java.awt.EventQueue;
import com.model.GamePlayer;

/**
 * @author PCF
 */
public class GameMain
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        GamePlayer player = new GamePlayer("user");
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {

                    GameFream frame = new GameFream(player, 0, 1);
                    frame.setVisible(true);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

}
