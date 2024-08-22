package com.dakshay.userfeed.controller;


import com.dakshay.userfeed.dto.AffectionRequestDTO;
import com.dakshay.userfeed.enums.AffectionContextType;
import com.dakshay.userfeed.enums.AffectionType;
import com.dakshay.userfeed.models.User;
import com.dakshay.userfeed.services.AffectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/affections")
@RequiredArgsConstructor
public class AffectionController {

    private final AffectionService affectionService;


    @PostMapping("/like")
    public ResponseEntity<Boolean> updateDisLike(@RequestBody AffectionRequestDTO affectionRequestDTO){
        affectionService.addLike(affectionRequestDTO);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PostMapping("/dislike")
    public ResponseEntity<Boolean> updateDislike(@RequestBody AffectionRequestDTO affectionRequestDTO){
        affectionService.addDislike(affectionRequestDTO);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @GetMapping("")
    public ResponseEntity<List<User>> list(
            @RequestParam Long contextId,
            @RequestParam AffectionContextType contextType,
            @RequestParam AffectionType affectionType

    ){
        return ResponseEntity.ok(affectionService.listLikesAndDislikes(contextId, contextType, affectionType));
    }
}
