package com.dakshay.userfeed.services;

import com.dakshay.userfeed.enums.AffectionContextType;

public class AffectionFactoryProvider {

    public static AffectionFactory getFactory(AffectionContextType contextType) {
        return switch (contextType) {
            case POST -> new PostAffectionFactory();
            case COMMENT -> new CommentAffectionFactory();
        };
    }
}
