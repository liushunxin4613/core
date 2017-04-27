package com.leo.core.inter.api;

import android.support.annotation.NonNull;

/**
 * 写入数据API
 * @param <T> 本身泛型
 * @param <I> 输入泛型
 * @param <P> 输入参数泛型
 */
public interface IWriteApi<T extends IApi, I, P> extends IApi<T> {

    /**
     * 写入数据
     * @param in 传入数据
     * @param param 传入参数
     * @return 本身
     */
    T write(I in, @NonNull P param);

}
