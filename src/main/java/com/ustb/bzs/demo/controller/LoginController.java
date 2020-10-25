package com.ustb.bzs.demo.controller;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.invoke.empty.Empty;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

   // @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @PostMapping(value = "/user/login")
    //@ResponseBody
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        Map<String,Object> map,
                        HttpSession session){
        if(userName.equals("admin") && password.equals("123456")){

            session.setAttribute("userName",userName);
            //return "123456";
            map.put("companyName",userName);
            return "redirect:/main.html";
        }else {

            map.put("msg","用户名密码错误");
            return "index";
        }

    }
}
