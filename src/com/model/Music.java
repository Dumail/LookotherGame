/**
 * Mucsic.java at 2018年1月19日
 */
package com.model;

import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JApplet;

/**
 * 播放音频，音频放在res的mus文件夹，必须是wav格式
 * 
 * @author PCF
 */
public class Music
{
    String floder = "res/mus/";// 音频文件夹
    AudioClip audioClip = null;

    /**
     * 设置待载人音频位置
     * 
     * @param filename 位置
     */
    public Music(String filename)
    {
        setMusic(filename);// 载人
    }

    /**
     * 载人音频
     * 
     * @param filename 音频文件名，不需要加.wav
     */
    public void setMusic(String filename)
    {
        try
        {
            URL url = new URL("file:" + floder + filename + ".wav");
            this.audioClip = JApplet.newAudioClip(url);
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 播放一次音频
     */
    public void play()
    {
        audioClip.play();
    }

    /**
     * 循环播放音频
     */
    public void loop()
    {
        audioClip.loop();
    }

    /**
     * 停止播放音频
     */
    public void stop()
    {
        audioClip.stop();
    }
}
