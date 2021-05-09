package com.runnersoftware.auto_test.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {
    
    @RequestMapping("/user/login")
    public String Register(@RequestParam("username")String username,@RequestParam("password")String password,Model model,HttpSession session){

        if(!StringUtils.isEmpty(username)&&"123".equals(password))
        {
            session.setAttribute("loginUser",username);
            return "index","editor";
        }else{
            model.addAttribute("msg", "用户名或者密码错误");
            return "login";
        }
    }
}
