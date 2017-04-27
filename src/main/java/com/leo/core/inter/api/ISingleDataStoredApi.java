package com.leo.core.inter.api;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.Map;

/**
 * 单键值表数据读取接口
 * <br>
 *     仅有增(改),删,查三项功能,仅支持单键查询
 * @param <T> 本身
 */
public interface ISingleDataStoredApi<T extends IContextApi> extends IContextApi<T> {

    /**
     * 切换表结构
     * <br>
     *     此方法应当在bind后,在其他所有方法前
     * @param key 表名
     * @param mode 数据存储类型
     * @return 本身
     */
    T switchTable(@NonNull String key, int mode);

    //增

    /**
     * 存储单个数据
     * @param key 键
     * @param value 数据
     * @param <V> 数据类型
     * @return 本身
    */
    <V> T saveData(@NonNull String key, @NonNull V value);

    /**
     * 存储多个数据
     * @param key 键
     * @param value List数据
     * @param <V> List 数据类型
     * @return 本身
     */
    <V> T saveData(@NonNull String key, @NonNull List<V> value);

    //查

    /**
     * 按类名获取单个数据
     * @param key 键
     * @return String类型数据
     */
    String getString(@NonNull String key);

    /**
     * 按key获取单个数据
     * @param key 键
     * @param cls 类名
     * @param <C> 类泛型型
     * @return 类数据
     */
    <C> C getBean(@NonNull String key, @NonNull Class<C> cls);

    /**
     * 按key和类名获取多个数据
     * @param key 键
     * @param lCls List子类类名
     * @param cls 数据类名
     * @param <C> 数据类泛型
     * @return 类集合数据
     */
    <C> List<C> getBeanData(@NonNull String key, @NonNull Class<? extends List> lCls,@NonNull Class<C> cls);

    /**
     * 获取某表的所有数据
     * @return 表数据
     */
    Map<String, ?> getAllBean();

    //删

    /**
     * 按key删除数据
     * @param key 键
     * @return 本身
     */
    T remove(@NonNull String key);

    /**
     * 删除该表
     * @return 本身
     */
    T removeAll();

}
