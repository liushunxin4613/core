package com.leo.core.inter.api;

import android.support.annotation.NonNull;

import java.util.Date;

/**
 * 闹钟以及计时器
 */
public interface IAlarmClockApi<T extends IContextApi> extends IContextApi<T> {

    /**
     * 添加闹钟
     *
     * @param title 标题
     * @param date  时间
     * @return 本身
     */
    T addAlarmClock(@NonNull String title, @NonNull Date date);

    /**
     * 设置计时器
     *
     * @param title 消息
     * @param sec   时间(秒)
     * @param ui    true为后台计时(通常通知栏会有提示)，false会弹出计时界面
     * @return 本身
     */
    T addTimer(@NonNull String title, int sec, boolean ui);

    /**
     * 列出所有闹钟
     *
     * @return 本身
     */
    T showAllAlarm();

}
