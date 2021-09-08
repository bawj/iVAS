package com.yn.electricity.socket;

import com.alibaba.fastjson.JSON;
import com.yn.electricity.entity.SocketResultVO;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.yeauty.annotation.*;
import org.yeauty.pojo.ParameterMap;
import org.yeauty.pojo.Session;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Consumer;


/**
 * @author webSocket
 */
@ServerEndpoint(port = 88)
@Component
@Slf4j
public class NettyWebSocket {

    private final static CopyOnWriteArraySet<SocketSession> webSockets = new CopyOnWriteArraySet<>();
//    private final static CopyOnWriteArraySet<Session> webSockets = new CopyOnWriteArraySet<>();

    /**
     * 当有新的WebSocket连接进入时
     *
     * @param session      session
     * @param headers      headers
     * @param parameterMap parameterMap
     * @throws IOException
     */
    @OnOpen
    public void onOpen(Session session, HttpHeaders headers, ParameterMap parameterMap) throws IOException {
        String code = parameterMap.getParameter("code");
        log.info("#NettyWebSocket.onOpen# 有新的链接加入 session = {} code = {}", session, code);
        SocketSession socketSession = new SocketSession();
        socketSession.setSession(session);
        webSockets.add(socketSession);
    }

    /**
     * 当有WebSocket连接关闭时
     *
     * @param session session
     * @throws IOException IOException
     */
    @OnClose
    public void onClose(Session session) throws IOException {
        log.info("#NettyWebSocket.onClose# 关闭链接 session = {} ", session);
        for (SocketSession webSocket : webSockets) {
            Session session1 = webSocket.getSession();
            if (session1.equals(session)) {
                webSockets.remove(webSocket);
                break;
            }
        }
    }

    /**
     * 当有WebSocket抛出异常时
     *
     * @param session   session
     * @param throwable throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("#NettyWebSocket.onError# Socket异常 session = {} ", session);
        throwable.printStackTrace();
    }

    /**
     * 当接收到字符串消息时，对该方法进行回调
     *
     * @param session session
     * @param message message
     */
    @OnMessage
    public void onMessage(Session session, String message) {
        log.error("#NettyWebSocket.onMessage# Socket收到消息 session = {} message = {} ", session, message);
    }

    /**
     * 当接收到二进制消息时
     *
     * @param session session
     * @param bytes   bytes
     */
    @OnBinary
    public void onBinary(Session session, byte[] bytes) {
        log.error("#NettyWebSocket.onBinary# Socket收到消息 session = {} bytes = {} ", session, bytes);
        //session.sendBinary(bytes);
    }

    /**
     * 当接收到Netty的事件时
     *
     * @param session session
     * @param evt     evt
     */
    @OnEvent
    public void onEvent(Session session, Object evt) {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    System.out.println("read idle");
                    break;
                case WRITER_IDLE:
                    System.out.println("write idle");
                    break;
                case ALL_IDLE:
                    System.out.println("all idle");
                    break;
                default:
                    break;
            }
        }
    }

    public void sendAllMessages(String message) {
        webSockets.forEach(new Consumer<SocketSession>() {
            @Override
            public void accept(SocketSession socketSession) {
                Session session = socketSession.getSession();
                session.sendText(message);
            }
        });
    }


    private <T> void sendMessage(String code, SocketResultVO<T> socketResult) {
        webSockets.forEach(new Consumer<SocketSession>() {
            @Override
            public void accept(SocketSession socketSession) {
                Session session = socketSession.getSession();
                if (code != null) {
                    if (socketSession.getCode().equals(code)) {
                        String msg = JSON.toJSONString(socketResult);
                        session.sendText(msg);
                    }
                }
            }
        });
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}