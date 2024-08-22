package com.dakshay.userfeed.controller;


import com.dakshay.userfeed.dto.CreateUserDTO;
import com.dakshay.userfeed.models.User;
import com.dakshay.userfeed.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<User> create(@RequestBody CreateUserDTO createUserDTO) {
        return ResponseEntity.ok(userService.createUser(createUserDTO));
    }

    @GetMapping("")
    public ResponseEntity<Stream<User>> list(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size ) {
        return ResponseEntity.ok(userService.list(page,size));
    }
}
