package it.hxl.myblogprod.sessionmanager;


import lombok.extern.slf4j.Slf4j;

import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author buhao
 * @version WsSessionManager.java, v 0.1 2019-10-22 10:24 buhao
 */
@Slf4j
public class MySessionManager {
    /**
     * 保存连接 session 的地方
     */
    private static ConcurrentHashMap<String, Session> SESSION_POOL = new ConcurrentHashMap<>();

    /**
     * 添加 session
     *
     * @param key
     */
    public static void add(String key, Session session) {
        // 添加 session
        SESSION_POOL.put(key, session);
    }

    /**
     * 删除 session,会返回删除的 session
     *
     * @param key
     * @return
     */
    public static Session remove(String key) {
        // 删除 session
        return SESSION_POOL.remove(key);
    }

    /**
     * 删除并同步关闭连接
     *
     * @param key
     */
    public static void removeAndClose(String key) {
        Session session = remove(key);
        if (session != null) {
            try {
                // 关闭连接
                session.close();
            } catch (IOException e) {
                // todo: 关闭出现异常处理
                e.printStackTrace();
            }
        }
    }

    /**
     * 获得 session
     *
     * @param key
     * @return
     */
    public static Session get(String key) {
        // 获得 session
        return SESSION_POOL.get(key);
    }

    public static Set<Map.Entry<String, Session>> getEntry(){

        return SESSION_POOL.entrySet();
    }

    public static List<String> getUserNames(){
        List<String> sessionIds = new ArrayList();
        getEntry().forEach((entry)->{
            sessionIds.add(entry.getKey());
        });
        return sessionIds;
    }

    public static List<Session> getSessions(){
        List<Session> sessions = new ArrayList();
        getEntry().forEach((entry)->{
            sessions.add(entry.getValue());
        });
        return sessions;
    }


}