package com.leo.core.impl.api;

import android.content.Context;
import android.support.annotation.NonNull;

import com.leo.core.impl.ab.AFileOutputStreamImplApi;
import com.leo.core.inter.api.IContextApi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * APP内部文件输出流(OpenFileOutputStream)
 */
public class OpenFileOutputStreamImplApi extends AFileOutputStreamImplApi<OpenFileOutputStreamImplApi> implements IContextApi<OpenFileOutputStreamImplApi> {

    private Context context;

    @Override
    public OpenFileOutputStreamImplApi bind(@NonNull Context context) {
        this.context = context;
        return this;
    }

    /**
     * 获取文件输出流
     * @param in 文件名称
     * @param mode 存储模式
     * @return 文件输出流
     */
    @Override
    public FileOutputStream getStream(@NonNull String in, @NonNull Integer mode) {
        try {
            return context.openFileOutput(in, mode);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public FileOutputStream getStream(@NonNull String in) {
        return getStream(in, Context.MODE_APPEND);
    }
}
