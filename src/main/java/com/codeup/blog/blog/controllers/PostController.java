package com.codeup.blog.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @GetMapping("/post")
    @ResponseBody
    public String postAll(){
        return "posts index page";
    }

    @GetMapping("/post/{id}")
    @ResponseBody
    public String postindividual(@PathVariable long id){
        return "view an individual post";
    }


    @GetMapping("/post/create")
    @ResponseBody
    public String view(){
        return "view the form for creating a post";
    }

    @PostMapping("/post/create")
    @ResponseBody
    public String create(@RequestParam long id){
        return "create a new post";
    }


}

