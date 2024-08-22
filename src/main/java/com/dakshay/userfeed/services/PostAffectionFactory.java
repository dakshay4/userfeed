package com.dakshay.userfeed.services;

import com.dakshay.userfeed.enums.AffectionContextType;
import com.dakshay.userfeed.enums.AffectionType;
import com.dakshay.userfeed.models.Affection;

public class PostAffectionFactory implements AffectionFactory {

    @Override
    public Affection createAffection(Long contextId, Long userId, AffectionType affectionType) {
        Affection affection = new Affection();
        affection.setContextId(contextId);
        affection.setContextType(AffectionContextType.POST);
        affection.setUserId(userId);
        affection.setAffectionType(affectionType);
        return affection;
    }
}