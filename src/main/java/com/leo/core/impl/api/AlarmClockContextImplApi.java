package com.leo.core.impl.api;

import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;
import android.support.annotation.NonNull;

import com.leo.core.R;
import com.leo.core.inter.api.IAlarmClockApi;
import com.leo.core.utils.ToastUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * 闹钟以及定时器处理类
 * <br>
 *     只能绑定短Context
 */
public class AlarmClockContextImplApi implements IAlarmClockApi<AlarmClockContextImplApi> {

    private Context context;
    private Calendar calendar;

    @Override
    public AlarmClockContextImplApi bind(@NonNull Context context) {
        this.context = context;
        calendar = Calendar.getInstance();
        return this;
    }

    @Override
    public AlarmClockContextImplApi addAlarmClock(@NonNull String title, @NonNull Date date) {
        calendar.setTime(date);
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.EXTRA_MESSAGE, title);
        intent.putExtra(AlarmClock.EXTRA_HOUR, calendar.get(Calendar.HOUR_OF_DAY));
        intent.putExtra(AlarmClock.EXTRA_MINUTES, calendar.get(Calendar.MINUTE));
        if (intent.resolveActivity(context.getPackageManager()) != null)
            context.startActivity(intent);
        return this;
    }

    @Override
    public AlarmClockContextImplApi addTimer(@NonNull String title, int sec, boolean ui) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {//api大于19,4.4
            Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER);
            intent.putExtra(AlarmClock.EXTRA_MESSAGE, title);
            intent.putExtra(AlarmClock.EXTRA_LENGTH, sec);
            intent.putExtra(AlarmClock.EXTRA_SKIP_UI, ui);
            if (intent.resolveActivity(context.getPackageManager()) != null)
                context.startActivity(intent);
        }else {
            ToastUtil.show(R.string.apibzctjdsq);
        }
        return this;
    }

    @Override
    public AlarmClockContextImplApi showAllAlarm() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            Intent intent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
            if (intent.resolveActivity(context.getPackageManager()) != null)
                context.startActivity(intent);
        }else {
            ToastUtil.show(R.string.apibzcxsnz);
        }
        return this;
    }

}
