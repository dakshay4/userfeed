package com.dakshay.userfeed.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "posts" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post extends BaseBean{


    private String statement;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
    private Long createdAt;
    private Long modifiedAt;


    public Post(String statement, User user) {
        this.statement = statement;
        this.user = user;
    }

    @PrePersist
    public void prePersist() {
        if (this.getId() == null) {
            this.createdAt = System.currentTimeMillis();
        }
        this.modifiedAt = System.currentTimeMillis();
    }
}
