package com.dakshay.userfeed.services;

import com.dakshay.userfeed.enums.AffectionType;
import com.dakshay.userfeed.models.Affection;

public interface AffectionFactory {

    Affection createAffection(Long contextId, Long userId, AffectionType affectionType);
}
