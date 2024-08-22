package com.dakshay.userfeed.services;

import com.dakshay.userfeed.dto.CommentResponseDTO;
import com.dakshay.userfeed.dto.CreateCommentDTO;
import com.dakshay.userfeed.enums.AffectionContextType;
import com.dakshay.userfeed.exceptions.CustomException;
import com.dakshay.userfeed.exceptions.UserFeedErrors;
import com.dakshay.userfeed.models.Comment;
import com.dakshay.userfeed.repo.CommentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final AffectionService affectionService;
    private final PostService postService;
    private final UserService userService;
    private final ObjectMapper objectMapper;

    public Comment create(CreateCommentDTO createCommentDTO) {
        Comment comment =  new Comment(
                createCommentDTO.getStatement(),
                userService.findById(createCommentDTO.getUserId()),
                postService.findById(createCommentDTO.getPostId()),
                createCommentDTO.getParentCommentId()!=null ? findById(createCommentDTO.getParentCommentId()) : null
                );
        return commentRepository.save(comment);
    }


    public Comment findById(Long commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if(commentOptional.isEmpty()) throw new CustomException(UserFeedErrors.COMMENT_NOT_FOUND);
        return commentOptional.get();
    }

    public Stream<CommentResponseDTO> list(Long postId, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Stream<Comment> comments = commentRepository.findAllByPostIdAndParentCommentIdIsNull(postId, pageable).get();
        return comments.map(this::convertToResponse);
    }

    private CommentResponseDTO convertToResponse(Comment comment) {
        CommentResponseDTO response = objectMapper.convertValue(comment, CommentResponseDTO.class);
        response.setAffectionCount(affectionService.findAffectionsOfAContext(comment.getId(), AffectionContextType.COMMENT));
        return response;
    }

    public Stream<CommentResponseDTO> replies(Long commentId, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Stream<Comment> comments = commentRepository.findAllByParentCommentId(commentId, pageable).get();
        return comments.map(this::convertToResponse);

    }
}
