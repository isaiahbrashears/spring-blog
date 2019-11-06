package com.codeup.blog.blog.controllers;

import com.codeup.blog.blog.models.Post;
import com.codeup.blog.blog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {
        ArrayList<Post> posts;

        private PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String showIndex(Model vModel){

        vModel.addAttribute("posts", postDao.findAll());

        return "/posts/index";
    }


    @GetMapping("/posts/{id}")
    public String showIndividual(@PathVariable long id, Model vModel){

        vModel.addAttribute("post", postDao.getOne(id));


        return "/posts/show";
    }


    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id){

        postDao.deleteById(id);

        return "redirect:/posts";
    }


    @GetMapping("/posts/{id}/edit")
    public String beginEdit(@PathVariable long id, Model vModel){

        vModel.addAttribute("post", postDao.getOne(id));

        return "/posts/edit" ;
    }

    @PostMapping ("/posts/{id}/edit")
    public String editPost(@PathVariable long id, @RequestParam(name = "title") String title, @RequestParam(name = "body") String body){

            postDao.editPost(title, body, id);


        return "redirect:/posts/" + id;
    }


    @GetMapping("/posts/create")
    @ResponseBody
    public String view(){
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String create(@RequestParam long id){
        return "create a new post";
    }


}

