package com.leo.core.impl.main;

import android.support.annotation.NonNull;

/**
 * class 工具类
 */
public class ClassUtil {

    /**
     * 根据类型返回数据
     */
    public static <T> T getObject(@NonNull Class<T> cls) {
        try {
            return cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据数据和类型返回
     */
    public static <T> T getObject(Object obj, @NonNull Class<T> cls) {
        if (obj != null && cls.isAssignableFrom(obj.getClass())) {
            return (T) obj;
        } else {
            try {
                return cls.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
