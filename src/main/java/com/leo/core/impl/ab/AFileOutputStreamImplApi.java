package com.leo.core.impl.ab;

import android.support.annotation.NonNull;

import com.leo.core.inter.api.IStreamApi;

import java.io.FileOutputStream;

/**
 * 文件输出流抽象流
 *
 * @param <T> 本身
 */
public abstract class AFileOutputStreamImplApi<T extends IStreamApi> implements IStreamApi<T, String, Integer, FileOutputStream> {
    /**
     * 获取文件输出流
     *
     * @param in 输入数据
     * @return 文件输出流
     */
    public abstract FileOutputStream getStream(@NonNull String in);
}
