package org.example.controller;

import org.example.pojo.Admin;
import org.example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AuthController {

    @Autowired
    AuthService authService;

    // authentication and redirection
    @RequestMapping("/login.action")
    public String login(String name, String pwd, HttpServletRequest httpServletRequest){

        Admin admin = authService.authentication(name,pwd);
        if (admin != null){
            httpServletRequest.setAttribute("admin",admin);
            // go to localhost:8080/admin/main.jsp via view resolver configured in springmvc.xml
            return "main";
        } else {
            // go to localhost:8080/admin/login.jsp via view resolver configured in springmvc.xml
            httpServletRequest.setAttribute("errmsg","username or password is incorrect");
            return "login";
        }
    }

    @RequestMapping("/register.action")
    public String register(String name, String pwd,HttpServletRequest httpServletRequest){
        String msg = null;

        try {
            msg = authService.register(name,pwd);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (msg == null){
                msg = "Please change your name. The same user exists!";
                httpServletRequest.setAttribute("registerMsg", msg);

                return "register";
            }
        }

        httpServletRequest.setAttribute("registerMsg", msg);

        return "login";
    }
}
