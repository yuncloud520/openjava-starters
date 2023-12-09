package cn.openjava.basic.format.utils;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {
    /**
     * 获取HttpServletRequest
     *
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes currentContext = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (currentContext == null) {
            return null;
        }
        HttpServletRequest request = currentContext.getRequest();
        return request;
    }

    /**
     * 获取HttpServletResponse
     *
     * @return
     */
    public static HttpServletResponse getHttpServletResponse() {
        ServletRequestAttributes currentContext = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (currentContext == null) {
            return null;
        }
        HttpServletResponse response = currentContext.getResponse();
        return response;
    }

    /**
     * 请求url
     *
     * @return {@link String}
     */
    public static String getRequestUrl() {
        return getHttpServletRequest().getRequestURI();
    }


    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

    public static String getUserAgent() {
        return getUserAgent(getHttpServletRequest());
    }

    /**
     * 获取浏览器名字
     *
     * @param request 请求
     * @return {@link String}
     */
    public static String getBrowserName(HttpServletRequest request) {
        String uaStr = request.getHeader("User-Agent");
        UserAgent ua = UserAgentUtil.parse(uaStr);
        return ua.getBrowser().toString();
    }

    public static String getBrowserName() {
        return getBrowserName(getHttpServletRequest());
    }

    /**
     * 获取浏览器版本
     *
     * @param request 请求
     * @return {@link String}
     */
    public static String getBrowserVersion(HttpServletRequest request) {
        String uaStr = request.getHeader("User-Agent");
        UserAgent ua = UserAgentUtil.parse(uaStr);
        return ua.getVersion();
    }

    public static String getBrowserVersion() {
        return getBrowserVersion(getHttpServletRequest());
    }

    /**
     * 获取操作系统名称
     *
     * @param request 请求
     * @return {@link String}
     */
    public static String getOsName(HttpServletRequest request) {
        String uaStr = request.getHeader("User-Agent");
        UserAgent ua = UserAgentUtil.parse(uaStr);
        return ua.getOs().toString();
    }

    public static String getOsName() {
        return getOsName(getHttpServletRequest());
    }

}
