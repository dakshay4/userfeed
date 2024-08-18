package com.dakshay.userfeed.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "posts" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String statement;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "affection_id", unique = true)
    private Affection affection;

    public Post(String statement, User user, Affection affection) {
        this.statement = statement;
        this.user = user;
        this.affection = affection;
    }

    @PrePersist
    public void prePersist() {
        if (this.affection != null && this.affection.getId() == null) {
            this.affection.setLikes(0);
            this.affection.setDislikes(0);
        }
    }
}
