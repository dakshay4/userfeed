package com.dakshay.userfeed.repo;


import com.dakshay.userfeed.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    @Query("SELECT p FROM Post p JOIN FETCH p.user")
    Page<Post> findAllJoin(Pageable pageable);

}
