package com.lshlmy.study.common.util;

import java.util.Properties;

import javax.servlet.ServletContext;

import com.lshlmy.study.common.constant.SysPropConstant;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * @author lshlmy
 * @description 系统环境工具类
 */
public class SystemEnvironmentUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    private static ServletContext servletContext;
    private static Properties properties;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取Spring的Bean对象
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * 获取Spring的Bean对象
     */
    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    /**
     * 获取Spring的Bean对象
     */
    public static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }

    /**
     * 获取ServletContext对象
     */
    public static ServletContext getServletContext() {
        return servletContext;
    }

    /**
     * 获取Properties的属性值
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SystemEnvironmentUtil.applicationContext = applicationContext;
        servletContext = ((WebApplicationContext) applicationContext).getServletContext();
        properties = SystemEnvironmentUtil.getBean("systemProperties", Properties.class);
        servletContext.setAttribute("path", getProperty(SysPropConstant.SysWebPath));
        servletContext.setAttribute("resources", getProperty(SysPropConstant.SysWebSkin));
    }
}
