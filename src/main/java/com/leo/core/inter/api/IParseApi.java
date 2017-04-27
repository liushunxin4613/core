package com.leo.core.inter.api;

import android.support.annotation.NonNull;

/**
 * xml解析
 *
 * @param <T> 本身
 * @param <I> 传入数据泛型
 * @param <P> 传入参数泛型
 * @param <R> 返回泛型
 */
public interface IParseApi<T extends IApi, I, P, R> extends IApi<T> {

    /**
     * 解析xml
     *
     * @param in    传入数据
     * @param param 传入参数
     * @return 返回解析数据
     */
    R parse(@NonNull I in, P param);

}
