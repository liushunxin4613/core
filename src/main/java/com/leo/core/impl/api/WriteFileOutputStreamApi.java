package com.leo.core.impl.api;

import android.support.annotation.NonNull;

import com.leo.core.inter.api.IWriteApi;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件输出流写入
 */
public class WriteFileOutputStreamApi implements IWriteApi<WriteFileOutputStreamApi, FileOutputStream, String> {

    @Override
    public WriteFileOutputStreamApi write(FileOutputStream in, @NonNull String param) {
        if (in == null){
            return this;
        }
        try {
            in.write(param.getBytes());
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

}
