package com.codeup.blog.blog.controllers;

import com.codeup.blog.blog.models.Post;
import com.codeup.blog.blog.models.User;
import com.codeup.blog.blog.repositories.PostRepository;
import com.codeup.blog.blog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {

        private PostRepository postDao;
        private UserRepository userDao;


    public PostController(PostRepository postDao, UserRepository userDao ){
    this.postDao = postDao;
    this.userDao = userDao;
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
    public String view(Model vModel) {
        vModel.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String create(@ModelAttribute Post post){
        post.setUser(userDao.getOne(1L));
        Post savedPost = postDao.save(post);

        return "redirect:/posts/" +  savedPost.getId();
    }


}

