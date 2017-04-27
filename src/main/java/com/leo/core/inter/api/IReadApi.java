package com.leo.core.inter.api;

import android.support.annotation.NonNull;

/**
 * 读取操作接口
 * @param <T> 本身
 * @param <I> 读入泛型
 * @param <P> 参数
 * @param <R> 读取返回值
 */
public interface IReadApi<T extends IContextApi, I, P, R> extends IContextApi<T> {

    /**
     * 读取
     * @param in 传入
     * @param param 传入参数
     * @return 返回泛型
     */
    R read(@NonNull I in, P param);

}
