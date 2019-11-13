package com.codeup.blog.blog.controllers;

import com.codeup.blog.blog.models.Post;
import com.codeup.blog.blog.models.User;
import com.codeup.blog.blog.repositories.PostRepository;
import com.codeup.blog.blog.repositories.UserRepository;
import com.codeup.blog.blog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {

        private PostRepository postDao;
        private UserRepository userDao;
        private EmailService emailService;


    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService ){
    this.postDao = postDao;
    this.userDao = userDao;
    this.emailService = emailService;
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
    public String beginEdit(@PathVariable long id, Model vModel, @ModelAttribute String missing){

        vModel.addAttribute("post", postDao.getOne(id));

        return "/posts/edit" ;
    }

    @PostMapping ("/posts/{id}/edit")
    public String editPost(@PathVariable long id, @RequestParam(name = "title") String title, @RequestParam(name = "body") String body, Model vModel){

        if (title.isEmpty() || body.isEmpty()) {
            return "redirect:/posts/" +id + "/edit";
        }else {
            postDao.editPost(title, body, id);
            return "redirect:/posts/" + id;
        }



    }


    @GetMapping("/posts/create")
    public String view(Model vModel, @ModelAttribute String missing) {
        vModel.addAttribute("post", new Post());

        if (missing != null){
            vModel.addAttribute("missing", missing);
        }


        return "posts/create";
    }



    @PostMapping("/posts/create")
    public String create(@ModelAttribute Post post, Model vModel){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (post.getTitle().isEmpty() || post.getBody().isEmpty()){
            vModel.addAttribute("missing", "Please fill out all forms");
            return "/posts/create";
        }else{
            post.setUser(userDao.getOne(user.getId()));
            Post savedPost = postDao.save(post);
            emailService.prepareAndSend(savedPost, "New Post", "Congrats, your post has been created.");
            return "redirect:/posts/" +  savedPost.getId();
        }


    }


}

