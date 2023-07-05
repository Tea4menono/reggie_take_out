package org.example.reggie.common;

public class BaseContext {

    private static final ThreadLocal<Long> threadLocal = new ThreadLocal();

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentID() {
        return threadLocal.get();
    }
}
