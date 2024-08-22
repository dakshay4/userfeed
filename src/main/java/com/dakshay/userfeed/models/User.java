package com.dakshay.userfeed.models;

import com.dakshay.userfeed.services.RedisService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public User(String name) {
        this.name = name;
    }

    @PostPersist
    public void postPersist() {
        RedisService.saveUserToRedis(this);
    }
}
