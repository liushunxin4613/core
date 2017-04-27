package com.leo.core.inter;

/**
 * 传递消息
 */
public interface OnMessage {

    /**
     * 传递消息
     */
    <T> void onMessage(T key, Object obj);

}
