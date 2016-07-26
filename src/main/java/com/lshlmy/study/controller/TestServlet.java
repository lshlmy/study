package com.lshlmy.study.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lshlmy on 2016/6/15.
 */
public class TestServlet extends HttpServlet{

    public void init() throws ServletException{

    }

    public void doGet(HttpServletRequest request , HttpServletResponse response) throws  ServletException,IOException{
        request.setAttribute("data","aaaa");
    }

    public void doPost(HttpServletRequest request , HttpServletResponse response) throws  ServletException,IOException{
        request.setAttribute("data","aaaa");
    }

    public void destory(){

    }
}
