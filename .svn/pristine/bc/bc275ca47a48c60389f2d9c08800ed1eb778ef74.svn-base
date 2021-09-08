package com.yn.electricity.socket;

import org.yeauty.pojo.Session;

import java.util.Objects;

/**
 * @author admin
 * Date 2021/1/4 14:46
 * Description
 **/
public class SocketSession {

    private Session session;
    /**
     * 设备编码
     */
    private String code;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "SocketSession{" +
                "session=" + session +
                ", code='" + code + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        SocketSession that = (SocketSession) o;
        return Objects.equals(session, that.session) &&
                Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(session, code);
    }
}
