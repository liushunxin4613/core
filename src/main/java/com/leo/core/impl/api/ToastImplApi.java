package com.leo.core.impl.api;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.leo.core.inter.api.IShowApi;

/**
 * ToastApi类
 */
public class ToastImplApi implements IShowApi<ToastImplApi, Object, Integer> {

    private Context context;

    @Override
    public ToastImplApi bind(@NonNull Context context) {
        this.context = context;
        return this;
    }

    /**
     * 显示数据
     * @param in 输入数据
     * @param duration 显示参数
     * @return 本身
     */
    @Override
    public ToastImplApi show(@NonNull Object in, @NonNull Integer duration) {
        checkContext();
        if (in instanceof CharSequence){
            Toast.makeText(context, (CharSequence) in, duration).show();
        }else if (in instanceof Integer){
            Toast.makeText(context, (Integer) in, duration).show();
        }else {
            Toast.makeText(context, in.toString(), duration).show();
        }
        return this;
    }

    /**
     * 显示数据
     * @param in 输入数据
     * @return 本身
     */
    public ToastImplApi show(@NonNull CharSequence in) {
        show(in, Toast.LENGTH_SHORT);
        return this;
    }

    /**
     * 显示数据
     * @param resId string id
     * @return 本身
     */
    public ToastImplApi show(@StringRes int resId) {
        show(resId, Toast.LENGTH_SHORT);
        return this;
    }

    private void checkContext(){
        if (context == null){
            throw new NullPointerException("ToastImplApi 没有bind(context)");
        }
    }

}
