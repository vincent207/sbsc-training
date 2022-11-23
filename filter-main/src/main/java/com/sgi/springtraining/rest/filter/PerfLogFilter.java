package com.sgi.springtraining.rest.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class PerfLogFilter extends GenericFilterBean {
    Logger logger = LoggerFactory.getLogger(PerfLogFilter.class);

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain
            filterChain)throws IOException, ServletException {
        long time = System.currentTimeMillis();
        try {
            filterChain.doFilter(req, res);
        } finally {
            time = System.currentTimeMillis() - time;
            logger.info("Request was processed in: {}: {} ms ", ((HttpServletRequest) req).getRequestURI(),  time);
        }
    }
}
