package com.dakshay.userfeed.services;

import com.dakshay.userfeed.dto.CreateUserDTO;
import com.dakshay.userfeed.models.User;
import com.dakshay.userfeed.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepo;

    public User createUser(CreateUserDTO createUserDTO) {
        User user = new User(createUserDTO.getName());
        return userRepo.save(user);
    }

    public User findById(Long userId) {
        User user = RedisService.getUser(userId);
        if(user ==null) {
            Optional<User> o = userRepo.findById(userId);
            if(o.isPresent()) user = o.get();
        }
        RedisService.saveUserToRedis(user);
        return user;
    }

    public Stream<User> list(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        if(page==null || size ==null)
            return userRepo.findAll(sort).stream();
        return userRepo.findAll(PageRequest.of(page, size, sort)).get();
    }
}
