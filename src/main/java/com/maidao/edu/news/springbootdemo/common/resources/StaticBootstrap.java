package com.maidao.edu.news.springbootdemo.common.resources;

import com.maidao.edu.Application;
import com.maidao.edu.news.springbootdemo.common.util.L;
import com.maidao.edu.news.springbootdemo.common.util.ProcessUtils;
import org.reflections.Reflections;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:StaticBootstrap
 * 类描述:TODO
 **/
@Service
public class StaticBootstrap {

    @PostConstruct
    public void init() {
        Reflections reflections = Application.getAppReflection();
        {
            Set<Class<?>> staticInitClasses = reflections.getTypesAnnotatedWith(StaticInit.class);
            for (Class<?> clazz : staticInitClasses) {
                try {
                    if (L.isInfoEnabled()) {
                        L.info("[StaticBootstrap] calling: " + clazz.getCanonicalName());
                    }
                    Class.forName(clazz.getCanonicalName());
                } catch (Throwable t) {
                    ProcessUtils.exitWithMessage(null, t);
                }
            }
        }
        if (L.isInfoEnabled()) {
            L.info("[StaticBootstrap] done");
        }
    }

}
