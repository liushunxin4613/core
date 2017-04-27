package com.leo.core.impl.api;

import android.os.Environment;
import android.support.annotation.NonNull;

import com.leo.core.impl.ab.AFileOutputStreamImplApi;
import com.leo.core.utils.ToastUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * 存储卡输出流API
 */
public class SDCardFileOutputStreamImplApi extends AFileOutputStreamImplApi<SDCardFileOutputStreamImplApi> {

    @Override
    public FileOutputStream getStream(@NonNull String in, Integer param) {
        try {
            if (!checkSDCard())
                return new FileOutputStream(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean checkSDCard() {
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            ToastUtil.show("存储卡未加载");
            return false;
        }
        return true;
    }

    @Override
    public FileOutputStream getStream(@NonNull String in) {
        return getStream(in, null);
    }

    public FileOutputStream getStream(@NonNull File file) {
        try {
            return new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
