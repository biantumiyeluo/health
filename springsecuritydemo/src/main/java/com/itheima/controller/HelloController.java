package com.itheima.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @PreAuthorize("hasAnyRole('add','ROLE_ADMIN')")
    @RequestMapping("/add")
    public String add() {
        System.out.println("add");
        return "success";
    }
}
