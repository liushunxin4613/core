package com.leo.core.inter.api;

/**
 * 存储API
 * @param <T> 本身泛型
 * @param <I> 输入泛型
 * @param <R> 输出泛型
 */
public interface IStorageApi<T extends IContextApi, I, R> extends IContextApi<T> {

    /**
     * 获取存储路径
     * @param in 输入数据
     * @return 存储路径
     */
    R getStoragePath(I in);

}
