package io.github.fourlastor.benchmark;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.ReflectionPool;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

public class AutoReleaseBenchmark {
    @State(Scope.Thread)
    public static class AutoPoolState {
        public final AutoPool<Vector2> pool = new AutoPool<>(Vector2.class);
    }

    @State(Scope.Thread)
    public static class ManualPoolState {
        public final Pool<Vector2> pool  = new ReflectionPool<>(Vector2.class, 15);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void autoRelease(Blackhole blackhole, AutoPoolState state) {
        AutoPool<Vector2> autoPool = state.pool;
        try (AutoPoolable<Vector2> auto = autoPool.obtain()) {
            blackhole.consume(auto.get());
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void manualRelease(Blackhole blackhole, ManualPoolState poolState) {
        Pool<Vector2> pool = poolState.pool;
        Vector2 vector2 = pool.obtain();
        blackhole.consume(vector2);
        pool.free(vector2);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void noPool(Blackhole blackhole) {
        Vector2 vector2 = new Vector2();
        blackhole.consume(vector2);
    }
}
