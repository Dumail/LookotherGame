/**
 * GamePlayer.java at 2018年1月19日
 */
package com.model;

/**
 * 游戏玩家
 * 
 * @author PCF
 */
public class GamePlayer
{
    private String username;// 用户名
    private int highscore = 0;// 最高分
    private int score = 0;// 当前分数

    /**
     * 获取最高分
     * 
     * @return highscore 最高分
     */
    public int getHighscore()
    {
        return highscore;
    }

    /**
     * 当前分数高于最高分时改变最高分
     */
    public void setHighscore()
    {
        if (this.highscore <= score)
            this.highscore = score;
    }

    /**
     * 获取用户名
     * 
     * @return username 用户名
     */
    public String getUsername()
    {
        return username;
    }

    public GamePlayer(String username)
    {
        this.username = username;
        // TODO 获取最高分
    }

    /**
     * 增加分数
     * 
     * @param add 增加的值
     */
    public void addScore(int add)
    {
        this.score += add;
    }

    /**
     * 减少分数
     * 
     * @param dcl 减少的值
     */
    public void dclScore(int dcl)
    {
        this.score -= dcl;
    }

    /**
     * 获取用户当前分数
     * 
     * @return score 当前分数
     */
    public int getScore()
    {
        return score;
    }

    /**
     * 清除分数
     */
    public void ClearScore()
    {
        score = 0;
    }

    /*
     * （非 Javadoc）
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    /*
     * （非 Javadoc）
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GamePlayer other = (GamePlayer) obj;
        if (username == null)
        {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

}
