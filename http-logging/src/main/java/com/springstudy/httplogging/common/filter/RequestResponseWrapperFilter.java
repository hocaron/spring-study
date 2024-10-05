package com.springstudy.httplogging.common.filter;

import com.springstudy.httplogging.common.wrapper.CachingRequestWrapper;
import com.springstudy.httplogging.common.wrapper.CachingResponseWrapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
public class RequestResponseWrapperFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain)
        throws ServletException, IOException {
        if (isAsyncDispatch(request)) {
            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(
                new CachingRequestWrapper(request),
                new CachingResponseWrapper(response)
            );
        }
    }
}

