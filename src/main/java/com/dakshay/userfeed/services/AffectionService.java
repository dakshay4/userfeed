package com.dakshay.userfeed.services;

import com.dakshay.userfeed.repo.AffectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AffectionService {

    private final AffectionRepository affectionRepository;


    public int incrementLike(Long id) {
         return affectionRepository.incrementLikes(id);
    }

    public int incrementDislikes(Long id) {
        return affectionRepository.incrementDislikes(id);

    }



}
