package com.ecom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";     // receive index.html
    }

    @GetMapping("/login")
    public String login() {
        return "login";     // login.html
    }

    @GetMapping("/register")
    public String register() {
        return "register";     // register.html
    }

    @GetMapping("/products")
    public String products() {
        return "product";     // product.html
    }

    @GetMapping("/product")
    public String product() {
        return "view_product";     // product.html
    }


}
