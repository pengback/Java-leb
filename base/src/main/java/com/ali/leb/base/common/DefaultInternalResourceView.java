package com.ali.leb.base.common;

import org.springframework.web.servlet.view.JstlView;

import java.util.Locale;

public class DefaultInternalResourceView extends JstlView {

    public boolean checkResource(Locale locale) throws Exception {
        this.logger.info("-------------------------" + this.getServletContext().getRealPath("/") + getUrl());
        System.out.println("-----------------------------------------");
        return true;
    }
}