package io.github.fourlastor.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

public class TileCoordinatesBenchmark {

    @State(Scope.Benchmark)
    public static class TileInfo {
        public final int x = 3;
        public final int y = 4;
        public final int id = 67;
        public final int width = 16;

        public final int fused = 196612;
    }



//    @Benchmark
    public void testSimpleId(Blackhole blackhole, TileInfo info) {
        blackhole.consume(simpleId(info.x, info.y, info.width));
    }

//    @Benchmark
    public void testSimpleY(Blackhole blackhole, TileInfo info) {
        blackhole.consume(simpleY(info.id, info.width));

    }

//    @Benchmark
    public void testSimpleX(Blackhole blackhole, TileInfo info) {
        blackhole.consume(simpleX(info.id, info.y, info.width));
    }

    private int simpleId(int x, int y, int width) {
        return x + y * width;
    }

    private int simpleY(int id, int width) {
        return id / width;
    }

    private int simpleX(int id, int y, int width) {
        return id - y * width;
    }

//    @Benchmark
    public void testFusedId(Blackhole blackhole, TileInfo info) {
        blackhole.consume(fusedId(info.x, info.y));
    }

//    @Benchmark
    public void testFusedY(Blackhole blackhole, TileInfo info) {
        blackhole.consume(fusedY(info.fused));

    }

//    @Benchmark
    public void testFusedX(Blackhole blackhole, TileInfo info) {
        blackhole.consume(fusedX(info.fused));
    }

    private int fusedId(int x, int y) {
        return x << 16 | (y & 0xFFFF);
    }

    private int fusedY(int fused) {
        return fused & 0xFFFF;
    }

    private int fusedX(int fused) {
        return fused >>> 16;
    }
}
