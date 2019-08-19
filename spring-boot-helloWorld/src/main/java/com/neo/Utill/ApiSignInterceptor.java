package com.neo.Utill;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApiSignInterceptor implements HandlerInterceptor {


    private final static String SEPERATOR = "_";
    private final static String SECRET = "jwentest";
    private final static String NO_PERMISSION_ERROR_MESSAGE = "Api Token Error, You have no permission to access this api";


    // md5计算
    private String md5Hex(String data) {
        return data.toLowerCase();
    }

    private String getSign(String t) {
        return md5Hex(t + SEPERATOR + SECRET);
    }

    // sign计算，t为时间戳,sign为md5(t+"_"+"jwentest")

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try {
            String t = request.getParameter("t");
            String sign = request.getParameter("sign");

            if (t.isEmpty() || sign.isEmpty()) {
                response.sendError(403, NO_PERMISSION_ERROR_MESSAGE);
                return false;
            }

            String expectedSign = getSign(t);

            if (!expectedSign.equals(sign)) {
                response.sendError(403, NO_PERMISSION_ERROR_MESSAGE);
                return false;
            }

        } catch (Throwable t) {
            response.sendError(403, NO_PERMISSION_ERROR_MESSAGE);
            return false;
        }

        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}