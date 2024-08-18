package com.dakshay.userfeed.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String statement;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "affection_id", unique = true)
    private Affection affection;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    @ManyToOne
    private Comment parentComment;

    public Comment(String statement, User user, Post post, Comment parentComment, Affection affection) {
        this.statement = statement;
        this.user = user;
        this.post = post;
        this.parentComment = parentComment;
        this.affection = affection;
    }

}
