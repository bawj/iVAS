package com.yn.electricity.entity;

/**
 * @author admin
 * Date 2020/12/17 15:45
 * Description socket 返回数据封装
 **/
public class SocketResultVO<T> {

    private Integer topic;

    private T content;

    public Integer getTopic() {
        return topic;
    }

    public void setTopic(Integer topic) {
        this.topic = topic;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T t) {
        this.content = t;
    }

    @Override
    public String toString() {
        return "SocketResultVO{" +
                "topic=" + topic +
                ", content=" + content +
                '}';
    }
}
