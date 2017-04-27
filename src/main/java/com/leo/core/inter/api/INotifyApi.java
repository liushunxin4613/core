package com.leo.core.inter.api;

import android.content.Intent;
import android.support.annotation.NonNull;

/**
 * 通知接口
 * @param <T> 本身
 */
public interface INotifyApi<T extends IContextApi> extends IContextApi<T>{

    /**
     * 发送通知
     * @param notify 通知类
     * @return 通知
     */
    T sendNotify(@NonNull Notify notify);

    /**
     * 通知数据
     */
    class Notify {
        private static int id;//自增长
        private int smallIcon;//图标
        private String ticker;//提示
        private String title;//标题
        private String content;//正文
        private Intent intent;//需跳转intent
        private boolean sound;//声音
        private boolean vibrate;//震动
        private boolean lights;//闪光

        public Notify(int smallIcon, String ticker, String title, String content, Intent intent,
                      boolean sound, boolean vibrate, boolean lights) {
            id++;
            this.smallIcon = smallIcon;
            this.ticker = ticker;
            this.title = title;
            this.content = content;
            this.intent = intent;
            this.sound = sound;
            this.vibrate = vibrate;
            this.lights = lights;
        }

        public static int getId() {
            return id;
        }

        public int getSmallIcon() {
            return smallIcon;
        }

        public void setSmallIcon(int smallIcon) {
            this.smallIcon = smallIcon;
        }

        public String getTicker() {
            return ticker;
        }

        public void setTicker(String ticker) {
            this.ticker = ticker;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Intent getIntent() {
            return intent;
        }

        public void setIntent(Intent intent) {
            this.intent = intent;
        }

        public boolean isSound() {
            return sound;
        }

        public void setSound(boolean sound) {
            this.sound = sound;
        }

        public boolean isVibrate() {
            return vibrate;
        }

        public void setVibrate(boolean vibrate) {
            this.vibrate = vibrate;
        }

        public boolean isLights() {
            return lights;
        }

        public void setLights(boolean lights) {
            this.lights = lights;
        }
    }
}
