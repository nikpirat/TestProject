package com.example.TestProject.service.impl;


import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * TASK 4 - CACHING DATA
 */


public class CacheUtilService<K,V> {
    private ConcurrentHashMap<Key,V> globalMap = new ConcurrentHashMap<>();
    private long default_timeout;
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(r -> {
        Thread th = new Thread(r);
        th.setDaemon(true);
        return th;
    });
    //Поток сделаем демоном, для того чтоб при завершении основного потока процесс, который чистит кеш так же завершался.

    public CacheUtilService(long default_timeout)  throws Exception {
        if (default_timeout < 100) {
            throw new Exception("Too short interval for storage in the cache. Interval should be more than 10 ms");
        }
        scheduler.scheduleAtFixedRate(() -> {
            long current = System.currentTimeMillis();
            for (Key k : globalMap.keySet()) {
                if (!k.isLive(current)) {
                    globalMap.remove(k);
                }
            }
        }, 1, default_timeout/5, TimeUnit.MILLISECONDS);
    }

    public void setDefault_timeout(long default_timeout) throws Exception {
        if (default_timeout < 100) {
            throw new Exception("Too short interval for storage in the cache. Interval should be more than 10 ms");
        }
        this.default_timeout = default_timeout;
    }

    /**
     * Метод для вставки обьекта в кеш
     * Время зранения берётся по умолчанию
     * @param key ключ в кеше
     * @param data данные
     */
    public void put(K key, V data) {
        globalMap.put(new Key(key, default_timeout), data);
    }

    /**
     * Метод для вставки обьекта в кеш
     * @param key ключ в кеше
     * @param data данные
     * @param timeout время хранения обьекта в кеше в милисекундах
     */
    public void put(K key, V data, long timeout) {
        globalMap.put(new Key(key, timeout), data);
    }

    /**
     * получение значения по ключу

     * @param key ключ для поиска с кеша
     * @return Обьект данных храняшийся в кеше
     */
    public V get(K key) {
        return globalMap.get(new Key(key));
    }

    /**
     * удаляет все значения по ключу из кеша
     * @param key - ключ
     */
    public void remove(K key) {
        globalMap.remove(new Key(key));
    }

    /**
     * Удаляет все значения из кеша
     */
    public void removeAll() {
        globalMap.clear();
    }

    /**
     * Полностью заменяет весь существующий кеш.
     * Время хранения по умолчанию.
     * @param map Карта с данными
     */
    public void setAll(Map<K, V> map) {
        ConcurrentHashMap<Key,V> tempMap = new ConcurrentHashMap<Key, V>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            tempMap.put(new Key(entry.getKey(), default_timeout), entry.getValue());
        }
        globalMap = tempMap;
    }

    /**
     * Добавляет к сущесвуещему кешу переданую карту
     * Время хранения по умолчанию.
     * @param map Карта с данными
     */
    public void addAll(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    private static class Key{
        private final Object key;
        private long timeLife;

        public Key(Object key, long timeout) {
            this.key = key;
            this.timeLife = System.currentTimeMillis() + timeout;
        }

        public Key(Object key) {
            this.key = key;
        }

        public Object getKey() {
            return key;
        }

        public boolean isLive(long currentTimeMillis) {
            return currentTimeMillis < timeLife;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Key other = (Key) obj;
            if (!Objects.equals(this.key, other.key)) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 43 * hash + (this.key != null ? this.key.hashCode() : 0);
            return hash;
        }

        @Override
        public String toString() {
            return "Key{" + "key=" + key + '}';
        }
    }
}
