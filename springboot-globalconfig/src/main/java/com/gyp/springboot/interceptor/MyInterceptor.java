package com.gyp.springboot.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Author: guoyapeng
 * @TODO: 默认拦截器
 * @Date: Created in 10:00 - 2020/9/1
 */
public class MyInterceptor implements HandlerInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(MyInterceptor.class);
    private final long   DELAYTIME = 1000L;

    /**
     * preHandler
     * 调用前提: Controller方法执行之前
     * 若返回false,则中断执行，注: 不会进入afterCompletion
     * @param request  请求
     * @param response 响应
     * @param handler  前端控制器接到的请求方法 Controller
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("------------------");
        System.out.println("----------");
        String ip = request.getRemoteAddr();
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime",startTime);
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        logger.info("用户:"+ip+",访问目标:"+method.getDeclaringClass().getName() + "." + method.getName());


        return true;
    }

    /**
     * postHandle
     * 调用前提：preHandle返回true
     * 调用时间：Controller方法处理完之后，DispatcherServlet进行视图的渲染之前，也就是说在这个方法中你可以对ModelAndView进行操作
     * @param request 请求
     * @param response 响应
     * @param handler 前端控制器接到的请求方法 Controller
     * @param modelAndView 前端页面
     * @throws Exception 异常
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
       HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        long startTime = (Long)request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime ;
        if ( executeTime > DELAYTIME){
            logger.info("[" + method.getDeclaringClass().getName() + "." + method.getName() + "] 执行耗时 : "
                    + executeTime + "ms");
        }else {
            logger.info("[" + method.getDeclaringClass().getName() + "." + method.getName() + "] 执行耗时 : "
                    + executeTime + "ms");
        }


    }

    /**
     * 调用前提：preHandle 返回true
     * 调用时间：DispatcherServlet 进行视图的渲染之后
     * 用于清理资源
     * @param request 请求
     * @param response 响应
     * @param handler 前端控制器接到的请求方法 Controller
     * @param ex 异常
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
