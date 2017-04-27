package com.leo.core.inter.api;

/**
 * pull式xml处理接口
 * @param <T> 本身
 * @param <S> 标签开始泛型
 * @param <N> 标签内容泛型
 * @param <R> 返回数据泛型
 */
public interface IXmlPullApi<T extends IApi, S, N, R> extends IApi<T> {

    /**
     * 开始解析
     *
     * @return 本身
     */
    T xmlOpen();

    /**
     * 开始解析标签
     *
     * @param parser 标签
     * @return 本身
     */
    T xmlStart(S parser);

    /**
     * 解析标签内容
     *
     * @param text 内容
     * @return 本身
     */
    T xmlText(N text);

    /**
     * 结束解析标签
     *
     * @param name 标签名称
     * @return 本身
     */
    T xmlEnd(String name);

    /**
     * 结束解析
     *
     * @return 返回内容泛型
     */
    R xmlClose();

}
