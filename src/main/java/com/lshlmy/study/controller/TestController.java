package com.lshlmy.study.controller;

import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lshlmy on 2016/6/14.
 */
@Controller
@RequestMapping("/")
public class TestController {

    @RequestMapping("getJson")
    @ResponseBody
    public Object getJosn(HttpServletRequest request){
        request.setAttribute("data","dasd");
        return "梁湘明111";
    }
}
