package org.smartx.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring utils
 *
 * @author yaoab
 */
public final class SpringUtils {

    private static ApplicationContext applicationContext = null;

    private static final Logger logger = LoggerFactory.getLogger(SpringUtils.class);

    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    public static ApplicationContext getApplicationContext() {
        initContext();
        return applicationContext;
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        initContext();
        return (T) applicationContext.getBean(name);
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(String name, Class<T> requiredType) {
        initContext();
        return applicationContext.getBean(name, requiredType);
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(Class<T> requiredType) {
        initContext();
        return applicationContext.getBean(requiredType);
    }

    /**
     * 清除SpringUtils中的ApplicationContext为Null.
     */
    public static void clear() {
        logger.debug("清除Spring上下文ApplicationContext:" + applicationContext);
        applicationContext = null;
    }

    /**
     * 初始化spring上下文
     */
    private static void initContext() {
        if (null == applicationContext) {
            synchronized (SpringUtils.class) {
                applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
                ((ConfigurableApplicationContext) applicationContext).registerShutdownHook();
            }
        }
    }
}
