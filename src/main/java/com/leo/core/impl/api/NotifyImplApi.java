package com.leo.core.impl.api;

import android.app.PendingIntent;
import android.content.Context;
import android.support.annotation.NonNull;

import com.leo.core.inter.api.INotifyApi;
import com.leo.core.utils.NotifyUtil;

/**
 * 通知处理类
 */
public class NotifyImplApi implements INotifyApi<NotifyImplApi> {

    private Context context;

    @Override
    public NotifyImplApi bind(@NonNull Context context) {
        this.context = context;
        return this;
    }

    @Override
    public NotifyImplApi sendNotify(@NonNull Notify notify) {
        //实例化工具类，并且调用接口
        NotifyUtil util = new NotifyUtil(context, Notify.getId());
        PendingIntent pIntent = PendingIntent.getActivity(context,
                util.getRequestCode(), notify.getIntent(), PendingIntent.FLAG_UPDATE_CURRENT);
        util.notify_normal_singline(pIntent, notify.getSmallIcon(), notify.getTicker(), notify.getTitle(),
                notify.getContent(), notify.isSound(), notify.isVibrate(), notify.isLights());
        return this;
    }
}
