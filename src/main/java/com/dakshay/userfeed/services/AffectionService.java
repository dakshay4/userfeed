package com.dakshay.userfeed.services;

import com.dakshay.userfeed.aspects.AffectionPostProcessor;
import com.dakshay.userfeed.dto.AffectionCountDTO;
import com.dakshay.userfeed.dto.AffectionRequestDTO;
import com.dakshay.userfeed.enums.AffectionContextType;
import com.dakshay.userfeed.enums.AffectionType;
import com.dakshay.userfeed.exceptions.CustomException;
import com.dakshay.userfeed.exceptions.UserFeedErrors;
import com.dakshay.userfeed.models.Affection;
import com.dakshay.userfeed.models.User;
import com.dakshay.userfeed.repo.AffectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AffectionService {

    private final AffectionRepository affectionRepository;
    private final RedisLockService redisLockService;
    private final UserService userService;


    @Async("affection-processor")
    @AffectionPostProcessor
    public void addLike(AffectionRequestDTO affectionRequestDTO) {
        AffectionFactory affectionFactory = getAffectionFactory(affectionRequestDTO);
        Long contextId = affectionRequestDTO.getContextId();
        Long userId = affectionRequestDTO.getUserId();
        AffectionContextType contextType = affectionRequestDTO.getContextType();
        boolean lockAcquire = redisLockService.acquireLock(affectionRequestDTO.getCacheKey());
        if(lockAcquire) {
            List<Affection> affections = affectionRepository.findByContextIdAndContextTypeAndUserId(contextId, contextType, userId);
            if(affections.isEmpty()) {
                Affection affection = affectionFactory.createAffection(
                        contextId,
                        userId,
                        AffectionType.LIKE
                );
                affectionRepository.save(affection);
            } else {
                Affection affection = affections.get(0);
                if(AffectionType.DISLIKE.equals(affection.getAffectionType()) || affection.isDeleted()) {
                    affection.setAffectionType(AffectionType.LIKE);
                    affection.setDeleted(false);
                }
                else affection.setDeleted(true); // was LIKE earlier, and then LIKE made again, hence soft delete the entry
                affectionRepository.save(affection);
            }
        }
    }

    public AffectionCountDTO findAffectionsOfAContext(Long contextId, AffectionContextType contextType) {
        List<Affection> affections = affectionRepository.findByContextIdAndContextType(contextId, contextType);
        if(affections==null || affections.isEmpty()) return new AffectionCountDTO(0,0);
        Map<AffectionType, Long> affectionCounts = affections.stream()
                .collect(Collectors.groupingBy(Affection::getAffectionType, Collectors.counting()));
        Long likes = affectionCounts.get(AffectionType.LIKE);
        Long dislikes = affectionCounts.get(AffectionType.DISLIKE);
        return new AffectionCountDTO(
                likes!= null ? likes.intValue() : 0,
                dislikes!= null ? dislikes.intValue() : 0
        );
    }

    private static AffectionFactory getAffectionFactory(AffectionRequestDTO affectionRequestDTO) {
        if(affectionRequestDTO.getContextId() == null) throw new CustomException(UserFeedErrors.INVALID_REQUEST);
        return AffectionFactoryProvider.getFactory(affectionRequestDTO.getContextType());
    }

    @AffectionPostProcessor
    @Async("affection-processor")
    public void addDislike(AffectionRequestDTO affectionRequestDTO) {
        AffectionFactory affectionFactory = getAffectionFactory(affectionRequestDTO);
        Long contextId = affectionRequestDTO.getContextId();
        Long userId = affectionRequestDTO.getUserId();
        AffectionContextType contextType = affectionRequestDTO.getContextType();
        boolean lockAcquire = redisLockService.acquireLock(affectionRequestDTO.getCacheKey());
        if(lockAcquire) {
            List<Affection> affections = affectionRepository.findByContextIdAndContextTypeAndUserId(contextId, contextType, userId);
            if(affections.isEmpty()) {
                Affection affection = affectionFactory.createAffection(
                        contextId,
                        userId,
                        AffectionType.DISLIKE
                );
                affectionRepository.save(affection);
            } else {
                Affection affection = affections.get(0);
                if(AffectionType.LIKE.equals(affection.getAffectionType()) || affection.isDeleted()) {
                    affection.setAffectionType(AffectionType.DISLIKE);
                    affection.setDeleted(false);
                }
                else affection.setDeleted(true); // was DISLIKE earlier, and then DISLIKE made again, hence soft delete the entry
                affectionRepository.save(affection);
            }
        }
    }


    public List<User> listLikesAndDislikes(Long contextId,
                                                AffectionContextType contextType,
                                                AffectionType affectionType) {
        List<Affection> affections = affectionRepository.findByContextIdAndContextTypeAndAffectionType(contextId, contextType, affectionType);
        return affections.stream().map(affection -> userService.findById(affection.getUserId())).toList();
    }
}
