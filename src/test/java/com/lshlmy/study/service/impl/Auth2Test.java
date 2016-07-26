package com.lshlmy.study.service.impl;

import com.lshlmy.study.common.SpringTest;
import com.lshlmy.study.common.config.oauth2.SparklrUserApprovalHandler;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.provider.ClientDetailsService;

import static javafx.scene.input.KeyCode.R;

/**
 * Created by lshlmy on 2016/7/23.
 */
public class Auth2Test extends SpringTest{



    @Test
    public void test() throws Exception{
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("ds.xml"));
    }
}
