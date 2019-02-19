package com.ali.leb.sso.core.service.impl;

import com.ali.leb.sso.core.service.SsoService;
import com.ali.leb.sso.core.service.SsoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class SsoServiceImplTest {


    @Test
    public void testDoLogin(){
        SsoService ssoService = new SsoServiceImpl();

    }

}