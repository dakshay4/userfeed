package com.dakshay.userfeed.controller;


import com.dakshay.userfeed.dto.CreatePostDTO;
import com.dakshay.userfeed.models.Post;
import com.dakshay.userfeed.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping("")
    public ResponseEntity<Post> create(@RequestBody CreatePostDTO createPostDTO) {
        Post post = postService.createPost(createPostDTO);
        return ResponseEntity.ok(post);
    }

    @GetMapping("")
    public ResponseEntity<Stream<Post>> list(@RequestParam int page, @RequestParam int size ) {
        return ResponseEntity.ok(postService.list(page,size));
    }


}
