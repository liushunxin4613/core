package com.leo.core.inter.api;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * 所有绑定Context类母接口
 */
public interface IContextApi<T extends IApi> extends IApi<T>{

    /**
     * 绑定
     * <br>
     *     此方法应当在所有方法前
     * @param context 上下文
     * @return 本身
     */
    T bind(@NonNull Context context);

}
