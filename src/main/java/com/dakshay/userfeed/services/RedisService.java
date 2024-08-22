package com.dakshay.userfeed.services;

import com.dakshay.userfeed.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedisService  {

    private static RedissonClient redissonClient;
    private static ObjectMapper objectMapper;

    @Autowired
    public RedisService(RedissonClient redissonClient, ObjectMapper objectMapper) {
        RedisService.redissonClient = redissonClient;
        RedisService.objectMapper = objectMapper;
    }

    public static void saveUserToRedis(User user) {
        if (user != null && user.getId() != null) {
            RBucket<User> bucket = redissonClient.getBucket(cacheKey(user.getId()));
            bucket.set(user);
        }
    }

    public static User getUser(Long userId) {
        User user = null;
        if (userId != null) {
            RBucket<User> bucket = redissonClient.getBucket(cacheKey(userId));
            user =  bucket.get();
        }
        return user;
    }

    public static String cacheKey(Long userId){
        return "user-" + userId;
    }
}