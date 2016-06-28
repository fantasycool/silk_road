package com.rpc.silkroad.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by frio on 16/6/24.
 */
public class IniterContext {
    /**
     * version cache
     */
    public static ConcurrentHashMap<Class, String> loadedServes = new ConcurrentHashMap<Class, String>();
    /**
     * ThreadLocal cache
     */
    public static final ThreadLocal threadLocal = new ThreadLocal();

    public static void putServeVersion(Class cls, String version){
        loadedServes.put(cls, version);
    }

    public static String getServeVersion(Class cls){
        return loadedServes.get(cls);
    }

    public static void set(Map<String, Object> contextParams){
        threadLocal.set(contextParams);
    }

    public static Object get(){
        return threadLocal.get();
    }

    public static void remove(){
        threadLocal.remove();
    }
}
