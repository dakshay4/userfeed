package com.dakshay.userfeed.services;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisLockService {

    private final RedissonClient redissonClient;

    public RedisLockService(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    private static final long LOCK_EXPIRE = 2000; // Lock expiration time in seconds
    private static final long RETRY_DELAY = 1000; // Retry delay in milliseconds
    private static final int THRESHOLD_COUNT = 5; // Retry delay in milliseconds

    public boolean acquireLock(String key) {
       return acquireLock(key, THRESHOLD_COUNT);
    }
    private boolean acquireLock(String key, int threshold) {
        RLock lock = redissonClient.getLock(key);
        try {
            // Try to acquire the lock
            if (lock.tryLock(0, LOCK_EXPIRE, TimeUnit.MILLISECONDS)) {
                return true; // Lock acquired
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupt status
        }

        try {
            // Wait before retrying
            Thread.sleep(RETRY_DELAY);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupt status
        }
        if(threshold>0) acquireLock(key, threshold-1);
        return false;
    }

    /**
     * Releases the lock based on the given key.
     *
     * @param key The key for the lock.
     */
    public void releaseLock(String key) {
        RLock lock = redissonClient.getLock(key);
        if (lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

}
