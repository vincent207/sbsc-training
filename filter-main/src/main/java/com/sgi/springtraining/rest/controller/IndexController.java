package com.sgi.springtraining.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    @GetMapping("/")
    @ResponseBody
    String index() {
        return "API Home";
    }
}
