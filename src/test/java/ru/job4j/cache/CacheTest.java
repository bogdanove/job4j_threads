package ru.job4j.cache;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CacheTest {

    @Test
    void whenAdd() {
        var cache = new Cache();
        var base = new Base(1, 1);
        base.setName("User");
        assertThat(cache.add(base)).isTrue();
    }

    @Test
    void whenUpdate() {
        var cache = new Cache();
        var base1 = new Base(1, 0);
        base1.setName("User-1");
        var base2 = new Base(1, 0);
        base2.setName("User-2");
        cache.add(base1);
        assertThat(cache.update(base2)).isTrue();
    }

    @Test
    void whenNotUpdateBadVersion() {
        var cache = new Cache();
        var base1 = new Base(1, 0);
        base1.setName("User-1");
        var base2 = new Base(1, 1);
        base2.setName("User-2");
        cache.add(base1);
        assertThrows(OptimisticException.class, () -> {
            cache.update(base2);
        });
    }

    @Test
    void whenNotUpdate() {
        var cache = new Cache();
        var base2 = new Base(1, 1);
        base2.setName("User-2");
        assertThat(cache.update(base2)).isFalse();
    }

    @Test
    void whenDelete() {
        var cache = new Cache();
        var base1 = new Base(1, 0);
        base1.setName("User-1");
        var base2 = new Base(1, 0);
        base2.setName("User-2");
        cache.add(base1);
        cache.delete(base1);
        assertThat(cache.update(base2)).isFalse();
    }
}