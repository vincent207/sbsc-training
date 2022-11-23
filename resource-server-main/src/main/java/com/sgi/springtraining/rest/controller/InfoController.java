package com.sgi.springtraining.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoController {
    Logger logger = LoggerFactory.getLogger(InfoController.class);

    @GetMapping("/")
    public String display()
    {
        logger.debug("Accessing documentation page");
        return "redirect:/doc/index.html";
    }
}
