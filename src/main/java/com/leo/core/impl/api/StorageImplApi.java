package com.leo.core.impl.api;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.leo.core.inter.api.IStorageApi;
import com.leo.core.utils.LogUtil;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取存储空间
 */
public class StorageImplApi implements IStorageApi<StorageImplApi, String, String> {

    private Context context;

    @Override
    public StorageImplApi bind(@NonNull Context context) {
        this.context = context;
        return this;
    }

    /**
     * 排除数据
     * @param exclude 输入数据
     * @return
     */
    @Override
    public String getStoragePath(String exclude) {
        List<String> pathsList = getStoragePaths(exclude);
        LogUtil.e(this, pathsList);
        if (pathsList.size() > 0){
            return pathsList.get(0);
        }
        return null;
    }

    /**
     * 获取存储路径地址
     * @param exclude 排除数据
     * @return 存储路径地址列表
     */
    public List<String> getStoragePaths(String exclude) {
        List<String> pathsList = new ArrayList<>();
        StorageManager storageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
        try {
            Method method = StorageManager.class.getDeclaredMethod("getVolumePaths");
            method.setAccessible(true);
            Object result = method.invoke(storageManager);
            if (result != null && result instanceof String[]) {
                String[] pathes = (String[]) result;
                StatFs statFs;
                for (String path : pathes) {
                    if (!TextUtils.isEmpty(path) && new File(path).exists()) {
                        statFs = new StatFs(path);
                        if (statFs.getBlockCount() * statFs.getBlockSize() != 0) {
                            pathsList.add(path);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            File externalFolder = Environment.getExternalStorageDirectory();
            if (externalFolder != null) {
                pathsList.add(externalFolder.getAbsolutePath());
            }
        }
        if (pathsList.contains(exclude)){
            pathsList.remove(exclude);
        }
        return pathsList;
    }

}
