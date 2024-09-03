package com.ecom.controller;

import com.ecom.model.UserDtls;
import com.ecom.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

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

    // save user after Registration
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute UserDtls user, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
        String imageName = file.isEmpty() ? "default.jpg" : file.getOriginalFilename();
        user.setProfileImage(imageName);
        UserDtls saveUser = userService.saveUser(user);

        // user is found
        if(!ObjectUtils.isEmpty(saveUser)){
            // store in database
            if(!file.isEmpty()) {
                File saveFile = new ClassPathResource("static/img").getFile();

                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "profile_img" + File.separator
                        + file.getOriginalFilename());

                System.out.println(path);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                session.setAttribute("successMsg", "Registered successfully!");
            }
        }   else {
            session.setAttribute("errorMsg", "oops! something went wrong!");

        }

        return "redirect:/register";
    }



}
