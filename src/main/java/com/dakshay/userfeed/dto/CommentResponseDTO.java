package com.dakshay.userfeed.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDTO {

    private Long id;
    private String statement;
    private AffectionCountDTO affectionCount;
    private UserResponseDTO user;
    private PostResponseDTO post;
    private CommentResponseDTO parentComment;
}
