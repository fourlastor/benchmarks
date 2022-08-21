package io.github.fourlastor.benchmark;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
public class AutoPool<T> extends Pool<AutoPoolable<T>> {

    private final Pool<T> pool;

    public AutoPool(Class<T> type) {
        pool = Pools.get(type, 15);
    }

    @Override
    public AutoPoolable<T> obtain() {
        AutoPoolable<T> poolable = super.obtain();
        poolable.set(pool.obtain());
        return poolable;
    }

    @Override
    protected AutoPoolable<T> newObject() {
        return new AutoPoolable<>(
                this,
                pool
        );
    }
}
