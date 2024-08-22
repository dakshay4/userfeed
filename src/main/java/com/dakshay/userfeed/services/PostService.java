package com.dakshay.userfeed.services;


import com.dakshay.userfeed.dto.CreatePostDTO;
import com.dakshay.userfeed.dto.PostResponseDTO;
import com.dakshay.userfeed.enums.AffectionContextType;
import com.dakshay.userfeed.exceptions.CustomException;
import com.dakshay.userfeed.exceptions.UserFeedErrors;
import com.dakshay.userfeed.models.Post;
import com.dakshay.userfeed.models.User;
import com.dakshay.userfeed.repo.PostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final AffectionService affectionService;
    private final UserService userService;
    private final ObjectMapper objectMapper;


    public PostResponseDTO createPost(CreatePostDTO createPostDTO){
        String statement = createPostDTO.getStatement();
        Long userId = createPostDTO.getUserId();
        User user = userService.findById(userId);
        Post post = postRepository.save(new Post(statement, user));
        return convertToResponse(post);
    }

    private PostResponseDTO convertToResponse(Post post) {
        PostResponseDTO response = objectMapper.convertValue(post, PostResponseDTO.class);
        response.setAffectionCount(affectionService.findAffectionsOfAContext(post.getId(), AffectionContextType.POST));
        return response;
    }


    public Post findById(Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if(postOptional.isEmpty()) throw new CustomException(UserFeedErrors.POST_NOT_FOUND);
        return postOptional.get();
    }

    public Stream<PostResponseDTO> list(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Stream<Post> posts =  postRepository.findAllJoin(pageable).get();
       return posts.map(this::convertToResponse);
    }
}
