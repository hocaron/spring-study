package com.springstudy.httplogging.common.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springstudy.httplogging.common.wrapper.CachingRequestWrapper;
import com.springstudy.httplogging.common.wrapper.CachingResponseWrapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Slf4j
@Component
public class HttpLogInterceptor extends HandlerInterceptorAdapter {

    private final ObjectMapper objectMapper;

    public HttpLogInterceptor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {

        if (request instanceof CachingRequestWrapper) {
            String req = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
            log.info("request - {} - {}", "[" + request.getMethod() + "]" + request.getRequestURI(),
                req);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
        Object handler, Exception ex)
        throws Exception {

        if (response instanceof CachingResponseWrapper) {
            String res = IOUtils.toString(
                ((CachingResponseWrapper) response).getContentInputStream(),
                response.getCharacterEncoding());
            log.info("response - {}", res);
        }
    }
}
