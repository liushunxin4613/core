package com.leo.core.impl.api;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.leo.core.config.Config;
import com.leo.core.inter.api.IClockApi;
import com.leo.core.manage.MainManage;
import com.leo.core.utils.LogUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 定时器处理类
 */
public class ClockImplApi implements IClockApi<ClockImplApi> {

    private final static String CLOCK_SHARED_PREFERENCES = Config.CLOCK_SHARED_PREFERENCES;

    private Context context;
    private SingleDataStoredImplApi api;

    @Override
    public ClockImplApi bind(@NonNull Context context) {
        this.context = context;
        api = MainManage.getContextApi(SingleDataStoredImplApi.class)
                .switchTable(CLOCK_SHARED_PREFERENCES);
        return this;
    }

    @Override
    public ClockImplApi addClock(@NonNull String title, String content, @NonNull Date date, int type,
                                 @NonNull Class<?> broadcast) {
        Intent intent = new Intent(context, broadcast)
                .putExtra("title", title)
                .putExtra("content", content);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.set(type, date.getTime(),
                PendingIntent.getBroadcast(context, 0, intent, 0));
        api.saveData(title, new Clock(title, date, type));
        return this;
    }

    @Override
    public Map<String, Clock> getClockData() {
        Map<String, Clock> map = new HashMap<>();
        LogUtil.e(this, new Gson().toJson(api.getAllBean()));
        Clock clock;
        for (Map.Entry<String, ?> entry : api.getAllBean().entrySet()) {
            if (entry.getValue() instanceof String) {
                try {
                    clock = new Gson().fromJson((String) entry.getValue(), Clock.class);
                    if (clock != null){
                        map.put(entry.getKey(), clock);
                    }
                }catch (Exception ignored){

                }
            }
        }
        return map;
    }

}
