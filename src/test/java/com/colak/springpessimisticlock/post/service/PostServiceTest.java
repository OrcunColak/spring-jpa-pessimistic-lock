package com.colak.springpessimisticlock.post.service;

import com.colak.springpessimisticlock.post.jpa.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService service;

    @Test
    void getAndLockPosts() {
        Assertions.assertEquals(2,
                service.getAndLockPosts(Post.PostStatus.PENDING, 2).size()
        );
    }
}