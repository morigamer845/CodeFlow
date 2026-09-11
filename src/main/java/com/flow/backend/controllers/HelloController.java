package com.flow.backend.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@RestController
class HelloController {
    @RequestMapping("/hello")
    public Map<String, Object> getHello(){
        Map<String, Object> response = new HashMap<>();

        response.put("hello", "hello");

        return response;
    }
}
