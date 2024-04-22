package com.colak.springpessimisticlock.post.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "post")

@Getter
@Setter
public class Post {

    public enum PostStatus {
        PENDING,
        APPROVED,
        SPAM
    }

    @Id
    private Long id;

    private String title;

    private String body;

    @Enumerated(EnumType.STRING)
    private PostStatus status;
}
