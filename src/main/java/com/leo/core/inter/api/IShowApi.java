package com.leo.core.inter.api;

import android.support.annotation.NonNull;

/**
 * 显示API
 *
 * @param <T> 本身泛型
 * @param <I> 输入泛型
 * @param <P> 输入参数
 */
public interface IShowApi<T extends IContextApi, I, P> extends IContextApi<T> {

    /**
     * 显示数据
     *
     * @param in    输入数据
     * @param param 输入参数
     * @return 本身
     */
    T show(@NonNull I in, @NonNull P param);

}
