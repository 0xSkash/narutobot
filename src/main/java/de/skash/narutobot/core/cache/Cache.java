package de.skash.narutobot.core.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Cache<K, V> {
    protected final Map<K, V> cache = new ConcurrentHashMap<>();

    public abstract void cacheElement(V element);

    public void removeElementFromCache(K key) {
        cache.remove(key);
    }

    public V getElementByKey(K key) {
        return cache.get(key);
    }

    public void clear() {
        cache.clear();
    }
}
