package com.leo.core.utils;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

/**
 * LOG管理类
 */
public class LogUtil {
    private static boolean isDebug = false;//当app开发完成之后要置为false
    private static boolean isDDebug = true;
    private static boolean isIDebug = true;
    private static boolean isEDebug = true;
    private static Gson gson = new Gson();
    private static String DEFAULT = "";
    private static String APP_PACKAGE = "com.leo.me";

    /**
     * 获取class 名称
     */
    private static String getClassName(Object obj) {
        if (obj == null) {
            return DEFAULT;
        }
        String name = obj.getClass().getName();
        String simpleName = obj.getClass().getSimpleName();
        if (!TextUtils.isEmpty(simpleName)){
            return simpleName;
        }else if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(APP_PACKAGE) && name.startsWith(APP_PACKAGE)) {
            return name.substring(APP_PACKAGE.length());
        }
        return name;
    }

    /**
     * 打印d级别的log
     *
     * @param tag
     * @param msg
     */
    public static void d(boolean isBug, String tag, String msg) {
        if (isBug && isDebug && isDDebug) {
            Log.d(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        d(true, tag, msg);
    }

    /**
     * 打印d级别的log
     *
     * @param msg
     */
    public static void d(boolean isBug, Object obj, String msg) {
        if (isBug && isDebug && isDDebug) {
            Log.d(getClassName(obj), msg);
        }
    }

    public static void d(Object obj, String msg) {
        d(true, obj, msg);
    }

    /**
     * 打印i级别的log
     *
     * @param tag
     * @param msg
     */
    public static void i(boolean isBug, String tag, String msg) {
        if (isBug && isDebug && isIDebug) {
            Log.i(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        i(true, tag, msg);
    }

    public static void i(Object tag, Object msg) {
        i(true, tag, gson.toJson(msg));
    }

    /**
     * 打印i级别的log
     *
     * @param msg
     */
    public static void i(boolean isBug, Object obj, String msg) {
        if (isBug && isDebug && isIDebug) {
            Log.i(getClassName(obj), msg);
        }
    }

    public static void i(Object obj, String msg) {
        i(true, obj, msg);
    }

    /**
     * 打印e级别的log
     *
     * @param tag
     * @param msg
     */
    public static void e(boolean isBug, String tag, String msg) {
        if (isBug && isDebug && isEDebug) {
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        e(true, tag, msg);
    }

    /**
     * 打印e级别的log
     *
     * @param msg
     */
    public static void e(boolean isBug, Object obj, String msg) {
        if (isBug && isDebug && isEDebug) {
            Log.e(getClassName(obj), msg);
        }
    }

    public static void e(Object obj, String msg) {
        e(true, obj, msg);
    }

    public static void e(Object obj, Object msg) {
        e(true, obj, gson.toJson(msg));
    }

}
