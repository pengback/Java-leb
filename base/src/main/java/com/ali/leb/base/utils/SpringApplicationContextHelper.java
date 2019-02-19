package com.ali.leb.base.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@SuppressWarnings({"rawtypes", "unchecked"})
@Component
public class SpringApplicationContextHelper implements ApplicationContextAware {

    private static final SpringApplicationContextHelper helper =  new SpringApplicationContextHelper();

    private static ApplicationContext applicationContext;

    public SpringApplicationContextHelper() {
    }

    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        applicationContext = arg0;
    }

    public static SpringApplicationContextHelper getInstance(){
        return helper;
    }

    /**
     * 根据bean的id来查找对象
     * @param id
     * @return
     */
    public Object getBeanById(String id){
        return applicationContext.getBean(id);
    }

    /**
     * 根据bean的class来查找对象
     * @param c
     * @return
     */
    public Object getBeanByClass(Class c){
        return applicationContext.getBean(c);
    }

    /**
     * 根据bean的class来查找所有的对象(包括子类)
     * @param c
     * @return
     */
    public Map getBeansByClass(Class c){
        return applicationContext.getBeansOfType(c);
    }
}
