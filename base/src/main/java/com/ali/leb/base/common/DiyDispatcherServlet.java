package com.ali.leb.base.common;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("serial")
public class DiyDispatcherServlet extends DispatcherServlet implements Filter {

    public DiyDispatcherServlet() {
        super();
    }

    public DiyDispatcherServlet(WebApplicationContext webApplicationContext) {
        super(webApplicationContext);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        super.init(new DiyServletFilter(filterConfig));
//        super.init();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;

        this.logger.info("-------------------------"+servletRequest.getContextPath() + servletRequest.getRequestURI());
        chain.doFilter(servletRequest, servletResponse);

    }
}
