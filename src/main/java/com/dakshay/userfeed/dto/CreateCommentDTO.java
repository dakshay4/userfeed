package com.dakshay.userfeed.dto;


import lombok.Data;

@Data
public class CreateCommentDTO {

    private String statement;
    private Long userId;
    private Long postId;
    private Long parentCommentId;
}
