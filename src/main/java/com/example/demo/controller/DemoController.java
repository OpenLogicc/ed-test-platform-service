package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
public  class DemoController {

    @GetMapping("/")
    public String getData(){
        return "Hello World";
    }

    @PostMapping("/name")
    public String postData(@RequestBody String name){
        return "Hello " + name;
    }
}