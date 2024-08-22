package com.dakshay.userfeed.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDTO {

    private Long id;
    private String statement;
    private UserResponseDTO user;
    private AffectionCountDTO affectionCount;
    private Long createdAt;
    private Long modifiedAt;

}
