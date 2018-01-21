/**
 * IconManager.java at 2018年1月18日
 */
package com.icons;


import java.awt.Image;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import javax.swing.ImageIcon;
import com.fream.GamePanel;
import com.model.Picture;

/**
 * 图片管理器
 * 
 * @author PCF
 */
public class IconManager
{
    static int width;
    static int height;
    private static Image[] icons;// 图片数组
    static String folder = "res/pics";

    /**
     * 获取游戏图片数组
     * 
     * @param length 获取的图片种类数
     * @param kind 图片的种类
     * @return 待分配的图片数组
     */
    public static Image[] getIcons(int length, int kind)
    {
        String path = System.getProperty("user.dir");// 获取用户当前工作目录
        File file = new File(path, folder + kind);// 进入图片文件夹
        String[] filenames = file.list(new FilenameFilter()
        {// 返回图片目录下的所有文件和目录的文件名，返回的是String数组
            @Override
            public boolean accept(File dir, String name)
            {// 重写文件名筛选的方法
                String ext = name.substring(name.lastIndexOf(".") + 1).toLowerCase();// 后缀名
                return isAllowed(ext, new String[] {"png", "gif"});// 后缀名允许列表
            }
        });

        Image[] icons = new Image[length];// 确定图片数组长度
        for (int i = 0; i < length; i++)
        {// 依次实例化图片
            icons[i] = new ImageIcon(path + "/" + folder + kind + "/" + filenames[i]).getImage();
        }
        return icons;
    }

    /**
     * 获取随机图片列表，用于显示在游戏主界面中
     * 
     * @param gp 目标游戏界面
     * @param length 游戏图片种类数
     * @param kind 游戏图片的种类
     * @param numbers 游戏图片总个数
     * @param maps 图片和相同游戏图片列表映射关系
     * @return 生成的游戏图片随机列表
     */
    public static java.util.List<Picture> getRandomIcons(GamePanel gp, int length, int kind,
            int numbers,
            Map<String, java.util.List<Picture>> maps)
    {
        java.util.List<Picture> iconlist = new LinkedList<Picture>();
        if (getIcons() == null)
        {// 如果没有图片组，则获取
            setIcons(getIcons(length, kind));
        }
        int size = getIcons().length;// 图片总数
        int number = 0;// 游戏图片ID
        maps.clear();// 清除所有映射
        for (int i = 0; i < numbers / 2; i++)
        {
            Image icon = getIcons()[(int) (Math.random() * size)];// 在图片组中随机取一个图片
            Picture p1 = new Picture(icon, number++ + "", gp);
            Picture p2 = new Picture(icon, number++ + "", gp);// 一次生成两个
            java.util.List<Picture> lists = maps.get(icon.toString());// 返回图片所对应的游戏图片列表
            if (lists == null)
            {// 如果映射不存在则向其中加入图片到游戏图片列表的映射
                lists = new ArrayList<Picture>();
                maps.put(icon.toString(), lists);
            }
            lists.add(p1);// 在相同的游戏图片列表中加入游戏图片
            lists.add(p2);
            iconlist.add(p1);
            iconlist.add(p2);
        }
        return iconlist;// 返回游戏图片链表
    }

    public static boolean isAllowed(String ext, String[] types)
    {// 判断字符串是否在字符串数组中
        for (String t : types)
        {
            if (ext.equals(t))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 设置文件夹
     * 
     * @param folder 要设置的 folder
     */
    public static void setFolder(String folder)
    {
        IconManager.folder = folder;
    }

    /**
     * 获取图片组
     * 
     * @return icons 图片数组
     */
    public static Image[] getIcons()
    {
        return icons;
    }

    /**
     * 设置图片数组
     * 
     * @param icons 要设置的 图片数组
     */
    public static void setIcons(Image[] icons)
    {
        IconManager.icons = icons;
    }
}
