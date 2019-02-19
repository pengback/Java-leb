package com.ali.leb.base.common;

import javax.servlet.FilterConfig;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.util.Enumeration;

public class DiyServletFilter implements ServletConfig, FilterConfig {

    private FilterConfig filterConfig;

    public DiyServletFilter(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public String getFilterName() {
        return filterConfig.getFilterName();
    }

    @Override
    public String getServletName() {
        return filterConfig.getFilterName();
    }

    @Override
    public ServletContext getServletContext() {
        return filterConfig.getServletContext();
    }

    @Override
    public String getInitParameter(String name) {
        return filterConfig.getInitParameter(name);
    }

    @Override
    public Enumeration getInitParameterNames() {
        return filterConfig.getInitParameterNames();
    }
}
