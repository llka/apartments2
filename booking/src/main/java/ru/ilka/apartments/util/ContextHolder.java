package ru.ilka.apartments.util;

import org.springframework.context.ApplicationContext;

/**
 * Here could be your advertisement.
 * Ilya_Kisel +375 29 3880490
 */
public class ContextHolder {
    private static ApplicationContext applicationContext;

    public ContextHolder() {
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        ContextHolder.applicationContext = applicationContext;
    }
}
