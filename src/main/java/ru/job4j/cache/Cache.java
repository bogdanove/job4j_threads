package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (a, b) -> {
            if (model.getVersion() != b.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            var result = new Base(b.getId(), b.getVersion() + 1);
            result.setName(model.getName());
            return result;
        }) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId());
    }
}
