package com.sgi.springtraining.methodsecurity.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

@PreAuthorize("isAuthenticated()")
@Controller
public class WebController {
    @Autowired
    private HttpServletRequest context;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {

        return "Hello "+context.getRemoteUser().toString() +"! <br/> " +
                "<a href=\"/restricted\">To Restricted</a> <br/>" +
                "<a href=\"/logout\">Logout</a>";
    }

    @RequestMapping("/restricted")
    @ResponseBody
    public String restricted() {
        return "You found the secret lair! <br/> " +
                "<a href=\"/hello\">To Hello</a> <br/>" +
                "<a href=\"/logout\">Logout</a>";
    }
}