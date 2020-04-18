package com.deepglint.library.tool;

import java.text.DecimalFormat;

/**
 * Created by gaofengdeng 2020/4/18
 **/
public class FusConstants {
    public final static int FAST_CLICK_TIME = 100;

    public final static int VIBRATE_TIME = 100;

    /**
     * 速度格式化
     */
    public static final DecimalFormat FORMAT_ONE = new DecimalFormat("#.#");
    /**
     * 距离格式化
     */
    public static final DecimalFormat FORMAT_TWO = new DecimalFormat("#.##");
    /**
     * 速度格式化
     */
    public static final DecimalFormat FORMAT_THREE = new DecimalFormat("#.###");

    //Date格式
    public static final String DATE_FORMAT_LINK = "yyyyMMddHHmmssSSS";

    //Date格式 常用
    public static final String DATE_FORMAT_DETACH = "yyyy-MM-dd HH:mm:ss";

    //Date格式 带毫秒
    public static final String DATE_FORMAT_DETACH_SSS = "yyyy-MM-dd HH:mm:ss SSS";

    //时间格式 分钟：秒钟 一般用于视频时间显示
    public static final String DATE_FORMAT_MM_SS = "mm:ss";
}
