/**
 * PlayerServer.java at 2018年1月21日
 */
package com.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import com.dao.SqliteDao;

/**
 * 玩家数据服务，用于对玩家数据进行操作
 * 
 * @author PCF
 */
public class PlayerServer
{
    /**
     * 获取玩家在数据库中的最高分
     * 
     * @param player 玩家
     * @return 历史最高分
     */
    public static int getPlayerScore(GamePlayer player)
    {
        ResultSet resultSet = SqliteDao.executeQuery(
                "SELECT highest FROM lian_score WHERE username='" + player.getUsername() + "';");
        int score;
        try
        {
            score = resultSet.getInt(1);
        } catch (SQLException e)
        {
            score = -1;
        }
        SqliteDao.close();
        return score;
    }

    /**
     * 当玩家当前最高分高于历史最高分时，更新玩家最高分
     * 
     * @param player 玩家
     */
    public static void updataPlayerScore(GamePlayer player)
    {
        if (getPlayerScore(player) < player.getHighscore())
        {
            SqliteDao.executeUpdate("UPDATE lian_score SET highest=" + player.getHighscore()
                    + " WHERE username='" + player.getUsername() + "';");
            SqliteDao.close();
        }
    }

    /**
     * 当数据库中不存在当前玩家时，将此玩家和当前最高分加入数据库
     * 
     * @param player 玩家
     */
    public static void insertPlayer(GamePlayer player)
    {
        if (getPlayerScore(player) == -1)
        {
            SqliteDao.executeUpdate("INSERT INTO lian_score(username,highest) " + "VALUES ('"
                    + player.getUsername() + "', " + player.getHighscore() + ");");
            SqliteDao.close();
        }
    }

    /**
     * 获取数据库中按分数降序排列的前一百位玩家名单
     * 
     * @return 玩家信息数组
     */
    public static String[][] getScoreTable()
    {
        LinkedList<String[]> scores = new LinkedList<String[]>();
        String[] player = new String[3];
        ResultSet aResultSet =
                SqliteDao.executeQuery("SELECT * FROM lian_score order by highest desc LIMIT 100;");
        int count = 1;
        try
        {
            while (aResultSet.next())
            {
                player[0] = count++ + "";
                player[1] = aResultSet.getString(2);
                player[2] = aResultSet.getInt(3) + "";
                scores.add(Arrays.copyOf(player, 3));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        String[][] score = new String[scores.size()][3];
        for (int i = 0; i < scores.size(); i++)
        {
            score[i] = scores.get(i);
        }
        SqliteDao.close();
        return score;
    }
}
