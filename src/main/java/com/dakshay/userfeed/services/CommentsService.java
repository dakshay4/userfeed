package com.dakshay.userfeed.services;

import com.dakshay.userfeed.dto.CreateCommentDTO;
import com.dakshay.userfeed.exceptions.CustomException;
import com.dakshay.userfeed.exceptions.UserFeedErrors;
import com.dakshay.userfeed.models.Affection;
import com.dakshay.userfeed.models.Comment;
import com.dakshay.userfeed.repo.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CommentsService {


    private final CommentRepository commentRepository;
    private final PostService postService;
    private final UserService userService;

    public Comment create(CreateCommentDTO createCommentDTO) {
        Comment comment =  new Comment(
                createCommentDTO.getStatement(),
                userService.findById(createCommentDTO.getUserId()),
                postService.findById(createCommentDTO.getPostId()),
                createCommentDTO.getParentCommentId()!=null ? findById(createCommentDTO.getParentCommentId()) : null,
                new Affection(0,0)
                );
        return commentRepository.save(comment);
    }


    public Comment findById(Long commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if(commentOptional.isEmpty()) throw new CustomException(UserFeedErrors.COMMENT_NOT_FOUND);
        return commentOptional.get();
    }

    public Stream<Comment> list(Long postId, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        return commentRepository.findAllByPostIdAndParentCommentIdIsNull(postId, pageable).get();
    }

    public Stream<Comment> replies(Long commentId, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        return commentRepository.findAllByParentCommentId(commentId, pageable).get();

    }
}
