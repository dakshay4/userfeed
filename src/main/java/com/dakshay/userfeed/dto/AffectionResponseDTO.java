package com.dakshay.userfeed.dto;


import com.dakshay.userfeed.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class AffectionResponseDTO {

    private Long id;
    private User user;
    private int dislikes;

}
