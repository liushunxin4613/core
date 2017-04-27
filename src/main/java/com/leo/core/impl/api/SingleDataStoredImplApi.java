package com.leo.core.impl.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.leo.core.config.Config;
import com.leo.core.impl.ParameterizedTypeImpl;
import com.leo.core.inter.api.ISingleDataStoredApi;
import com.leo.core.utils.LogUtil;

import java.util.List;
import java.util.Map;

/**
 * 单键值表数据处理类
 */
public class SingleDataStoredImplApi implements ISingleDataStoredApi<SingleDataStoredImplApi> {

    private static final String APP_SHARED_PREFERENCES = Config.APP_SHARED_PREFERENCES;
    private static final int MODE_DEFAULT = Context.MODE_PRIVATE;//默认mode,私有数据
    private static final String LIST_END = ".list";//list数据标志
    private Context context;
    private SharedPreferences preferences;
    private Gson gson;
    private int mode = MODE_DEFAULT;

    @Override
    public SingleDataStoredImplApi bind(@NonNull Context context) {
        this.context = context;
        gson = new Gson();
        return this;
    }

    @Override
    public SingleDataStoredImplApi switchTable(@NonNull String key, int mode) {
        this.mode = mode;
        preferences = context.getSharedPreferences(key, mode);
        return this;
    }

    /**
     * 切换数据表
     *
     * @param key 表名
     * @return 本身
     */
    public SingleDataStoredImplApi switchTable(@NonNull String key) {
        preferences = context.getSharedPreferences(key, mode);
        return this;
    }

    /**
     * 切换到默认表
     * @return 本身
     */
    public SingleDataStoredImplApi switchDefault() {
        switchTable(APP_SHARED_PREFERENCES, mode);
        return this;
    }

    /**
     * 检查表是否存在
     *
     * @return true为存在
     */
    private boolean checkPreferences() {
        if (preferences == null) {
            throw new NullPointerException("SingleDataStoredImplApi 没有switchTable(key,mode), preferences不能为空");
        }
        return true;
    }

    @Override
    public <V> SingleDataStoredImplApi saveData(@NonNull String key, @NonNull V value) {
        checkPreferences();
        preferences.edit().putString(key, gson.toJson(value)).apply();
        return this;
    }

    /**
     * 存储单个数据,key为类名
     *
     * @param value 数据
     * @param <V>   数据类型
     * @return 本身
     */
    public <V> SingleDataStoredImplApi saveData(@NonNull V value) {
        saveData(value.getClass().getName(), value);
        return this;
    }

    @Override
    public <V> SingleDataStoredImplApi saveData(@NonNull String key, @NonNull List<V> value) {
        if (value.size() > 0) {
            saveData(key + LIST_END, value);
        } else {
            LogUtil.e(this, "添加数据失败,List数据数不能为0");
        }
        return this;
    }

    /**
     * 存储多个数据
     *
     * @param value List数据
     * @param <V>   List 数据类型
     * @return 本身
     */
    public <V> SingleDataStoredImplApi saveData(@NonNull List<V> value) {
        if (value.size() > 0) {
            saveData(value.get(0).getClass().getName() + LIST_END, value);
        } else {
            LogUtil.e(this, "添加数据失败,List数据数不能为0");
        }
        return this;
    }

    @Override
    public String getString(@NonNull String key) {
        checkPreferences();
        return preferences.getString(key, null);
    }

    @Override
    public <C> C getBean(@NonNull String key, @NonNull Class<C> cls) {
        checkPreferences();
        return gson.fromJson(preferences.getString(key, null), cls);
    }

    @Override
    public <C> List<C> getBeanData(@NonNull String key, @NonNull Class<? extends List> lCls, @NonNull Class<C> cls) {
        checkPreferences();
        return gson.fromJson(preferences.getString(key + LIST_END, null), ParameterizedTypeImpl.get(lCls, cls));
    }

    /**
     * 按cls类名获取多个数据
     *
     * @param lCls List子类类名
     * @param cls  数据类名
     * @param <C>  数据类泛型
     * @return 类集合数据
     */
    public <C> List<C> getBeanData(@NonNull Class<? extends List> lCls, @NonNull Class<C> cls) {
        return getBeanData(cls.getName(), lCls, cls);
    }

    @Override
    public Map<String, ?> getAllBean() {
        checkPreferences();
        return preferences.getAll();
    }

    /**
     * 获取该表所有数据
     *
     * @return json String 数据
     */
    public String getAllBeanJsonString() {
        checkPreferences();
        return gson.toJson(preferences.getAll());
    }

    @Override
    public SingleDataStoredImplApi remove(@NonNull String key) {
        checkPreferences();
        preferences.edit().remove(key).apply();
        return this;
    }

    @Override
    public SingleDataStoredImplApi removeAll() {
        checkPreferences();
        preferences.edit().clear().apply();
        return this;
    }

    /**
     * 按cls删除数据
     *
     * @param cls 类
     * @return 本身
     */
    public SingleDataStoredImplApi remove(@NonNull Class cls) {
        remove(cls.getName());
        return this;
    }

    /**
     * 按key删除List数据
     *
     * @param key 键
     * @return 本身
     */
    public SingleDataStoredImplApi removeList(@NonNull String key) {
        remove(key + LIST_END);
        return this;
    }

    /**
     * 按cls删除List数据
     *
     * @param cls 类
     * @return 本身
     */
    public SingleDataStoredImplApi removeList(@NonNull Class cls) {
        remove(cls.getName() + LIST_END);
        return this;
    }
}
