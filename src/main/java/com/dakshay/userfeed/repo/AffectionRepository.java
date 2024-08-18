package com.dakshay.userfeed.repo;

import com.dakshay.userfeed.models.Affection;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AffectionRepository extends JpaRepository<Affection, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Affection a set a.likes = a.likes + 1 WHERE a.id = :id")
    public int incrementLikes(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Affection a set a.dislikes = a.dislikes + 1 WHERE a.id = :id")
    public int incrementDislikes(@Param("id") Long id);
}
