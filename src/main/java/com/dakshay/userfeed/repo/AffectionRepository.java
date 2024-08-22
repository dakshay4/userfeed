package com.dakshay.userfeed.repo;

import com.dakshay.userfeed.enums.AffectionContextType;
import com.dakshay.userfeed.enums.AffectionType;
import com.dakshay.userfeed.models.Affection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AffectionRepository extends JpaRepository<Affection, Long> {


    @Query("Select a from Affection a where a.contextId=:contextId and a.contextType=:contextType and a.deleted=false")
    List<Affection> findByContextIdAndContextType(Long contextId, AffectionContextType contextType);

    @Query("Select a from Affection a where a.contextId=:contextId and a.contextType=:contextType " +
            "and a.affectionType=:affectionType and a.deleted=false")
    List<Affection> findByContextIdAndContextTypeAndAffectionType(Long contextId, AffectionContextType contextType , AffectionType affectionType);

    List<Affection> findByContextIdAndContextTypeAndUserId(Long contextId, AffectionContextType contextType, Long userId);

    /*@Modifying
    @Transactional
    @Query("UPDATE Affection a set a.likes = a.likes + 1 WHERE a.id = :id")
    public int incrementLikes(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Affection a set a.dislikes = a.dislikes + 1 WHERE a.id = :id")
    public int incrementDislikes(@Param("id") Long id);*/
}
