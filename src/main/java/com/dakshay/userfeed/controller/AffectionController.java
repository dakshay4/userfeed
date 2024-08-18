package com.dakshay.userfeed.controller;


import com.dakshay.userfeed.services.AffectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/affections")
@RequiredArgsConstructor
public class AffectionController {

    private final AffectionService affectionService;

    @PostMapping("/like/{id}")
    public ResponseEntity<Integer> updateLike(@PathVariable Long id){
        return ResponseEntity.ok(affectionService.incrementLike(id));
    }

    @PostMapping("/dislike/{id}")
    public ResponseEntity<Integer> updateDislike(@PathVariable Long id){
        return ResponseEntity.ok(affectionService.incrementDislikes(id));
    }
}
