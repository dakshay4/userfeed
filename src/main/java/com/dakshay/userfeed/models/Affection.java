package com.dakshay.userfeed.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "affections")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Affection {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private int likes;
    private int dislikes;

    public Affection(int likes, int dislikes) {
        this.likes = likes;
        this.dislikes = dislikes;
    }
}
