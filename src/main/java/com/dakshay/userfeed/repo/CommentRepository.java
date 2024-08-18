package com.dakshay.userfeed.repo;

import com.dakshay.userfeed.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    Page<Comment> findAllByPostIdAndParentCommentIdIsNull(Long postId, Pageable pageable);

    Page<Comment> findAllByParentCommentId(Long commentId, Pageable pageable);

}
