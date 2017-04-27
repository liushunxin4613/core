package com.leo.core.impl.api;

import android.content.Context;
import android.support.annotation.NonNull;

import com.leo.core.inter.api.IReadApi;

import java.io.InputStream;

/**
 * 资源IO Stream API
 */
public class ResStreamReadImplApi implements IReadApi<ResStreamReadImplApi, String, String, InputStream> {

    private Context context;

    @Override
    public ResStreamReadImplApi bind(@NonNull Context context) {
        this.context = context;
        return null;
    }

    @Override
    public InputStream read(@NonNull String in, String param) {
        return context.getClass().getClassLoader().getResourceAsStream(in);
    }

    /**
     * 读取
     *
     * @param in 传入
     * @return 本身
     */
    public InputStream read(@NonNull String in) {
        return read(in, null);
    }

}
