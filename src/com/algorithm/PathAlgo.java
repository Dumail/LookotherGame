/**
 * PathLine.java at 2018年1月18日
 */
package com.algorithm;

import com.fream.GamePanel;
import com.model.PathPoint;
import com.model.Picture;

/**
 * 算法
 * 
 * @author PCF
 */
public class PathAlgo
{
    /**
     * 获取可以连接两个图片的点
     * 
     * @param b1
     * @param b2
     * @return 满足连接条件返回路径点数组，否则返回null
     */
    public static PathPoint[] getPoints(Picture b1, Picture b2, GamePanel gp)
    {
        if (b1.getRow() == b2.getRow())
        {// 同一行
            return wayRow(b1, b2, gp);
        } else if (b1.getCol() == b2.getCol())
        {// 同一列
            return wayCol(b1, b2, gp);
        } else
        {
            PathPoint[] cp = wayRow(b1, b2, gp);
            return cp == null ? wayCol(b1, b2, gp) : cp;
        }
    }

    /**
     * 行路径算法
     *
     * @param b1
     * @param b2
     * @return
     */
    public static PathPoint[] wayRow(Picture b1, Picture b2, GamePanel gp)
    {

        Picture left = b1.getCol() < b2.getCol() ? b1 : b2;// 左边的图片
        Picture right = (left == b1) ? b2 : b1;// 右边的图片

        int center = getCenter(b1.getRow(), b2.getRow());// 行数
        int flag = -1;

        row: for (int i = 0; i < gp.gameinfo.getRows() + 2; i++)
        {
            if (center + flag * i < -1)
            {
                center += 1;
            } else if (center + flag * i > gp.gameinfo.getRows())
            {
                center -= 1;
            } else
            {
                center += flag * i;
                flag = -flag;
            }

            if (center > -1 && center < gp.gameinfo.getRows())
            {
                for (int x = left.getCol(); x <= right.getCol(); x++)
                {
                    String id = gp.getPictures()[center][x].getId();
                    if (id.equals(b1.getId()) || id.equals(b2.getId()))
                    {
                        continue;
                    }
                    if (gp.getPictures()[center][x].isVisible())
                    {
                        continue row;
                    }
                }
            }
            int r = left.getRow();

            int beg = r < center ? r : center;
            int end = r < center ? center : r;
            for (int step = beg + 1; step < end; step++)
            {
                if (gp.getPictures()[step][left.getCol()].isVisible())
                {
                    continue row;
                }
            }

            r = right.getRow();
            beg = r < center ? r : center;
            end = r < center ? center : r;
            for (int step = beg + 1; step < end; step++)
            {
                if (gp.getPictures()[step][right.getCol()].isVisible())
                {
                    continue row;
                }
            }

            PathPoint[] points;
            if (left.getRow() == center && right.getRow() == center)
            {
                points = new PathPoint[2];
                points[0] = new PathPoint(left, gp.gameinfo);
                points[1] = new PathPoint(right, gp.gameinfo);
            } else if (left.getRow() == center)
            {
                points = new PathPoint[3];
                points[0] = new PathPoint(left, gp.gameinfo);
                points[1] = new PathPoint(center, right.getCol(), gp.gameinfo);
                points[2] = new PathPoint(right, gp.gameinfo);
            } else if (right.getRow() == center)
            {
                points = new PathPoint[3];
                points[0] = new PathPoint(left, gp.gameinfo);
                points[1] = new PathPoint(center, left.getCol(), gp.gameinfo);
                points[2] = new PathPoint(right, gp.gameinfo);
            } else
            {
                points = new PathPoint[4];
                points[0] = new PathPoint(left, gp.gameinfo);
                points[1] = new PathPoint(center, left.getCol(), gp.gameinfo);
                points[2] = new PathPoint(center, right.getCol(), gp.gameinfo);
                points[3] = new PathPoint(right, gp.gameinfo);
            }

            if (b2.getId().equals(left.getId()))
            {
                int length = points.length;
                PathPoint[] cp = new PathPoint[length];
                for (int index = 0; index < length; index++)
                {
                    cp[index] = points[length - index - 1];
                }
                return cp;
            }
            return points;
        }
        return null;
    }

    /**
     * 列路径算法
     *
     * @param b1
     * @param b2
     * @return
     */
    public static PathPoint[] wayCol(Picture b1, Picture b2, GamePanel gp)
    {
        Picture top = b1.getRow() < b2.getRow() ? b1 : b2;
        Picture bottom = (top == b1) ? b2 : b1;

        int middle = getCenter(b1.getCol(), b2.getCol());
        int flag = -1;

        row: for (int i = 0; i < gp.gameinfo.getColumns() + 2; i++)
        {
            if (middle + flag * i < -1)
            {
                middle += 1;
            } else if (middle + flag > gp.gameinfo.getRows())
            {
                middle -= 1;
            } else
            {
                middle += flag * i;
                flag = -flag;
            }

            if (middle > -1 && middle < gp.gameinfo.getColumns())
            {
                for (int x = top.getRow(); x <= bottom.getRow(); x++)
                {
                    String id = gp.getPictures()[x][middle].getId();
                    if (id.equals(b1.getId()) || id.equals(b2.getId()))
                    {
                        continue;
                    }
                    if (gp.getPictures()[x][middle].isVisible())
                    {
                        continue row;
                    }
                }
            }
            int r = top.getCol();
            int beg = r < middle ? r : middle;
            int end = r < middle ? middle : r;
            for (int step = beg + 1; step < end; step++)
            {
                if (gp.getPictures()[top.getRow()][step].isVisible())
                {
                    continue row;
                }
            }

            r = bottom.getCol();
            beg = r < middle ? r : middle;
            end = r < middle ? middle : r;
            for (int step = beg + 1; step < end; step++)
            {
                if (gp.getPictures()[bottom.getRow()][step].isVisible())
                {
                    continue row;
                }
            }

            PathPoint[] points;
            if (top.getCol() == middle && bottom.getCol() == middle)
            {
                points = new PathPoint[2];
                points[0] = new PathPoint(top, gp.gameinfo);
                points[1] = new PathPoint(bottom, gp.gameinfo);
            } else
            {
                points = new PathPoint[4];
                points[0] = new PathPoint(top, gp.gameinfo);
                points[1] = new PathPoint(top.getRow(), middle, gp.gameinfo);
                points[2] = new PathPoint(bottom.getRow(), middle, gp.gameinfo);
                points[3] = new PathPoint(bottom, gp.gameinfo);
            }

            if (b2.getId().equals(top.getId()))
            {
                int length = points.length;
                PathPoint[] cp = new PathPoint[length];
                for (int index = 0; index < length; index++)
                {
                    cp[index] = points[length - index - 1];
                }
                return cp;
            }
            return points;

        }

        return null;
    }

    /**
     * 获取两个数的中间值
     * 
     * @param i1
     * @param i2
     * @return
     */
    public static int getCenter(int i1, int i2)
    {
        int min = i1 < i2 ? i1 : i2;
        int max = i1 < i2 ? i2 : i1;
        return min + (max - min) / 2;
    }
}
