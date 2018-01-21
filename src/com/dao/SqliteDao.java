/**
 * SqliteDao.java at 2018年1月18日
 */
package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 对数据库进行连接和直接访问
 * 
 * @author PCF
 */
public class SqliteDao
{
    protected static String dbClassName = "org.sqlite.JDBC";
    protected static String dbUrl = "jdbc:sqlite:lib/LookGame.db";
    private static Connection conn = null;
    private static Statement stmt = null;

    private SqliteDao()
    {
        try
        {
            if (conn == null)
            {
                Class.forName(dbClassName);
                conn = DriverManager.getConnection(dbUrl);
                stmt = conn.createStatement();
            } else
                return;
        } catch (Exception ee)
        {
            ee.printStackTrace();
        }
    }

    /**
     * 执行有结果集的sql语句
     * 
     * @param sql 语句
     * @return 结果集
     */
    public static ResultSet executeQuery(String sql)
    {
        try
        {
            if (conn == null)
                new SqliteDao();

            return stmt.executeQuery(sql);
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        } finally
        {
        }
    }

    /**
     * 执行更改数据库的语句
     * 
     * @param sql
     */
    public static int executeUpdate(String sql)
    {
        try
        {
            if (conn == null)
                new SqliteDao();
            return conn.createStatement().executeUpdate(sql);
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return -1;
        } finally
        {
        }
    }

    /**
     * 关闭数据库连接
     */
    public static void close()
    {
        if (conn != null)
        {
            try
        {
            stmt.close();
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            conn = null;
        }
        }

    }
}
