package com.colak.springtutorial.post.service;

import com.colak.springtutorial.post.jpa.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.hibernate.LockOptions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final EntityManager entityManager;

    // select * from post p where p.status=? order by p.id
    // fetch first ? rows ONLY FOR NO KEY UPDATE SKIP LOCKED
    @Transactional
    public List<Post> getAndLockPosts(Post.PostStatus status, int postCount) {
        return entityManager.createQuery(
                        "select p " +
                        "from Post p " +
                        "where p.status = :status " +
                        "order by p.id", Post.class)
                .setParameter("status", status)
                .setMaxResults(postCount)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .setHint(
                        "jakarta.persistence.lock.timeout",
                        LockOptions.SKIP_LOCKED
                )
                .getResultList();
    }
}
