package com.leo.core.manage;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.leo.core.impl.main.ApiFactory;
import com.leo.core.inter.api.IApi;
import com.leo.core.inter.api.IContextApi;

/**
 * 公共管理类
 */
public class MainManage {

    private static Context context;//解析类

    public static void init(@NonNull Application context) {
        MainManage.context = context;
    }

    /**
     * 按照cls名称返回IContext子类
     *
     * @param cls IContext子类名
     * @param <T> 继承于IContext的类
     * @return IContext子类
     */
    public static <T extends IContextApi> T getContextApi(@NonNull Class<T> cls) {
        if (context == null){
            throw new NullPointerException("MainManage 没有 init(Application context)");
        }
        return ApiFactory.getInstance().getApi(cls, context);
    }

    /**
     * 按照cls名称返回IBase子类
     *
     * @param cls IBase子类名
     * @param <T> 继承于IBase的类
     * @return IBase子类
     */
    public static <T extends IApi> T getApi(@NonNull Class<T> cls) {
        return ApiFactory.getInstance().getApi(cls);
    }

}
