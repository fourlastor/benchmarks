package io.github.fourlastor.benchmark

import kotlinx.collections.immutable.toImmutableList
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import org.pcollections.TreePVector
import java.util.concurrent.*
import kotlin.random.Random

@Warmup(iterations = 7, time = 1)
@Measurement(iterations = 7, time = 1)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
@Fork(2)
open class ListsBenchmark {
    private val random = Random
    private val smallList = List(100) { random.nextInt() }
    private val list = List(1000) { random.nextInt() }
    private val immutableList = list.toImmutableList()
    private val pImmutableList = TreePVector.from(list)
    private val lists = List(10) { smallList.toList() }
    private val immutableLists = List(10) { smallList.toImmutableList() }
    private val pImmutableLists = List(10) { TreePVector.from(smallList) }

    @Benchmark
    fun list(blackhole: Blackhole) {
        for (i in list) {
            blackhole.consume(i)
        }
    }
    @Benchmark
    fun immutableList(blackhole: Blackhole) {
        for (i in immutableList) {
            blackhole.consume(i)
        }
    }
    @Benchmark
    fun pImmutableList(blackhole: Blackhole) {
        for (i in pImmutableList) {
            blackhole.consume(i)
        }
    }
    @Benchmark
    fun lists(blackhole: Blackhole) {
        for (list in lists) {
            for (i in list) {
                blackhole.consume(i)
            }
        }
    }
    @Benchmark
    fun immutableLists(blackhole: Blackhole) {
        for (immutableList in immutableLists) {
            for (i in immutableList) {
                blackhole.consume(i)
            }
        }
    }
    @Benchmark
    fun pImmutableLists(blackhole: Blackhole) {
        for (pImmutableList in pImmutableLists) {
            for (i in pImmutableList) {
                blackhole.consume(i)
            }
        }
    }
}
