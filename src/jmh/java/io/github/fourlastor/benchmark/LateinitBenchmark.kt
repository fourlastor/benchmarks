package io.github.fourlastor.benchmark

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.*

@Warmup(iterations = 7, time = 1)
@Measurement(iterations = 7, time = 1)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
@Fork(2)
open class LateinitBenchmark {

    lateinit var notInitialized: Any
    lateinit var initialized: Any

    var nullField: Any? = null
    var notNullField: Any? = 1

    @Setup
    fun init() {
        initialized = 1
    }

    @Benchmark
    fun checkNotInitialized(blackhole: Blackhole) {
        blackhole.consume(this::notInitialized::isInitialized)
    }

    @Benchmark
    fun checkInitialized(blackhole: Blackhole) {
        blackhole.consume(this::initialized::isInitialized)
    }

    @Benchmark
    fun checkNoNull(blackhole: Blackhole) {
        blackhole.consume(notNullField == null)
    }

    @Benchmark
    fun checkNull(blackhole: Blackhole) {
        blackhole.consume(nullField == null)
    }
}
