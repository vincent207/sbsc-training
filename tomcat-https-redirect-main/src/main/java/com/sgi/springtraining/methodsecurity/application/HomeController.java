package com.sgi.springtraining.methodsecurity.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Welcome home! <br/> " +
                "<a href=\"/restricted\">Try Restricted Page</a> <br/>" +
                "<a href=\"/hello\">Login</a>";
    }
}