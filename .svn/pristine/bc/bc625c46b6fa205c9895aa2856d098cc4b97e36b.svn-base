package com.yn.electricity.security;

import com.yn.electricity.enums.ErrorCommonEnum;
import com.yn.electricity.util.SpringUtils;
import com.yn.electricity.web.WebResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.MapCache;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * @author hxy
 */
@Slf4j
public class InterceptorHandler extends AccessControlFilter {

    private static final Cache<String, Deque<Serializable>> CACHE = new MapCache<>("cache", new LinkedHashMap<>());

    public static final String KIC_OUT_PROPERTY_NAME = "kicOut";

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        //如果用户没有登录且没有配置『记住我』
        Subject subject = SecurityUtils.getSubject();
        if (subject == null) {
            WebResult.error(ErrorCommonEnum.NOT_login_ERROR, response);
        }else {
            boolean authenticated = subject.isAuthenticated();
            if (!authenticated){
                WebResult.error(ErrorCommonEnum.NOT_login_ERROR, response);
            }else {
                return deque(subject , response);
            }
        }
        return false;
    }

    private boolean deque(Subject subject, ServletResponse response) {
        Session session = subject.getSession();
        String loginName = (String) subject.getPrincipal();
        Serializable sessionId = session.getId();

        //初始化用户队列 放进缓存
        Deque<Serializable> deque = CACHE.get(loginName);
        if (deque == null || deque.size() == 0) {
            deque = new LinkedList<>();
            CACHE.put(loginName, deque);
        }

        //如果用户队列里没有此sessionId,且没有被踢出 则放入队列 并放入缓存
        if (!deque.contains(sessionId) && session.getAttribute(KIC_OUT_PROPERTY_NAME) == null) {
            deque.push(sessionId);
        }
        //如果队列里的用户数量超过最大值 开始踢人
        while (deque.size() > 1) {
            Serializable kicOutSessionId;
            //踢出前者
            kicOutSessionId = deque.removeLast();
            DefaultSessionKey defaultSessionKey = new DefaultSessionKey(kicOutSessionId);
            Session outSession = getSessionManager().getSession(defaultSessionKey);
            if (outSession != null) {
                //设置此属性为true表示这个会话被踢出了
                outSession.setAttribute(KIC_OUT_PROPERTY_NAME, true);
            }
        }
        if (session.getAttribute(KIC_OUT_PROPERTY_NAME) != null) {
            // 会话被踢出了 从shiro退出登录
            subject.logout();
            WebResult.error(ErrorCommonEnum.LOGIN_ERROR , response);
            return false;
        }
        return true;
    }

    private SessionManager getSessionManager() {
        return (SessionManager) SpringUtils.getBean("sessionManager");
    }

    public static void removeCache(String key){
        CACHE.remove(key);
    }

}
