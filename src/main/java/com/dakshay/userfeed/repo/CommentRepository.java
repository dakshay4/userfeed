package com.dakshay.userfeed.repo;

import com.dakshay.userfeed.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    @Query("SELECT c FROM Comment c JOIN FETCH c.user JOIN FETCH c.post" +
            " where c.post.id=:postId and c.parentComment IS NULL")
    Page<Comment> findAllByPostIdAndParentCommentIdIsNull(Long postId, Pageable pageable);

    Page<Comment> findAllByParentCommentId(Long commentId, Pageable pageable);

}
