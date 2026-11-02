package com.week6.week6.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Its me, A.E.T AKA Mr Sensational and I'm doing syper fantastic";
    }
}