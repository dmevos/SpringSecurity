package ru.osipov.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController{

    @GetMapping("all")
    public String getAll() {
        return "Hello everyone!";
    }

    @GetMapping("onlyAuth")
    public String getOnlyAuth() {
        return "only Auth!";
    }

    @GetMapping("onlyRead")
    public String getOnlyRead() {

        return "only Read!";
    }
    @GetMapping("onlyWrite")
    public String getOnlyWrite() {

        return "only Write!";
    }
}