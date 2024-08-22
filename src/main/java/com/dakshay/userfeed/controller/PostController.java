package com.dakshay.userfeed.controller;


import com.dakshay.userfeed.dto.CreatePostDTO;
import com.dakshay.userfeed.dto.PostResponseDTO;
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
    public ResponseEntity<PostResponseDTO> create(@RequestBody CreatePostDTO createPostDTO) {
        PostResponseDTO post = postService.createPost(createPostDTO);
        return ResponseEntity.ok(post);
    }

    @GetMapping("")
    public ResponseEntity<Stream<PostResponseDTO>> list(@RequestParam int page, @RequestParam int size ) {
        return ResponseEntity.ok(postService.list(page,size));
    }


}
