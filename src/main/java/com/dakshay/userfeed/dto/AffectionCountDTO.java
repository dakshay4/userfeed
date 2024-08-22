package com.dakshay.userfeed.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AffectionCountDTO {

    private int likes;
    private int dislikes;


    public AffectionCountDTO(int likes, int dislikes) {
        this.likes = likes;
        this.dislikes = dislikes;
    }
}
