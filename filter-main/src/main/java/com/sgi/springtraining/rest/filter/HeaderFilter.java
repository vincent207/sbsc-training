package com.sgi.springtraining.rest.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import java.io.IOException;

public class HeaderFilter extends GenericFilterBean {
    Logger logger = LoggerFactory.getLogger(PerfLogFilter.class);

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain
            filterChain)throws IOException, ServletException {
        var request = (HttpServletRequest) req;
        var response = (HttpServletResponse) res;

        //if header is missing , send un-athorized error back
        String authHeader = request.getHeader("X-Custom-Header");
        if (StringUtils.isEmpty(authHeader)) {
            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            filterChain.doFilter(req, res);
        }
        logger.info("Header filtered");
    }
}