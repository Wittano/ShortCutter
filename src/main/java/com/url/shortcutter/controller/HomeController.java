package com.url.shortcutter.controller;

import com.url.shortcutter.model.Link;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("newLink", new Link());

        return "index";
    }
}
