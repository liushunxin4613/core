package com.leo.core.inter.api;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.util.Date;
import java.util.Map;

/**
 * 定时器接口
 * @param <T> 本身
 */
public interface IClockApi<T extends IContextApi> extends IContextApi<T> {

    /**
     * 添加定时器
     *
     * @param title     名称
     * @param content   内容
     * @param date      时间
     * @param type      类型
     * @param broadcast 提示的广播类
     * @return 是否成功添加
     */
    T addClock(@NonNull String title, String content, @NonNull Date date, int type, @NonNull Class<?> broadcast);

    /**
     * 获取定时器列表
     *
     * @return 定时器列表
     */
    Map<String, Clock> getClockData();

    /**
     * 闹钟bean
     */
    class Clock {
        private String name;
        private Date date;
        private int dateType;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public int getDateType() {
            return dateType;
        }

        public void setDateType(int dateType) {
            this.dateType = dateType;
        }

        public Clock(String name, Date date, int dateType) {
            this.name = name;
            this.date = date;
            this.dateType = dateType;
        }

        @Override
        public String toString() {
            return new Gson().toJson(this);
        }
    }
}
