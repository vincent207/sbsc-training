package com.sgi.springtraining.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;
import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@Controller
public class HomeController {
    private final WebClient webClient;
    Logger logger = LoggerFactory.getLogger(HomeController.class);

    public HomeController(WebClient webClient){
        this.webClient = webClient;
    }

    @RequestMapping(value={"/","/index"})
    public String display()
    {
        logger.info("Accessing index page");
        return "index";
    }

    @RequestMapping(value={"/consent"})
    @ResponseBody
    public String setConsent(Model model,
                             @RegisteredOAuth2AuthorizedClient("sgi-client-authorization-code")
                             OAuth2AuthorizedClient authorizedClient) {
        logger.info("/authorize authorization_code hit ...");
        String[] messages = this.webClient
                .get()
                .uri("http://103.171.84.244:8090/api/books")
                .attributes(oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToMono(String[].class)
                .block();
        model.addAttribute("messages", messages);
        return messages.toString();
    }

    @RequestMapping(value={"/books"})
    @ResponseBody
    public String showBooks(Model model, @RequestHeader HttpHeaders headers)
    {
        logger.info("Accessing books page");

        //Map<String, String> attr = (Map<String, String>) clientRegistrationId("sgi-oidc-client-credentials");
        //logger.info(attr.toString());

        String books = this.webClient
                .get()
                .uri("http://103.171.84.244:8090/api/books")
                .attributes(clientRegistrationId("sgi-oidc-client-credentials"))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        model.addAttribute("books", books);

        logger.info("Books API get result: "+ books);
        return books;
    }

    @RequestMapping(value={"/log-me-out"})
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";  //Where you go after logout here.
    }
}
