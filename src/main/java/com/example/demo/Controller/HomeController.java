package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class HomeController {
    
    @GetMapping("/streaming/")
    public String home() {
        return "api anda";
    }
    
}
