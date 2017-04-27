package com.leo.core.impl.main;

import android.content.Context;
import android.support.annotation.NonNull;

import com.leo.core.inter.api.IApi;
import com.leo.core.inter.api.IContextApi;

import java.util.HashMap;
import java.util.Map;

/**
 * 接口子类生成工厂
 */
public class ApiFactory {

    private static ApiFactory instance;

    private static Map<String, IApi> map;

    private ApiFactory() {
        map = new HashMap<>();
    }

    public static ApiFactory getInstance(){
        if (instance == null){
            synchronized (ApiFactory.class){
                if (instance == null)
                    instance = new ApiFactory();
            }
        }
        return instance;
    }

    public <T extends IApi> T getApi(@NonNull Class<T> cls, Context context){
        String name = cls.getName();
        IApi api = map.get(name);
        if (api == null || !cls.isAssignableFrom(api.getClass())){
            api = ClassUtil.getObject(cls);
            map.put(name, api);
        }
        if (api instanceof IContextApi && context != null){
            ((IContextApi) api).bind(context);
        }
        return (T) api;
    }

    public <T extends IApi> T getApi(@NonNull Class<T> cls){
        return getApi(cls, null);
    }

    public void close(){
        instance = null;
        map = null;
    }

}
