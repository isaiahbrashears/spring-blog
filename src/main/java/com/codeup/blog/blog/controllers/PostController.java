package com.codeup.blog.blog.controllers;

import com.codeup.blog.blog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {
        ArrayList<Post> posts;
        public PostController(){
            posts = new ArrayList<>();
            posts.add( new Post(1,"My First Post", "This is so exciting!"));
            posts.add( new Post(2,"My Second Post", "I'm kinda over it"));
        }

    @GetMapping("/post")
    public String showIndex(Model vModel){

        vModel.addAttribute("posts", posts);

        return "/posts/index";

    }

    @GetMapping("/post/{id}")
    public String showIndividual(@PathVariable long id, Model vModel){

        vModel.addAttribute("post", posts.get((int)id - 1));


        return "/posts/show";
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

