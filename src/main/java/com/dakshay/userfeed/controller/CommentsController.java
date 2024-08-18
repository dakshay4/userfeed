package com.dakshay.userfeed.controller;


import com.dakshay.userfeed.dto.CreateCommentDTO;
import com.dakshay.userfeed.models.Comment;
import com.dakshay.userfeed.services.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentsController {

    private final CommentsService commentsService;

    @PostMapping("/create")
    public ResponseEntity<Comment> create(@RequestBody CreateCommentDTO createCommentDTO) {
        return ResponseEntity.ok(commentsService.create(createCommentDTO));
    }


    @GetMapping("/{postId}")
    public ResponseEntity<Stream<Comment>> list(@PathVariable Long postId, @RequestParam(required = false) int page, @RequestParam int size ) {
        return ResponseEntity.ok(commentsService.list(postId, page,size));
    }


    @GetMapping("/replies/{commentId}")
    public ResponseEntity<Stream<Comment>> replies(@PathVariable Long commentId, @RequestParam(required = false) int page, @RequestParam int size ) {
        return ResponseEntity.ok(commentsService.replies(commentId, page,size));
    }
}
