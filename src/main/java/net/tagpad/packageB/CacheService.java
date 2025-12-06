package net.tagpad.packageB;

import java.util.HashMap;
import java.util.Map;

public class CacheService<K, V> {
    private Map<K, CacheEntry<V>> cache;
    private long defaultTtlMs;

    private static class CacheEntry<V> {
        V value;
        long expiryTime;

        CacheEntry(V value, long expiryTime) {
            this.value = value;
            this.expiryTime = expiryTime;
        }

        boolean isExpired() {
            return System.currentTimeMillis() > expiryTime;
        }
    }

    public CacheService() {
        this(60000); // default 1 minute TTL
    }

    public CacheService(long defaultTtlMs) {
        this.cache = new HashMap<>();
        this.defaultTtlMs = defaultTtlMs;
    }

    public void put(K key, V value) {
        put(key, value, defaultTtlMs);
    }

    public void put(K key, V value, long ttlMs) {
        long expiryTime = System.currentTimeMillis() + ttlMs;
        cache.put(key, new CacheEntry<>(value, expiryTime));
    }

    public V get(K key) {
        CacheEntry<V> entry = cache.get(key);
        if (entry == null) {
            return null;
        }
        if (entry.isExpired()) {
            cache.remove(key);
            return null;
        }
        return entry.value;
    }

    public void remove(K key) {
        cache.remove(key);
    }

    public void clear() {
        cache.clear();
    }

    public int size() {
        return cache.size();
    }
}
