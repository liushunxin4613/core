package com.leo.core.inter.api;

import android.support.annotation.NonNull;

/**
 * 流处理接口
 * @param <T> 本身泛型
 * @param <I> 传入数据泛型
 * @param <P> 传入参数泛型
 * @param <R> 传出参数泛型
 */
public interface IStreamApi<T extends IApi, I, P, R> extends IApi<T> {

    /**
     * 获取流
     * @param in 传入数据
     * @param param 传入参数
     * @return 传出流
     */
    R getStream(@NonNull I in, P param);

}
