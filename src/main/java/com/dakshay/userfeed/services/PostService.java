package com.dakshay.userfeed.services;


import com.dakshay.userfeed.dto.CreatePostDTO;
import com.dakshay.userfeed.exceptions.CustomException;
import com.dakshay.userfeed.exceptions.UserFeedErrors;
import com.dakshay.userfeed.models.Affection;
import com.dakshay.userfeed.models.Post;
import com.dakshay.userfeed.models.User;
import com.dakshay.userfeed.repo.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;


    public Post createPost(CreatePostDTO createPostDTO){
        String statement = createPostDTO.getStatement();
        Long userId = createPostDTO.getUserId();
        User user = userService.findById(userId);
        return postRepository.save(new Post(statement, user, new Affection(0,0)));
    }


    public Post findById(Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if(postOptional.isEmpty()) throw new CustomException(UserFeedErrors.POST_NOT_FOUND);
        return postOptional.get();
    }

    public Stream<Post> list(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        return postRepository.findAll(pageable).get();
    }
}
