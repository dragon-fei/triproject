package com.use.util.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class ValidationInterceptor implements HandlerInterceptor {

    @Resource
    private MessageSource messageSource;
    private Logger logger = LoggerFactory.getLogger(ValidationInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Object actionObject = handlerMethod.getBean();
            Method method = handlerMethod.getMethod();
            String key = null;
            if(actionObject.getClass().getSimpleName().contains("$$")){
                key = actionObject.getClass().getSimpleName().substring(0,actionObject.getClass().getSimpleName().indexOf("$$")) + "." + method.getName();
            }else{

                key = actionObject.getClass().getSimpleName() + "." + method.getName();
            }
            String rule = null;
            try{
                rule = this.messageSource.getMessage(key,null,null);
            }catch (Exception e){}
            if(rule != null){

            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
