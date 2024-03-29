package com.codeup.blog.blog.repositories;

import com.codeup.blog.blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {



    @Transactional
    @Modifying
    @Query("update Post p set p.title = ?1, p.body = ?2 where p.id = ?3")
    void editPost(String title, String body, long id);

}
