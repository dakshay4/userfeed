package com.dakshay.userfeed.services;

import com.dakshay.userfeed.dto.CreateUserDTO;
import com.dakshay.userfeed.exceptions.CustomException;
import com.dakshay.userfeed.exceptions.UserFeedErrors;
import com.dakshay.userfeed.models.User;
import com.dakshay.userfeed.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepo;

    public User createUser(CreateUserDTO createUserDTO) {
        User user = new User(createUserDTO.getName());
        return userRepo.save(user);
    }

    public User findById(Long userId) {
        Optional<User> userOptional = userRepo.findById(userId);
        if(userOptional.isEmpty()) throw new CustomException(UserFeedErrors.USER_NOT_FOUND);
        return userOptional.get();
    }
}
