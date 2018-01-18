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
import com.model.PanelInfo;
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
    static Image[] icons;// 图片数组
    static String folder = "res/pics";

    /**
     * 获取图片数组
     * 
     * @return
     */
    public static Image[] getIcons()
    {
        String path = System.getProperty("user.dir");// 获取用户当前工作目录
        File file = new File(path, folder);// 进入图片文件夹
        String[] filenames = file.list(new FilenameFilter()
        {// 返回图片目录下的所有文件和目录的文件名，返回的是String数组
            @Override
            public boolean accept(File dir, String name)
            {// 重写文件名筛选的方法
                String ext = name.substring(name.lastIndexOf(".") + 1).toLowerCase();// 后缀名
                return isAllowed(ext, new String[] {"jpg", "jpeg", "png", "gif"});// 后缀名允许列表
            }

            private boolean isAllowed(String ext, String[] types)
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
        });

        Image[] icons = new Image[filenames.length];// 确定图片数组长度
        for (int i = 0; i < filenames.length; i++)
        {// 依次实例化图片
            icons[i] = new ImageIcon(path + "/" + folder + "/" + filenames[i]).getImage();
        }
        return icons;
    }


    /**
     * 获取随机图片列表
     * 
     * @param numbers
     * @param maps
     * @return
     */
    public static java.util.List<Picture> getRandomIcons(PanelInfo pi, int numbers,
            Map<String, java.util.List<Picture>> maps)
    {
        java.util.List<Picture> iconlist = new LinkedList<Picture>();
        if (icons == null)
        {// 如果没有图片组，则获取
            icons = getIcons();
        }
        int size = icons.length;// 图片总数
        int number = 0;// 游戏图片ID
        maps.clear();// 清除所有映射
        for (int i = 0; i < numbers / 2; i++)
        {
            Image icon = icons[(int) (Math.random() * size)];// 在图片组中随机取一个图片
            Picture p1 = new Picture(icon, number++ + "", pi);
            Picture p2 = new Picture(icon, number++ + "", pi);// 一次生成两个
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
}
